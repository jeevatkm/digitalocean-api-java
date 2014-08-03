/**
 * 
 */
package com.myjeeva.digitalocean.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.myjeeva.digitalocean.common.ApiAction;
import com.myjeeva.digitalocean.common.Constants;
import com.myjeeva.digitalocean.common.RequestMethod;
import com.myjeeva.digitalocean.exception.AccessDeniedException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.exception.ResourceNotFoundException;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.serializer.DropletSerializer;

/**
 * @author madanj01
 *
 */
public abstract class ClientHelper implements Constants {

  private final Logger LOG = LoggerFactory.getLogger(ClientHelper.class);

  /**
   * Http client
   */
  protected HttpClient httpClient;

  /**
   * OAuth Authorization Token for Accessing DigitalOcean API
   */
  protected String authToken;

  /**
   * DigitalOcean API version. defaults to v2 from constructor
   */
  protected String apiVersion;

  /**
   * DigitalOcean API Host is <code>api.digitalocean.com</code>
   */
  protected String apiHost = "api.digitalocean.com";

  /**
   * JSON Parser instance for deserialize
   */
  private Gson deserialize;

  /**
   * JSON Parser instance for serialize
   */
  private Gson serialize;

  private JsonParser jsonParser;

  public ClientHelper() {
    this.deserialize = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

    this.serialize =
        new GsonBuilder().setDateFormat(DATE_FORMAT)
            .registerTypeAdapter(Droplet.class, new DropletSerializer())
            .excludeFieldsWithoutExposeAnnotation().create();

    this.jsonParser = new JsonParser();
  }

  /**
   * @return the httpClient
   */
  public HttpClient getHttpClient() {
    return httpClient;
  }

  /**
   * @return the authToken
   */
  public String getAuthToken() {
    return authToken;
  }

  /**
   * @return the apiVersion
   */
  public String getApiVersion() {
    return apiVersion;
  }

  /**
   * @return the apiHost
   */
  public String getApiHost() {
    return apiHost;
  }

  protected ApiResponse performAction(ApiRequest request) throws AccessDeniedException,
      ResourceNotFoundException, RequestUnsuccessfulException {

    URI uri = createUri(request);
    String response = null;

    if (RequestMethod.GET.equals(request.getMethod())) {
      response = doGet(uri);
    } else if (RequestMethod.POST.equals(request.getMethod())) {
      response = doPost(uri, getRequestData(request));
    } else if (RequestMethod.PUT.equals(request.getMethod())) {
      response = doPut(uri, getRequestData(request));
    } else if (RequestMethod.DELETE.equals(request.getMethod())) {
      response = doDelete(uri);
    }

    ApiResponse apiResponse = new ApiResponse(request.getApiAction(), true);

    if ("true".equals(response) || "false".equals(response)) {
      apiResponse.setData(Boolean.valueOf(response));
    } else {
      if (request.getElementName().endsWith("s")) {
        apiResponse.setData(deserialize.fromJson(response, request.getClazz()));
      } else {
        JsonElement element =
            jsonParser.parse(response).getAsJsonObject().get(request.getElementName());
        apiResponse.setData(deserialize.fromJson(element, request.getClazz()));
      }
    }

    return apiResponse;
  }

  private String doGet(URI uri) throws AccessDeniedException, ResourceNotFoundException,
      RequestUnsuccessfulException {
    HttpGet get = new HttpGet(uri);
    get.setHeaders(getHttpHeaders());
    return execute(get);
  }

  private String doPost(URI uri, StringEntity entity) throws AccessDeniedException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    HttpPost post = new HttpPost(uri);
    post.setHeaders(getHttpHeaders());

    if (null != entity) {
      post.setEntity(entity);
    }

    return execute(post);
  }

  private String doPut(URI uri, StringEntity entity) throws AccessDeniedException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    HttpPut put = new HttpPut(uri);
    put.setHeaders(getHttpHeaders());

    if (null != entity) {
      put.setEntity(entity);
    }

    return execute(put);
  }

  private String doDelete(URI uri) throws AccessDeniedException, ResourceNotFoundException,
      RequestUnsuccessfulException {
    HttpDelete delete = new HttpDelete(uri);
    delete.setHeaders(getHttpHeaders());
    delete.setHeader("Content-Type", FORM_URLENCODED_CONTENT_TYPE);
    return execute(delete);
  }

  private String execute(HttpRequestBase request) throws AccessDeniedException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    String response = "";
    try {
      HttpResponse httpResponse = httpClient.execute(request);

      int statusCode = httpResponse.getStatusLine().getStatusCode();
      if (HttpStatus.SC_NO_CONTENT == statusCode) {
        response = "true";
      }

      if (HttpStatus.SC_UNAUTHORIZED == statusCode) {
        throw new AccessDeniedException(
            "Request failed to authenticate into the DigitalOcean API successfully");
      }

      if (HttpStatus.SC_NOT_FOUND == statusCode) {
        throw new ResourceNotFoundException("Requested resource is not available at DigitalOcean");
      }

      if (null != httpResponse.getEntity()) {
        response = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
      }

      LOG.debug("HTTP Response: " + response);
    } catch (ClientProtocolException cpe) {
      throw new RequestUnsuccessfulException(cpe.getMessage(), cpe);
    } catch (IOException ioe) {
      throw new RequestUnsuccessfulException(ioe.getMessage(), ioe);
    } finally {
      request.releaseConnection();
    }

    return response;
  }

  private URI createUri(ApiRequest request) {
    URIBuilder ub = new URIBuilder();
    ub.setScheme(HTTPS_SCHEME);
    ub.setHost(apiHost);
    ub.setPath(createPath(request));

    if (null != request.getPageNo()) {
      ub.setParameter(PARAM_PAGE_NO, request.getPageNo().toString());
    }

    URI uri = null;
    try {
      uri = ub.build();
    } catch (URISyntaxException use) {
      LOG.error(use.getMessage(), use);
    }

    return uri;
  }

  private Header[] getHttpHeaders() {
    Header[] headers =
        {new BasicHeader("X-User-Agent", "DigitalOcean API Client in Java by myjeeva.com"),
            new BasicHeader("Content-Type", JSON_CONTENT_TYPE),
            new BasicHeader("Authorization", "Bearer " + authToken)};
    return headers;
  }

  private String createPath(ApiRequest request) {
    String path = URL_PATH_SEPARATOR + apiVersion + request.getApiAction().getPath();
    return (null == request.getParams() ? path : String.format(path, request.getParams()));
  }

  private StringEntity getRequestData(ApiRequest request) {
    StringEntity data = null;

    if (null != request.getData()) {
      String inputData = serialize.toJson(request.getData());
      data = new StringEntity(inputData, ContentType.create(JSON_CONTENT_TYPE));
    }

    return data;
  }
}

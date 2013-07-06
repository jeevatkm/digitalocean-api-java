package com.myjeeva.digitalocean.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.myjeeva.digitalocean.Constants;
import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.DoUtils;
import com.myjeeva.digitalocean.common.DigitalOceanAction;
import com.myjeeva.digitalocean.exception.AccessDeniedException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.exception.ResourceNotFoundException;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.DropletImage;
import com.myjeeva.digitalocean.pojo.DropletPlan;
import com.myjeeva.digitalocean.pojo.Region;
import com.myjeeva.digitalocean.pojo.Response;
import com.myjeeva.digitalocean.pojo.SshKey;

/**
 * 
 * 
 * @author Jeevanandam (jeeva@myjeeva.com)
 * 
 */
@SuppressWarnings("unchecked")
public class DigitalOceanClient implements DigitalOcean, Constants {

	private final Logger LOG = LoggerFactory
			.getLogger(DigitalOceanClient.class);

	/**
	 * Http client
	 */
	private DefaultHttpClient httpClient;

	/**
	 * User's Client ID
	 */
	private String clientId;

	/**
	 * User's API key
	 */
	private String apiKey;

	/**
	 * Constructor
	 * 
	 * @param clientId
	 *            a {@link String} object
	 * @param apiKey
	 *            a {@link String} object
	 */
	public DigitalOceanClient(String clientId, String apiKey) {
		this.clientId = clientId;
		this.apiKey = apiKey;
		this.httpClient = DoUtils.getHttpClient();
	}

	@Override
	public List<Droplet> getAvailableDroplets() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		String data = performAndGetJsonData(DigitalOceanAction.AVAILABLE_DROPLETS);
		Type collectionType = new TypeToken<List<Droplet>>() {}.getType();
		return (List<Droplet>) DoUtils.getGson().fromJson(data, collectionType);
	}

	@Override
	public Droplet createDroplet(Droplet droplet, String sshKeyIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Droplet getDropletInfo(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		String data = performAndGetJsonData(
				DigitalOceanAction.GET_DROPLET_INFO, params);
		return DoUtils.getGson().fromJson(data, Droplet.class);
	}

	@Override
	public Response rebootDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response powerCyleDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response shutdownDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response powerOffDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response powerOnDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response resetDropletPassword(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response resizeDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response takeDropletSnapshot(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response restoreDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response rebuildDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response enableDropletBackups(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response disableDropletBackups(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response renameDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteDroplet(Integer dropletId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Region> getAvailableRegions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DropletImage> getAvailableImages()
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		String data = performAndGetJsonData(DigitalOceanAction.AVAILABLE_IMAGES);
		Type collectionType = new TypeToken<List<DropletImage>>() {}.getType();
		return (List<DropletImage>) DoUtils.getGson().fromJson(data,
				collectionType);
	}

	@Override
	public DropletImage getImageInfo(Integer imageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteImage(Integer imageId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response transerImage(Integer imageId, Integer regionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SshKey> getAvailableSshKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SshKey getSshKeyInfo(Integer sshKeyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SshKey addSshKey(String sshKeyName, String sshPublicKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SshKey editSshKey(Integer sshKeyId, String sshPublicKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteSshKey(Integer sshKeyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DropletPlan> getAvailablePlans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Domain> getAvailableDomains() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Domain getDomainInfo(Integer domainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Domain createDomain(String domainName, String ipAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Domain deleteDomain(Integer domainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DomainRecord> getDomainRecords(Integer domainId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainRecord getDomainRecord(Integer domainId, Integer recordId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainRecord createDomainRecord(DomainRecord domainRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DomainRecord editDomainRecord(DomainRecord domainRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteDomainRecord(DomainRecord domainRecord) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String performAndGetJsonData(DigitalOceanAction action,
			Object... params) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		URI uri = generateUri(action, params);
		LOG.debug("Request URI: " + uri);

		String response = "";
		try {
			response = execute(uri);
			LOG.debug("HTTP Response: " + response);
		} catch (ClientProtocolException cpe) {
			throw new RequestUnsuccessfulException(cpe.getMessage(), cpe);
		} catch (IOException ioe) {
			throw new RequestUnsuccessfulException(ioe.getMessage(), ioe);
		}

		JsonElement element = new JsonParser().parse(response);
		JsonObject obj = element.getAsJsonObject();
		String status = obj.get(STATUS).getAsString();
		LOG.info("DigitalOcean Response Status: " + status);

		if ("OK".equalsIgnoreCase(status)) {
			String data = obj.get(action.getElementName()).toString();
			LOG.debug("JSON Respose Data: " + data);

			return data;
		} else {
			throw new RequestUnsuccessfulException(
					"DigitalOcean request unsuccessful [" + uri + "]");
		}
	}

	private URI generateUri(DigitalOceanAction action, Object... params) {

		URIBuilder ub = DoUtils.getBaseUri();
		ub.setPath(processPath(action, params));
		ub.setParameter(PARAM_CLIENT_ID, this.clientId);
		ub.setParameter(PARAM_API_KEY, this.apiKey);

		URI uri = null;
		try {
			uri = ub.build();
		} catch (URISyntaxException use) {
			LOG.error(use.getMessage(), use);
		}

		return uri;
	}

	private String processPath(DigitalOceanAction action, Object...params) {
		return String.format(action.getMapPath(), params);
	}

	private String execute(URI uri) throws ClientProtocolException, IOException, AccessDeniedException, ResourceNotFoundException {
		HttpGet httpGet = new HttpGet(uri);
		LOG.debug("API Endpoint URI: " + uri);

		String response = "";
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);

			if (401 == httpResponse.getStatusLine().getStatusCode()) {
				throw new AccessDeniedException("Request failed to authenticate into the DigitalOcean API successfully");
			}

			if (404 == httpResponse.getStatusLine().getStatusCode()) {
				throw new ResourceNotFoundException("Request failed to authenticate into the DigitalOcean API successfully");
			}

			HttpEntity entity = httpResponse.getEntity();

			if (null != entity) {
				InputStream input = entity.getContent();
				byte[] data = IOUtils.toByteArray(input);
				response = StringUtils.newStringUtf8(data);
				IOUtils.closeQuietly(input);
			}

		} finally {
			httpGet.releaseConnection();
		}

		return response;
	}

}

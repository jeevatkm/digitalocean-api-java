/* The MIT License
 *
 * Copyright (c) 2010-2013 Jeevanandam M. (myjeeva.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 * 
 */
package com.myjeeva.digitalocean.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.myjeeva.digitalocean.Constants;
import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.common.Action;
import com.myjeeva.digitalocean.exception.AccessDeniedException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.exception.ResourceNotFoundException;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.DropletImage;
import com.myjeeva.digitalocean.pojo.DropletSize;
import com.myjeeva.digitalocean.pojo.Region;
import com.myjeeva.digitalocean.pojo.Response;
import com.myjeeva.digitalocean.pojo.SshKey;

/**
 * DigitalOcean API client wrapper methods Implementation
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
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
	 * DigitalOcean API Host is <code>api.digitalocean.com</code>
	 */
	private String apiHost = "api.digitalocean.com";
	
	/**
	 * Gson Parser
	 */
	private Gson gson;

	/**
	 * Constructor for initializing DigitalOcean Client
	 * 
	 * @param clientId
	 *            a {@link String} object
	 * @param apiKey
	 *            a {@link String} object
	 * @throws ResourceNotFoundException
	 */
	public DigitalOceanClient(String clientId, String apiKey) {
		this.clientId = clientId;
		this.apiKey = apiKey;
		
		// Initializing required variable
		this.gson = new Gson();
		this.httpClient = new DefaultHttpClient(new PoolingClientConnectionManager()); //DoUtils.getHttpClient();
	}

	@Override
	public List<Droplet> getAvailableDroplets() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		JsonObject data = performAction(Action.AVAILABLE_DROPLETS);
		Type collectionType = new TypeToken<List<Droplet>>() {}.getType();
		return (List<Droplet>) gson.fromJson(
				data.get(Action.AVAILABLE_DROPLETS.getElementName())
						.toString(), collectionType);
	}
	
	@Override
	public Droplet createDroplet(Droplet droplet) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return createDroplet(droplet, null);
	}

	@Override
	public Droplet createDroplet(Droplet droplet, String sshKeyIds)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {

		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_NAME, droplet.getName());
		qp.put(PARAM_SIDE_ID, String.valueOf(droplet.getSizeId()));
		qp.put(PARAM_IMAGE_ID, String.valueOf(droplet.getImageId()));		
		qp.put(PARAM_REGION_ID, String.valueOf(droplet.getRegionId()));
		
		if(null != sshKeyIds) {
			qp.put(PARAM_SSH_KEY_IDS, sshKeyIds);
		}
		
		JsonObject data = performAction(Action.CREATE_DROPLET, qp);
		return gson.fromJson(
				data.get(Action.CREATE_DROPLET.getElementName()).toString(),
				Droplet.class);
	}

	@Override
	public Droplet getDropletInfo(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.GET_DROPLET_INFO,
				params);
		return gson
				.fromJson(
						data.get(Action.GET_DROPLET_INFO.getElementName())
								.toString(), Droplet.class);
	}

	@Override
	public Response rebootDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.REBOOT_DROPLET, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response powerCyleDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.POWER_CYCLE_DROPLET, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response shutdownDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.SHUTDOWN_DROPLET, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response powerOffDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.POWER_OFF_DROPLET, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response powerOnDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.POWER_ON_DROPLET, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response resetDropletPassword(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.RESET_PASSWORD_DROPLET, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response resizeDroplet(Integer dropletId, Integer sizeId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_SIDE_ID, String.valueOf(sizeId));

		JsonObject data = performAction(Action.RESET_PASSWORD_DROPLET, qp,
				params);
		return gson.fromJson(data.toString(), Response.class);
	}
	
	@Override
	public Response takeDropletSnapshot(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return takeDropletSnapshot(dropletId, null);
	}

	@Override
	public Response takeDropletSnapshot(Integer dropletId, String snapshotName)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		Map<String, String> qp = null;

		if (null != snapshotName) {
			qp = new HashMap<String, String>();
			qp.put(PARAM_NAME, String.valueOf(snapshotName));
		}

		JsonObject data = performAction(Action.TAKE_DROPLET_SNAPSHOT, qp,
				params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response restoreDroplet(Integer dropletId, Integer imageId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_IMAGE_ID, String.valueOf(imageId));

		JsonObject data = performAction(Action.RESTORE_DROPLET, qp,
				params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response rebuildDroplet(Integer dropletId, Integer imageId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_IMAGE_ID, String.valueOf(imageId));

		JsonObject data = performAction(Action.REBUILD_DROPLET, qp,
				params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response enableDropletBackups(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.ENABLE_AUTOMATIC_BACKUPS,
				params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response disableDropletBackups(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.DISABLE_AUTOMATIC_BACKUPS,
				params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response renameDroplet(Integer dropletId, String name)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};

		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_NAME, String.valueOf(name));

		JsonObject data = performAction(Action.RENAME_DROPLET, qp, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response deleteDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {dropletId};
		JsonObject data = performAction(Action.DELETE_DROPLET, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public List<Region> getAvailableRegions() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		JsonObject data = performAction(Action.AVAILABLE_REGIONS);
		Type collectionType = new TypeToken<List<Region>>() {}.getType();
		return (List<Region>) gson.fromJson(
				data.get(Action.AVAILABLE_REGIONS.getElementName())
						.toString(), collectionType);
	}

	@Override
	public List<DropletImage> getAvailableImages()
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		JsonObject data = performAction(Action.AVAILABLE_IMAGES);
		Type collectionType = new TypeToken<List<DropletImage>>() {}.getType();
		return (List<DropletImage>) gson
				.fromJson(
						data.get(Action.AVAILABLE_IMAGES.getElementName())
								.toString(), collectionType);
	}

	@Override
	public DropletImage getImageInfo(Integer imageId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {imageId};
		JsonObject data = performAction(Action.GET_IMAGE_INFO, params);
		return gson.fromJson(
				data.get(Action.GET_IMAGE_INFO.getElementName()).toString(),
				DropletImage.class);
	}

	@Override
	public Response deleteImage(Integer imageId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		Object[] params = {imageId};
		JsonObject data = performAction(Action.DELETE_IMAGE, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public Response transerImage(Integer imageId, Integer regionId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {imageId};

		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_REGION_ID, String.valueOf(regionId));

		JsonObject data = performAction(Action.TRANSFER_IMAGE, qp, params);
		return gson.fromJson(data.toString(), Response.class);
	}

	@Override
	public List<SshKey> getAvailableSshKeys() {
		throw new UnsupportedOperationException("To be released in v1.2");
	}

	@Override
	public SshKey getSshKeyInfo(Integer sshKeyId) {
		throw new UnsupportedOperationException("To be released in v1.2");
	}

	@Override
	public SshKey addSshKey(String sshKeyName, String sshPublicKey) {
		throw new UnsupportedOperationException("To be released in v1.2");
	}

	@Override
	public SshKey editSshKey(Integer sshKeyId, String sshPublicKey) {
		throw new UnsupportedOperationException("To be released in v1.2");
	}

	@Override
	public Response deleteSshKey(Integer sshKeyId) {
		throw new UnsupportedOperationException("To be released in v1.2");
	}

	@Override
	public List<DropletSize> getAvailableSizes() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		JsonObject data = performAction(Action.AVAILABLE_SIZES);
		Type collectionType = new TypeToken<List<DropletSize>>() {}.getType();
		return (List<DropletSize>) gson.fromJson(
				data.get(Action.AVAILABLE_SIZES.getElementName()).toString(),
				collectionType);
	}

	@Override
	public List<Domain> getAvailableDomains() {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	@Override
	public Domain getDomainInfo(Integer domainId) {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	@Override
	public Domain createDomain(String domainName, String ipAddress) {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	@Override
	public Domain deleteDomain(Integer domainId) {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	@Override
	public List<DomainRecord> getDomainRecords(Integer domainId) {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	@Override
	public DomainRecord getDomainRecord(Integer domainId, Integer recordId) {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	@Override
	public DomainRecord createDomainRecord(DomainRecord domainRecord) {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	@Override
	public DomainRecord editDomainRecord(DomainRecord domainRecord) {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	@Override
	public Response deleteDomainRecord(Integer domainId, Integer recordId) {
		throw new UnsupportedOperationException("To be released in v1.1");
	}

	private JsonObject performAction(Action action,
			Object... pathParams) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return performAction(action, null, pathParams);
	}
	
	private JsonObject performAction(Action action,
			Map<String, String> queryParams, Object... pathParams)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		URI uri = generateUri(action, queryParams, pathParams);
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
			LOG.debug("JSON Respose Data: " + obj.toString());
			return obj;
		} else {
			throw new RequestUnsuccessfulException(
					"DigitalOcean request unsuccessful [" + uri + "]");
		}
	}

	private URI generateUri(Action action, Map<String, String> queryParams,
			Object... pathParams) {

		URIBuilder ub = new URIBuilder();
		ub.setScheme(HTTPS_SCHEME);
		ub.setHost(apiHost);
		ub.setPath(String.format(action.getMapPath(), pathParams));
		ub.setParameter(PARAM_CLIENT_ID, this.clientId);
		ub.setParameter(PARAM_API_KEY, this.apiKey);

		if (null != queryParams) {
			for (Map.Entry<String, String> entry : queryParams.entrySet()) {
				ub.setParameter(entry.getKey(), entry.getValue());
			}
		}

		URI uri = null;
		try {
			uri = ub.build();
		} catch (URISyntaxException use) {
			LOG.error(use.getMessage(), use);
		}

		return uri;
	}

	private String execute(URI uri) throws ClientProtocolException,
			IOException, AccessDeniedException, ResourceNotFoundException {
		HttpGet httpGet = new HttpGet(uri);
		LOG.debug("DigitalOcean API Endpoint URI: " + uri);

		String response = "";
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);

			if (401 == httpResponse.getStatusLine().getStatusCode()) {
				throw new AccessDeniedException(
						"Request failed to authenticate into the DigitalOcean API successfully");
			}

			if (404 == httpResponse.getStatusLine().getStatusCode()) {
				throw new ResourceNotFoundException(
						"Requested resource is not available DigitalOcean");
			}

			HttpEntity entity = httpResponse.getEntity();

			if (null != entity) {
				InputStream input = entity.getContent();
				response = readInputStream(input);

				// Let's close the stream
				try {
					if (null != input) {
						input.close();
					}
				} catch (IOException ioe) {
					LOG.error("Error occured while reading HTTP input stream ["
							+ ioe.getMessage() + "]");
				}
			}

		} finally {
			httpGet.releaseConnection();
		}

		return response;
	}
	
	private String readInputStream(InputStream input) throws IOException {

		ByteArrayOutputStream output = new ByteArrayOutputStream();
		int n = 0;
		byte[] buffer = new byte[4096];
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
		}

		return output.toString(UTF_8);
	}

}

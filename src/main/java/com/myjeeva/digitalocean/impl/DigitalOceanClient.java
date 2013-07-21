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

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.myjeeva.digitalocean.Constants;
import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.Utils;
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

		// Initializing required variable(s)
		this.httpClient = new DefaultHttpClient(
				new PoolingClientConnectionManager());
	}

	@Override
	public List<Droplet> getAvailableDroplets() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return (List<Droplet>) processByScope(Action.AVAILABLE_DROPLETS,
				TYPE_DROPLET_LIST);
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

		if (null != sshKeyIds) {
			qp.put(PARAM_SSH_KEY_IDS, sshKeyIds);
		}

		return (Droplet) processByScope(Action.CREATE_DROPLET, Droplet.class,
				qp);
	}

	@Override
	public Droplet getDropletInfo(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return (Droplet) processByScope(Action.GET_DROPLET_INFO, Droplet.class,
				dropletId);
	}

	@Override
	public Response rebootDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.REBOOT_DROPLET, dropletId);
	}

	@Override
	public Response powerCyleDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.POWER_CYCLE_DROPLET, dropletId);
	}

	@Override
	public Response shutdownDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.SHUTDOWN_DROPLET, dropletId);
	}

	@Override
	public Response powerOffDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.POWER_OFF_DROPLET, dropletId);
	}

	@Override
	public Response powerOnDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.POWER_ON_DROPLET, dropletId);
	}

	@Override
	public Response resetDropletPassword(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.RESET_PASSWORD_DROPLET, dropletId);
	}

	@Override
	public Response resizeDroplet(Integer dropletId, Integer sizeId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_SIDE_ID, String.valueOf(sizeId));

		return process(Action.RESIZE_DROPLET, dropletId, qp);
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
		Response response = null;
		if (null == snapshotName) {
			response = process(Action.TAKE_DROPLET_SNAPSHOT, dropletId);
		} else {
			Map<String, String> qp = new HashMap<String, String>();
			qp.put(PARAM_NAME, snapshotName);
			response = process(Action.TAKE_DROPLET_SNAPSHOT, dropletId, qp);
		}

		return response;
	}

	@Override
	public Response restoreDroplet(Integer dropletId, Integer imageId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_IMAGE_ID, String.valueOf(imageId));

		return process(Action.RESTORE_DROPLET, dropletId, qp);
	}

	@Override
	public Response rebuildDroplet(Integer dropletId, Integer imageId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_IMAGE_ID, String.valueOf(imageId));

		return process(Action.REBUILD_DROPLET, dropletId, qp);
	}

	@Override
	public Response enableDropletBackups(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.ENABLE_AUTOMATIC_BACKUPS, dropletId);
	}

	@Override
	public Response disableDropletBackups(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.DISABLE_AUTOMATIC_BACKUPS, dropletId);
	}

	@Override
	public Response renameDroplet(Integer dropletId, String name)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_NAME, String.valueOf(name));

		return process(Action.RENAME_DROPLET, dropletId, qp);
	}

	@Override
	public Response deleteDroplet(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.DELETE_DROPLET, dropletId);
	}

	@Override
	public List<Region> getAvailableRegions() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return (List<Region>) processByScope(Action.AVAILABLE_REGIONS,
				TYPE_REGION_LIST);
	}

	@Override
	public List<DropletImage> getAvailableImages()
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return (List<DropletImage>) processByScope(Action.AVAILABLE_IMAGES,
				TYPE_IMAGE_LIST);
	}

	@Override
	public DropletImage getImageInfo(Integer imageId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return (DropletImage) processByScope(Action.GET_IMAGE_INFO,
				DropletImage.class, imageId);
	}

	@Override
	public Response deleteImage(Integer imageId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return process(Action.DELETE_IMAGE, imageId);
	}

	@Override
	public Response transferImage(Integer imageId, Integer regionId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_REGION_ID, String.valueOf(regionId));

		return process(Action.TRANSFER_IMAGE, imageId, qp);
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
		return (List<DropletSize>) processByScope(Action.AVAILABLE_SIZES,
				TYPE_SIZE_LIST);
	}

	@Override
	public List<Domain> getAvailableDomains() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return (List<Domain>) processByScope(Action.AVAILABLE_DOMAINS,
				TYPE_DOMAIN_LIST);
	}

	@Override
	public Domain getDomainInfo(Integer domainId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return (Domain) processByScope(Action.GET_DOMAIN_INFO, Domain.class,
				domainId);
	}

	@Override
	public Domain createDomain(String domainName, String ipAddress)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Map<String, String> qp = new HashMap<String, String>();
		qp.put(PARAM_NAME, domainName);
		qp.put(PARAM_IP_ADDRESS, ipAddress);

		return (Domain) processByScope(Action.CREATE_DOMAIN, Domain.class, qp);
	}

	@Override
	public Response deleteDomain(Integer domainId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(Action.DELETE_DOMAIN, domainId);
	}

	@Override
	public List<DomainRecord> getDomainRecords(Integer domainId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return (List<DomainRecord>) processByScope(Action.GET_DOMAIN_RECORDS,
				TYPE_DOMAIN_RECORD_LIST, domainId);
	}

	@Override
	public DomainRecord getDomainRecordInfo(Integer domainId, Integer recordId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {domainId, recordId};
		return (DomainRecord) processByScope(Action.GET_DOMAIN_RECORD_INFO,
				DomainRecord.class, params);
	}

	@Override
	public DomainRecord createDomainRecord(DomainRecord domainRecord)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return (DomainRecord) processByScope(Action.CREATE_DOMAIN_RECORD,
				DomainRecord.class,
				Utils.prepareDomainRecordParams(domainRecord),
				domainRecord.getDomainId());
	}

	@Override
	public DomainRecord editDomainRecord(DomainRecord domainRecord)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {domainRecord.getDomainId(), domainRecord.getId()};
		return (DomainRecord) processByScope(Action.EDIT_DOMAIN_RECORD,
				DomainRecord.class,
				Utils.prepareDomainRecordParams(domainRecord), params);
	}

	@Override
	public Response deleteDomainRecord(Integer domainId, Integer recordId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		Object[] params = {domainId, recordId};
		return process(Action.DELETE_DOMAIN_RECORD, params);
	}

	private JsonObject performAction(Action action,
			Map<String, String> queryParams, Object... pathParams)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		URI uri = generateUri(action.getMapPath(), queryParams, pathParams);

		String response = "";
		try {
			response = execute(uri);
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
					"DigitalOcean API request unsuccessful, possible reason colud be incorrect values ["
							+ uri + "].");
		}
	}

	private URI generateUri(String path, Map<String, String> queryParams,
			Object... pathParams) {

		URIBuilder ub = new URIBuilder();
		ub.setScheme(HTTPS_SCHEME);
		ub.setHost(apiHost);
		ub.setPath(String.format(path, pathParams));
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
				response = Utils.readInputStream(input);

				// Let's close the stream
				try {
					if (null != input) {
						input.close();
					}
				} catch (IOException ioe) {
					LOG.error("Error occured while reading HTTP input stream ["
							+ ioe.getMessage() + "]");
				}

				LOG.debug("HTTP Response: " + response);
			}

		} finally {
			httpGet.releaseConnection();
		}

		return response;
	}

	private Response process(Action action, Object... id)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return process(action, id, null);
	}

	private Response process(Action action, Object[] id,
			Map<String, String> queryParams) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return (Response) Utils.byClass(performAction(action, queryParams, id),
				Response.class);
	}

	private Object processByScope(Action action, Class<?> clazz,
			Object... pathParams) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return processByScope(action, clazz, null, pathParams);
	}

	private Object processByScope(Action action, Class<?> clazz,
			Map<String, String> queryParams, Object... pathParams)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		return Utils.byClass(performAction(action, queryParams, pathParams)
				.get(action.getElementName()), clazz);
	}

	private Object processByScope(Action action, Type type,
			Object... pathParams) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		return Utils.byType(
				performAction(action, null, pathParams).get(
						action.getElementName()), type);
	}

}

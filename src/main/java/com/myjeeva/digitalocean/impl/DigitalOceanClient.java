package com.myjeeva.digitalocean.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.common.DigitalOceanAction;
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

public class DigitalOceanClient implements DigitalOcean {

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
     * API end point - defaults to https://api.digitalocean.com
     */
    private String apiEndPoint = "https://api.digitalocean.com";

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
	this.httpClient = new DefaultHttpClient();
    }

    @Override
    public Collection<Droplet> getAvailableDroplets() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Droplet createDroplet(Droplet droplet, String sshKeyIds) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Droplet getDropletInfo(Integer dropletId) {
	// TODO Auto-generated method stub
	return null;
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
    public Collection<Region> getAvailableRegions() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Collection<DropletImage> getAvailableImages() {
	// TODO Auto-generated method stub
	return null;
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
    public Collection<SshKey> getAvailableSshKeys() {
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
    public Collection<DropletPlan> getAvailablePlans() {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Collection<Domain> getAvailableDomains() {
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
    public Collection<DomainRecord> getDomainRecords(Integer domainId) {
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

    private String execute(String uri) throws ClientProtocolException,
	    IOException {
	HttpGet httpGet = new HttpGet(uri);
	LOG.debug("API Endpoint: " + uri);

	String response = "";
	try {
	    HttpResponse httpResponse = httpClient.execute(httpGet);
	    HttpEntity entity = httpResponse.getEntity();

	    if (null != entity) {
		InputStream input = entity.getContent();
		byte[] data = IOUtils.toByteArray(input);
		response = StringUtils.newStringUtf8(data);
		IOUtils.closeQuietly(input);

		LOG.debug("Resposne: " + response);
	    }

	} finally {
	    httpGet.releaseConnection();
	}

	return response;
    }

    /**
     * @return the apiEndPoint
     */
    public String getApiEndPoint() {
	return apiEndPoint;
    }

    /**
     * @param apiEndPoint
     *            the apiEndPoint to set
     */
    public void setApiEndPoint(String apiEndPoint) {
	this.apiEndPoint = apiEndPoint;
    }

}

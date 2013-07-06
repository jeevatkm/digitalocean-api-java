package com.myjeeva.digitalocean;

import java.util.List;

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
public interface DigitalOcean {

	/*
	 * Droplet manipulation methods
	 */

	List<Droplet> getAvailableDroplets() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	Droplet createDroplet(Droplet droplet, String sshKeyIds);

	Droplet getDropletInfo(Integer dropletId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	Response rebootDroplet(Integer dropletId);

	Response powerCyleDroplet(Integer dropletId);

	Response shutdownDroplet(Integer dropletId);

	Response powerOffDroplet(Integer dropletId);

	Response powerOnDroplet(Integer dropletId);

	Response resetDropletPassword(Integer dropletId);

	Response resizeDroplet(Integer dropletId);

	Response takeDropletSnapshot(Integer dropletId);

	Response restoreDroplet(Integer dropletId);

	Response rebuildDroplet(Integer dropletId);

	Response enableDropletBackups(Integer dropletId);

	Response disableDropletBackups(Integer dropletId);

	Response renameDroplet(Integer dropletId);

	Response deleteDroplet(Integer dropletId);

	/*
	 * Regions (aka Data Centers) methods
	 */
	List<Region> getAvailableRegions();

	/*
	 * Images manipulation (aka Distribution) methods
	 */
	List<DropletImage> getAvailableImages() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	DropletImage getImageInfo(Integer imageId);

	Response deleteImage(Integer imageId);

	Response transerImage(Integer imageId, Integer regionId);

	/*
	 * SSH Key manipulation methods
	 */
	List<SshKey> getAvailableSshKeys();

	SshKey getSshKeyInfo(Integer sshKeyId);

	SshKey addSshKey(String sshKeyName, String sshPublicKey);

	SshKey editSshKey(Integer sshKeyId, String sshPublicKey);

	Response deleteSshKey(Integer sshKeyId);

	/*
	 * Sizes (aka Available Droplet Plans) methods
	 */
	List<DropletPlan> getAvailablePlans();

	/*
	 * Domain manipulation methods
	 */
	List<Domain> getAvailableDomains();

	Domain getDomainInfo(Integer domainId);

	Domain createDomain(String domainName, String ipAddress);

	Domain deleteDomain(Integer domainId);

	List<DomainRecord> getDomainRecords(Integer domainId);

	DomainRecord getDomainRecord(Integer domainId, Integer recordId);

	DomainRecord createDomainRecord(DomainRecord domainRecord);

	DomainRecord editDomainRecord(DomainRecord domainRecord);

	Response deleteDomainRecord(DomainRecord domainRecord);

}

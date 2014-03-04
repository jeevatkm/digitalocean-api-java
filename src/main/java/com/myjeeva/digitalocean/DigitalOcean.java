/* The MIT License
 *
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
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
package com.myjeeva.digitalocean;

import java.util.List;

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
 * <p>
 * <strong>DigitalOcean API client written in Java</strong>
 * </p>
 * 
 * <p>
 * A simple and meaningful wrapper methods for <a
 * href="https://api.digitalocean.com/"
 * title="DigitalOcean's API">DigitalOcean's API</a>. All of the RESTful that
 * you find in DigitalOcean API's will be made available via simple java
 * methods.
 * </p>
 * 
 * <p>
 * <strong>Sample Code:</strong><br/>
 * 
 * <pre>
 * // Create a DigitalOcean client
 * DigitalOcean apiClient = new DigitalOceanClient(clientId, apiKey);
 * 
 * // Let's invoke the appropriate method as per need
 * // Fetching all the available droplets from control panel 
 * List&lt;Droplet> droplets = apiClient.getAvailableDroplets();
 * 
 * // Create a new droplet
 * Droplet newDroplet = new Droplet();
 * newDroplet.setName("api-cliet-test-host");
 * newDroplet.setSizeId(66); // 66 => 512MB plan
 * newDroplet.setRegionId(3); // 3 => San Francisco 1 Data center
 * newDroplet.setImageId(473123); // 473123 => Ubuntu 12.10 x64 Image
 * Droplet droplet = apiClient.createDroplet(newDroplet); 
 * 
 * // Fetch droplet information 
 * Droplet droplet = apiClient.getDropletInfo(dropletId);
 * 
 * // Fetch Available Plans/Sizes supported by DigitalOcean
 * List&lt;DropletSize> sizes = apiClient.getAvailableSizes();
 * 
 * and so on... simple to use and effective!
 * </pre>
 * 
 * </p>
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public interface DigitalOcean {

	/*
	 * Droplet manipulation methods
	 */

	/**
	 * Method returns all active droplets that are currently running in your
	 * account. All available API information is presented for each droplet.
	 * 
	 * @return List&lt;Droplet>
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	List<Droplet> getAvailableDroplets() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * <p>
	 * Method allows you to create a new droplet. See the required parameters
	 * section below for an explanation of the variables that are needed to
	 * create a new droplet.
	 * </p>
	 * 
	 * <p>
	 * Create a instance of {@link Droplet} object and populate following values
	 * </p>
	 * <ul>
	 * <li>Name Required, String, this is the name of the droplet must be
	 * formatted by hostname rules</li>
	 * <li>Side Id Required, Numeric, this is the id of the size you would like
	 * the droplet created at</li>
	 * <li>Image Id Required, Numeric, this is the id of the image you would
	 * like the droplet created with</li>
	 * <li>Region Id Required, Numeric, this is the id of the region you would
	 * like your server in</li>
	 * </ul>
	 * 
	 * @param droplet
	 *            the id of the droplet
	 * @return {@link Droplet}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Droplet createDroplet(Droplet droplet) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * <p>
	 * Method allows you to create a new droplet. See the required parameters
	 * section below for an explanation of the variables that are needed to
	 * create a new droplet.
	 * </p>
	 * 
	 * <p>
	 * Create a instance of {@link Droplet} object and populate following values
	 * </p>
	 * <ul>
	 * <li>Name Required, String, this is the name of the droplet must be
	 * formatted by hostname rules</li>
	 * <li>Side Id Required, Numeric, this is the id of the size you would like
	 * the droplet created at</li>
	 * <li>Image Id Required, Numeric, this is the id of the image you would
	 * like the droplet created with</li>
	 * <li>Region Id Required, Numeric, this is the id of the region you would
	 * like your server in</li>
	 * </ul>
	 * 
	 * @param droplet
	 *            the id of the droplet
	 * @param sshKeyIds
	 *            Numeric CSV, comma separated list of ssh_key_ids that you
	 *            would like to be added to the server
	 * @return {@link Droplet}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Droplet createDroplet(Droplet droplet, String sshKeyIds)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method returns full information for a specific droplet ID that is passed
	 * in the URL.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Droplet}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Droplet getDropletInfo(Integer dropletId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method allows you to reboot a droplet. This is the preferred method to
	 * use if a server is not responding.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response rebootDroplet(Integer dropletId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method allows you to power cycle a droplet. This will turn off the
	 * droplet and then turn it back on.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response powerCyleDroplet(Integer dropletId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method allows you to shutdown a running droplet. The droplet will remain
	 * in your account.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response shutdownDroplet(Integer dropletId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method allows you to poweroff a running droplet. The droplet will remain
	 * in your account.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response powerOffDroplet(Integer dropletId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method allows you to poweron a powered off droplet.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response powerOnDroplet(Integer dropletId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method will reset the root password for a droplet. Please be aware that
	 * this will reboot the droplet to allow resetting the password.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response resetDropletPassword(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method allows you to resize a specific droplet to a different size. This
	 * will affect the number of processors and memory allocated to the droplet.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response resizeDroplet(Integer dropletId, Integer sizeId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method allows you to take a snapshot of the running droplet, which can
	 * later be restored or used to create a new droplet from the same image.
	 * Please be aware this may cause a reboot.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response takeDropletSnapshot(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method allows you to take a snapshot of the running droplet, which can
	 * later be restored or used to create a new droplet from the same image.
	 * Please be aware this may cause a reboot.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @param snapshotName
	 *            the name the snapshot to be created
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response takeDropletSnapshot(Integer dropletId, String snapshotName)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method allows you to restore a droplet with a previous image or snapshot.
	 * This will be a mirror copy of the image or snapshot to your droplet. Be
	 * sure you have backed up any necessary information prior to restore.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response restoreDroplet(Integer dropletId, Integer imageId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method allows you to reinstall a droplet with a default image. This is
	 * useful if you want to start again but retain the same IP address for your
	 * droplet.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response rebuildDroplet(Integer dropletId, Integer imageId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method enables automatic backups which run in the background daily to
	 * backup your droplet's data.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response enableDropletBackups(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method disables automatic backups from running to backup your droplet's
	 * data.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response disableDropletBackups(Integer dropletId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method renames the droplet to the specified name.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response renameDroplet(Integer dropletId, String name)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method destroys one of your droplets this is irreversible.
	 * 
	 * @param dropletId
	 *            the id of the droplet
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response deleteDroplet(Integer dropletId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/*
	 * Regions (aka Data Centers) methods
	 */
	/**
	 * Method will return all the available regions within the DigitalOcean
	 * cloud.
	 * 
	 * @return List&ltRegion>
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	List<Region> getAvailableRegions() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/*
	 * Images manipulation (aka Distribution) methods
	 */
	/**
	 * Method returns all the available images that can be accessed by your
	 * client ID. You will have access to all public images by default, and any
	 * snapshots or backups that you have created in your own account.
	 * 
	 * @return List&ltDropletImage>
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	List<DropletImage> getAvailableImages() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method retrieves the attributes of an image.
	 * 
	 * @param imageId
	 *            the image Id of the droplet/snapshot/backup images
	 * @return {@link DropletImage}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	DropletImage getImageInfo(Integer imageId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method allows you to deletes an image. There is no way to restore a
	 * deleted image so be careful and ensure your data is properly backed up.
	 * 
	 * @param imageId
	 *            the image Id of the droplet/snapshot/backup images
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response deleteImage(Integer imageId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method allows you to transfer an image to a specified region.
	 * 
	 * @param imageId
	 *            the image Id of the droplet/snapshot/backup images
	 * @param regionId
	 *            the region Id of the digitalocean data centers
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	Response transferImage(Integer imageId, Integer regionId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/*
	 * SSH Key manipulation methods
	 */
	/**
	 * Method lists all the available public SSH keys in your account that can
	 * be added to a droplet.
	 * 
	 * @return <code>List&lt;SshKey></code>
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.2
	 */
	List<SshKey> getAvailableSshKeys() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method shows a specific public SSH key in your account that can be added
	 * to a droplet.
	 * 
	 * @param sshKeyId
	 *            the SSH key Id
	 * @return {@link SshKey}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.2
	 */
	SshKey getSshKeyInfo(Integer sshKeyId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method allows you to add a new public SSH key to your account
	 * 
	 * @param sshKeyName
	 *            the name you want to give this SSH key
	 * @param sshPublicKey
	 *            the actual public SSH key
	 * @return {@link SshKey}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.2
	 */
	SshKey addSshKey(String sshKeyName, String sshPublicKey)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method allows you to modify an existing public SSH key in your account.
	 * 
	 * @param sshKeyId
	 *            the SSH key Id, you would like to edit
	 * @param newSshPublicKey
	 *            the new public SSH key
	 * @return {@link SshKey}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.2
	 */
	SshKey editSshKey(Integer sshKeyId, String newSshPublicKey)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method will delete the SSH key from your account.
	 * 
	 * @param sshKeyId
	 *            the SSH key Id, you would like to delete
	 * @return {@link Response}
	 * @throws RequestUnsuccessfulException
	 * @throws ResourceNotFoundException
	 * @throws AccessDeniedException
	 * 
	 * @since v1.2
	 */
	Response deleteSshKey(Integer sshKeyId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/*
	 * Sizes (aka Available Droplet Plans) methods
	 */
	/**
	 * Method returns all the available sizes that can be used to create a
	 * droplet.
	 * 
	 * @return List&lt;DropletSize>
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.0
	 */
	List<DropletSize> getAvailableSizes() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/*
	 * Domain manipulation methods
	 */
	/**
	 * Method returns all of your available domains from DNS control panel
	 * 
	 * @return <code>List&lt;Domain></code>
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.1
	 */
	List<Domain> getAvailableDomains() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method creates a new domain name with an A record for the specified
	 * [ip_address].
	 * 
	 * @param domainName
	 *            the name of the domain
	 * @param ipAddress
	 *            the IP Address for the domain
	 * @return {@link Domain}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.1
	 */
	Domain createDomain(String domainName, String ipAddress)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method returns the specified domain attributes and zone file info.
	 * 
	 * @param domainId
	 *            the Id of the domain
	 * @return {@link Domain}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.1
	 */
	Domain getDomainInfo(Integer domainId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method deletes the specified domain from DNS control panel
	 * 
	 * @param domainId
	 *            the Id of the domain
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.1
	 */
	Response deleteDomain(Integer domainId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

	/**
	 * Method returns all of your current domain records from DNS control panel
	 * for given domain.
	 * 
	 * @param domainId
	 *            the Id of the domain
	 * @return <code>List&lt;DomainRecord></code>
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.1
	 */
	List<DomainRecord> getDomainRecords(Integer domainId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method creates a new domain record name with an given domain record
	 * values
	 * 
	 * @param domainRecord
	 *            the domain record values domain Id, record type, data, name,
	 *            priority, port, weight
	 * @return {@link DomainRecord}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.1
	 */
	DomainRecord createDomainRecord(DomainRecord domainRecord)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method returns the specified domain record.
	 * 
	 * @param domainId
	 *            the Id of the domain
	 * @param recordId
	 *            the record Id of the domain
	 * @return {@link DomainRecord}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.1
	 */
	DomainRecord getDomainRecordInfo(Integer domainId, Integer recordId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * method edits an existing domain record of the given domain.
	 * 
	 * @param domainRecord
	 *            the domain record values domain Id, record type, data, name,
	 *            priority, port, weight
	 * @return {@link DomainRecord}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 * 
	 * @since v1.1
	 */
	DomainRecord editDomainRecord(DomainRecord domainRecord)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/**
	 * Method deletes the specified domain record from domain.
	 * 
	 * @throws RequestUnsuccessfulException
	 * @throws ResourceNotFoundException
	 * @throws AccessDeniedException
	 * 
	 * @since v1.1
	 */
	Response deleteDomainRecord(Integer domainId, Integer recordId)
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException;

	/*
	 * Events status methods
	 */
	/**
	 * Method is primarily used to collect progress of an event and determined
	 * by percentage of completion.
	 * 
	 * @param eventId
	 *            this is event id of the event you would like to get more
	 *            information
	 * @return {@link Response}
	 * @throws AccessDeniedException
	 * @throws ResourceNotFoundException
	 * @throws RequestUnsuccessfulException
	 */
	Response getEventProgress(Integer eventId) throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException;

}

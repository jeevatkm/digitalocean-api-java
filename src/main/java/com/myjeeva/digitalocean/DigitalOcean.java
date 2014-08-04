/*
 * The MIT License
 * 
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.myjeeva.digitalocean;

import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.pojo.Action;
import com.myjeeva.digitalocean.pojo.Actions;
import com.myjeeva.digitalocean.pojo.Backups;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.Domains;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.Images;
import com.myjeeva.digitalocean.pojo.Kernels;
import com.myjeeva.digitalocean.pojo.Regions;
import com.myjeeva.digitalocean.pojo.Sizes;
import com.myjeeva.digitalocean.pojo.Snapshots;

/**
 * <p>
 * <strong>DigitalOcean API client in Java</strong>
 * </p>
 * 
 * <p>
 * A simple and meaningful wrapper methods for <a href="https://api.digitalocean.com/"
 * title="DigitalOcean's API">DigitalOcean's API</a>. All of the RESTful that you find in
 * DigitalOcean API's will be made available via simple java methods.
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

  // ===========================================
  // Droplets methods
  // ===========================================

  /**
   * Method returns all active droplets that are currently running in your account. All available
   * API information is presented for each droplet.
   * 
   * @param pageNo of request pagination
   * @return {@link Droplets}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   **/
  Droplets getAvailableDroplets(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;

  Kernels getAvailableKernels(Integer dropletId, Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;

  Snapshots getAvailableSnapshots(Integer dropletId, Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;

  Backups getAvailableBackups(Integer dropletId, Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;

  Actions getAvailableActions(Integer dropletId, Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;

  /**
   * Method returns full information for a specific droplet ID that is passed in the URL.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Droplet}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Droplet getDropletInfo(Integer dropletId) throws DigitalOceanException,
      RequestUnsuccessfulException;

  /**
   * <p>
   * Method allows you to create a new droplet. See the required parameters section below for an
   * explanation of the variables that are needed to create a new droplet.
   * </p>
   * 
   * <p>
   * Create a instance of {@link Droplet} object and populated the droplet object appropriately.
   * </p>
   * <p>
   * Minimum required values are -
   * </p>
   * 
   * <pre>
   * {
   *   "name": "example-droplet-name",
   *   "region": "nyc1",
   *   "size": "512mb",
   *   "image": 3445812
   * }
   * </pre>
   * 
   * @param droplet the id of the droplet
   * @return {@link Droplet}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Droplet createDroplet(Droplet droplet) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method destroys one of your droplets; this is irreversible.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Boolean}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Boolean deleteDroplet(Integer dropletId) throws DigitalOceanException,
      RequestUnsuccessfulException;

  // Droplet Action methods

  /**
   * Method allows you to reboot a droplet. This is the preferred method to use if a server is not
   * responding.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Action rebootDroplet(Integer dropletId) throws DigitalOceanException,
      RequestUnsuccessfulException;

  /**
   * Method allows you to power cycle a droplet. This will turn off the droplet and then turn it
   * back on.
   * 
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Action powerCycleDroplet(Integer dropletId) throws DigitalOceanException,
      RequestUnsuccessfulException;



  // ==============================================
  // Images manipulation (aka Distribution) methods
  // ==============================================
  /**
   * Method returns all the available images that can be accessed by your client ID. You will have
   * access to all public images by default, and any snapshots or backups that you have created in
   * your own account.
   * 
   * @param pageNo of request pagination
   * @return Images
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Images getAvailableImages(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;

  /**
   * Method retrieves the attributes of an image.
   * 
   * @param imageId the image Id of the droplet/snapshot/backup images
   * @return {@link Image}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Image getImageInfo(Integer imageId) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method retrieves the attributes of an image.
   * 
   * @param slug of the public image
   * @return {@link Image}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Image getImageInfo(String slug) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method updates the given details for an image.
   * 
   * @param image object for update
   * @return {@link Image}
   * @throws DigitalOceanException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Image updateImage(Image image) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to deletes an image. There is no way to restore a deleted image so be careful
   * and ensure your data is properly backed up.
   * 
   * @param imageId of the droplet/snapshot/backup images
   * @return {@link Boolean}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Boolean deleteImage(Integer imageId) throws DigitalOceanException, RequestUnsuccessfulException;


  // ===========================================
  // Regions (aka Data Centers) methods
  // ===========================================
  /**
   * Method will return all the available regions within the DigitalOcean cloud.
   * 
   * @param pageNo of request pagination
   * @return {@link Regions}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Regions getAvailableRegions(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;


  // ===========================================
  // Sizes (aka Available Droplet Plans) methods
  // ===========================================
  /**
   * Method returns all the available sizes that can be used to create a droplet.
   * 
   * @param pageNo of request pagination
   * @return {@link Sizes}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v1.0
   */
  Sizes getAvailableSizes(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;


  // ===========================================
  // Domain manipulation methods
  // ===========================================
  /**
   * Method returns all of your available domains from DNS control panel
   * 
   * @param pageNo of request pagination
   * @return {@link Domains}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Domains getAvailableDomains(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException;

  /**
   * Method returns the specified domain attributes and zone file info.
   * 
   * @param domainName the name of the domain
   * @return {@link Domain}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Domain getDomainInfo(String domainName) throws DigitalOceanException,
      RequestUnsuccessfulException;

  /**
   * Method creates a new domain name with an A record for the specified [ip_address].
   * 
   * @param domain object with name and IP address for creation
   * @return {@link Domain}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Domain createDomain(Domain domain) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method deletes the specified domain from DNS control panel
   * 
   * @param domainName the name of the domain
   * @return {@link Boolean}
   * @throws DigitalOceanException
   * @throws ResourceNotFoundException
   * @throws RequestUnsuccessfulException
   * 
   * @since v2.0
   */
  Boolean deleteDomain(String domainName) throws DigitalOceanException,
      RequestUnsuccessfulException;


}

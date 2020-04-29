/**
 * The MIT License
 *
 * <p>Copyright (c) 2013-2020 Jeevanandam M. (jeeva@myjeeva.com)
 *
 * <p>Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * <p>The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * <p>THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.myjeeva.digitalocean;

import com.myjeeva.digitalocean.common.ActionType;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.pojo.Account;
import com.myjeeva.digitalocean.pojo.Action;
import com.myjeeva.digitalocean.pojo.Actions;
import com.myjeeva.digitalocean.pojo.Backups;
import com.myjeeva.digitalocean.pojo.Certificate;
import com.myjeeva.digitalocean.pojo.Certificates;
import com.myjeeva.digitalocean.pojo.Delete;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.DomainRecords;
import com.myjeeva.digitalocean.pojo.Domains;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Firewall;
import com.myjeeva.digitalocean.pojo.Firewalls;
import com.myjeeva.digitalocean.pojo.FloatingIP;
import com.myjeeva.digitalocean.pojo.FloatingIPs;
import com.myjeeva.digitalocean.pojo.ForwardingRules;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.Images;
import com.myjeeva.digitalocean.pojo.Kernels;
import com.myjeeva.digitalocean.pojo.Key;
import com.myjeeva.digitalocean.pojo.Keys;
import com.myjeeva.digitalocean.pojo.LoadBalancer;
import com.myjeeva.digitalocean.pojo.LoadBalancers;
import com.myjeeva.digitalocean.pojo.Neighbors;
import com.myjeeva.digitalocean.pojo.Project;
import com.myjeeva.digitalocean.pojo.Projects;
import com.myjeeva.digitalocean.pojo.Regions;
import com.myjeeva.digitalocean.pojo.Resource;
import com.myjeeva.digitalocean.pojo.Response;
import com.myjeeva.digitalocean.pojo.Sizes;
import com.myjeeva.digitalocean.pojo.Snapshot;
import com.myjeeva.digitalocean.pojo.Snapshots;
import com.myjeeva.digitalocean.pojo.Tag;
import com.myjeeva.digitalocean.pojo.Tags;
import com.myjeeva.digitalocean.pojo.Volume;
import com.myjeeva.digitalocean.pojo.Volumes;
import java.util.List;

/**
 * <strong>DigitalOcean API client in Java</strong>
 *
 * <p>A simple and meaningful java methods for <a
 * href="https://api.digitalocean.com/">DigitalOcean's API</a>. All of the RESTful that you find in
 * DigitalOcean API's Version 2 is available via simple java methods.
 *
 * <p><strong>Sample Code:</strong>
 *
 * <pre>
 * // Create a DigitalOcean client
 * DigitalOcean apiClient = new DigitalOceanClient(authToken);
 * <code>or</code>
 * DigitalOcean apiClient = new DigitalOceanClient("v2", authToken);
 *
 * <strong>Let's invoke the appropriate method as per need</strong>
 *
 * // Fetching all the available droplets from control panel
 * Droplets droplets = apiClient.getAvailableDroplets(pageNo);
 *
 * // Fetching all the available kernels for droplet
 * Kernels kernels = apiClient.getAvailableKernels(dropletId, pageNo);
 *
 * // Create a new droplet
 * Droplet newDroplet = new Droplet();
 * newDroplet.setName("api-client-test-host");
 * newDroplet.setSize(new Size("512mb")); // setting size by slug value
 * newDroplet.setRegion(new Region("sgp1")); // setting region by slug value; sgp1 =&gt; Singapore 1 Data center
 * newDroplet.setImage(new Image(1601)); // setting by Image Id 1601 =&gt; centos-5-8-x64 also available in image slug value
 * newDroplet.setEnableBackup(Boolean.TRUE);
 * newDroplet.setEnableIpv6(Boolean.TRUE);
 * newDroplet.setEnablePrivateNetworking(Boolean.TRUE);
 * Droplet droplet = apiClient.createDroplet(newDroplet);
 *
 * // Fetch droplet information
 * Droplet droplet = apiClient.getDropletInfo(dropletId);
 *
 * // Fetch Available Plans/Sizes supported by DigitalOcean
 * Sizes sizes = apiClient.getAvailableSizes(pageNo);
 *
 * // Fetch Available Regions supported by DigitalOcean
 * Sizes sizes = apiClient.getAvailableRegions(pageNo);
 *
 * and so on... simple to use and effective!
 * </pre>
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v1.0
 */
public interface DigitalOcean {

  // ===========================================
  // Droplets methods
  // ===========================================

  /**
   * Method returns all active droplets that are currently running in your account. All available
   * API information is presented for each droplet.
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Droplets}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Droplets getAvailableDroplets(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns all available kernels for given droplet ID
   *
   * @param dropletId for kernel info
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Kernels}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Kernels getDropletKernels(Integer dropletId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns all available snapshots for given droplet ID
   *
   * @param dropletId for snapshot info
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Snapshots}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Snapshots getDropletSnapshots(Integer dropletId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns all available snapshots for given droplet ID
   *
   * @param dropletId for backup info
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Backups}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Backups getDropletBackups(Integer dropletId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns complete information for given droplet ID
   *
   * @param dropletId the id of the droplet
   * @return {@link Droplet}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Droplet getDropletInfo(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to create a new droplet. See the required parameters section below for an
   * explanation of the variables that are needed to create a new droplet.
   *
   * <p><strong>Note:</strong> Currently return object doesn't include 'action' information of
   * create droplet.
   *
   * <p>Create a instance of {@link Droplet} class and populated the droplet object appropriately.
   * Minimum required values are -
   *
   * <pre>
   * {
   *   "name": "example-droplet-name",
   *   "region": "nyc1",
   *   "size": "512mb",
   *   "image": "ubuntu-14-04-x64",
   *   "backups": false
   * }
   * </pre>
   *
   * @param droplet the instance of the droplet class
   * @return {@link Droplet}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Droplet createDroplet(Droplet droplet) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to create multiple droplets simultaneously. See the required parameters
   * section below for an explanation of the variables that are needed to create multiple droplets.
   *
   * <p>Create a instance of {@link Droplet} class and populated the droplet object appropriately.
   * Particularly <strong>names</strong> attribute in the Droplet class. Minimum required values are
   * -
   *
   * <pre>
   * {
   *   "names": [
   *       "sub-01.example.com",
   *       "sub-02.example.com"
   *   ],
   *   "region": "nyc1",
   *   "size": "512mb",
   *   "image": "ubuntu-14-04-x64",
   *   "backups": false
   * }
   * </pre>
   *
   * @param droplet the instance of the droplet class
   * @return {@link Droplet}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  Droplets createDroplets(Droplet droplet)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method destroys one of your droplet; this is irreversible.
   *
   * @param dropletId the id of the droplet
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Delete deleteDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method destroys one or more of your droplet by given tag name; this is irreversible.
   *
   * @param tagName the associated tag name with droplet
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.9
   */
  Delete deleteDropletByTagName(String tagName)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * For an individual droplet; Method retrieves a list of droplets that are running on the same
   * physical server (any other droplets that share the same physical hardware).
   *
   * @param dropletId the id of the droplet
   * @param pageNo for pagination
   * @return {@link Droplets}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Droplets getDropletNeighbors(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * For an entire account; Method retrieves a list of <strong>any</strong> droplets that are
   * running on the same physical server (any other droplets that share the same physical hardware).
   *
   * @param pageNo for pagination
   * @return {@link Neighbors}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Neighbors getAllDropletNeighbors(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * To list Droplets by a tag.
   *
   * <p>The response will match that of regular droplet listing request but will be filtered to only
   * include the tagged Droplets.
   *
   * @param tagName for tagName
   * @param pageNo for pagination
   * @param perPage for pagination
   * @return {@link Droplets}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.16
   */
  Droplets getAvailableDropletsByTagName(String tagName, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Droplet Action methods
  // ===========================================

  /**
   * Method allows you to reboot a droplet. This is the preferred method to use if a server is not
   * responding.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action rebootDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to power cycle a droplet. This will turn off the droplet and then turn it
   * back on.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action powerCycleDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to shutdown a running droplet.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action shutdownDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to poweroff a running droplet.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action powerOffDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to poweron a powered off droplet.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action powerOnDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method will reset the root password for a droplet. Please be aware that this will reboot the
   * droplet to allow resetting the password.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action resetDropletPassword(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to resize a specific droplet to a different size. This will affect the number
   * of processors and memory allocated to the droplet.
   *
   * @param dropletId the id of the droplet
   * @param size of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action resizeDroplet(Integer dropletId, String size)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to resize a specific droplet to a different size. This will affect the number
   * of processors and memory allocated to the droplet and optionally disk size.
   *
   * @param dropletId the id of the droplet
   * @param size of the droplet
   * @param disk whether to resize disk of the droplet or or not
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action resizeDroplet(Integer dropletId, String size, Boolean disk)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to take a snapshot of the running droplet, which can later be restored or
   * used to create a new droplet from the same image. Please be aware this may cause a reboot.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action takeDropletSnapshot(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to take a snapshot of the running droplet, which can later be restored or
   * used to create a new droplet from the same image. Please be aware this may cause a reboot.
   *
   * @param dropletId the id of the droplet
   * @param snapshotName the name the snapshot to be created
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action takeDropletSnapshot(Integer dropletId, String snapshotName)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to restore a droplet with a previous image or snapshot. This will be a mirror
   * copy of the image or snapshot to your droplet. Be sure you have backed up any necessary
   * information prior to restore.
   *
   * @param dropletId the id of the droplet
   * @param imageId the id of the DigitalOcean public image or your private image
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action restoreDroplet(Integer dropletId, Integer imageId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to reinstall a droplet with a default image. This is useful if you want to
   * start again but retain the same IP address for your droplet.
   *
   * @param dropletId the id of the droplet
   * @param imageId the id of the DigitalOcean public image or your private image
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action rebuildDroplet(Integer dropletId, Integer imageId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method enables automatic backups for your droplet's data.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  Action enableDropletBackups(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method disables automatic backups from running to backup your droplet's data.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action disableDropletBackups(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method renames the droplet to the specified name.
   *
   * @param dropletId the id of the droplet
   * @param name the new name of droplet to be called
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action renameDroplet(Integer dropletId, String name)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method changes a OS kernel for given droplet
   *
   * @param dropletId the id of the droplet
   * @param kernelId the kernel id to be changed for droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Action changeDropletKernel(Integer dropletId, Integer kernelId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Enabling IP v6 networking capability for droplet. It may be dependent on Data Center Features.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   */
  Action enableDropletIpv6(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Enabling private networking capability for droplet. It may be dependent on Data Center
   * Features.
   *
   * @param dropletId the id of the droplet
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Action enableDropletPrivateNetworking(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ==============================================
  // Account manipulation/access methods
  // ==============================================

  /**
   * Method returns account information for provided credentials
   *
   * @return ${@link Account}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   */
  Account getAccountInfo() throws DigitalOceanException, RequestUnsuccessfulException;

  // ==============================================
  // Actions manipulation/access methods
  // ==============================================

  /**
   * Method return all the action informations, regardless of categories.
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Actions}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Actions getAvailableActions(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * To retrieve a specific action information by action ID
   *
   * @param actionId the id of action
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Action getActionInfo(Integer actionId) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method return all the action informations; specific to given Droplet Id
   *
   * @param dropletId the id of the droplet
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Actions}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Actions getAvailableDropletActions(Integer dropletId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method return all the action informations; specific to given Image Id
   *
   * @param imageId the id of the Image
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Actions}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Actions getAvailableImageActions(Integer imageId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method retrives all actions that have been executed on a Floating IP address.
   *
   * @param ipAddress Floating IP address
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Actions}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  Actions getAvailableFloatingIPActions(String ipAddress, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method to retrieve the status of a Floating IP action.
   *
   * @param ipAddress Floating IP address
   * @param actionId the id of action
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  Action getFloatingIPActionInfo(String ipAddress, Integer actionId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ==============================================
  // Images manipulation (aka Distribution) methods
  // ==============================================

  /**
   * Method returns all the available images that can be accessed by your OAuth Token. You will have
   * access to all public images by default, and any snapshots or backups that you have created in
   * your own account.
   *
   * @param pageNo of request pagination
   * @param perPage no. of items per page
   * @return {@link Images}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Images getAvailableImages(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns all the available images based on <code>type={distribution or application}
   * </code> that can be accessed by your OAuth Token.
   *
   * @param pageNo of request pagination
   * @param perPage no. of items per page
   * @param type of action
   * @return {@link Images}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Images getAvailableImages(Integer pageNo, Integer perPage, ActionType type)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method retrieves only the private images of a user
   *
   * @param pageNo of request pagination
   * @param perPage no. of items per page
   * @return {@link Images}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   */
  Images getUserImages(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method retrieves the attributes of an image.
   *
   * @param imageId the image Id of the droplet/snapshot/backup images
   * @return {@link Image}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Image getImageInfo(Integer imageId) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method retrieves the attributes of an image.
   *
   * @param slug of the public image
   * @return {@link Image}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Image getImageInfo(String slug) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method creates the custom image.
   *
   * <p>Refer to <a
   * href="https://developers.digitalocean.com/documentation/v2/#create-a-custom-image">
   * https://developers.digitalocean.com/documentation/v2/#create-a-custom-image</a>
   *
   * <p>Sample Payload:
   *
   * <pre>
   * {
   *   "name": "ubuntu-18.04-minimal",
   *   "url": "http://cloud-images.ubuntu.com/minimal/releases/bionic/release/ubuntu-18.04-minimal-cloudimg-amd64.img",
   *   "distribution": "Ubuntu",
   *   "region": "nyc3",
   *   "description": "Cloud-optimized image w/ small footprint",
   *   "tags": [
   *     "base-image",
   *     "prod"
   *   ]
   * }
   * </pre>
   *
   * @param image instance with custom image attributes
   * @return {@link Image}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.17
   */
  Image createCustomImage(Image image) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method updates the given details for an image.
   *
   * @param image object for update
   * @return {@link Image}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Image updateImage(Image image) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to deletes an image. There is no way to restore a deleted image so be careful
   * and ensure your data is properly backed up.
   *
   * @param imageId of the droplet/snapshot/backup images
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Delete deleteImage(Integer imageId) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to transfer an image to a specified region.
   *
   * @param imageId the Id of the droplet/snapshot/backup images
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Action transferImage(Integer imageId, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to convert image into snapshot
   *
   * @param imageId the Id of the droplet/snapshot/backup images
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Action convertImage(Integer imageId) throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Regions (aka Data Centers) methods
  // ===========================================

  /**
   * Method returns all the available regions within the DigitalOcean cloud.
   *
   * @param pageNo for pagination
   * @return {@link Regions}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Regions getAvailableRegions(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Sizes (aka Available Droplet Plans) methods
  // ===========================================

  /**
   * Method returns all the available sizes that can be used to create a droplet.
   *
   * @param pageNo for pagination
   * @return {@link Sizes}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Sizes getAvailableSizes(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Domain manipulation methods
  // ===========================================

  /**
   * Method returns all of your available domains from DNS control panel
   *
   * @param pageNo for pagination
   * @return {@link Domains}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Domains getAvailableDomains(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns the specified domain attributes and zone file info.
   *
   * @param domainName the name of the domain
   * @return {@link Domain}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Domain getDomainInfo(String domainName)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method creates a new domain name with an A record for the specified [ip_address].
   *
   * @param domain object with name and IP address for creation
   * @return {@link Domain}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Domain createDomain(Domain domain) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method deletes the specified domain from DNS control panel
   *
   * @param domainName the name of the domain
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.0
   */
  Delete deleteDomain(String domainName) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns all of your current domain records from DNS control panel for given domain.
   *
   * @param domainName of the domain
   * @param pageNo of request pagination
   * @param perPage no. of items per page
   * @return {@link DomainRecords}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.1
   */
  DomainRecords getDomainRecords(String domainName, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method creates a new domain record name with an given domain record values
   *
   * @param domainName of the domain
   * @param domainRecord the domain record values domain Id, record type, data, name, priority,
   *     port, weight
   * @return {@link DomainRecord}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.1
   */
  DomainRecord createDomainRecord(String domainName, DomainRecord domainRecord)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns the specified domain record.
   *
   * @param domainName of the domain
   * @param recordId of the domain
   * @return {@link DomainRecord}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.1
   */
  DomainRecord getDomainRecordInfo(String domainName, Integer recordId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * method edits an existing domain record of the given domain.
   *
   * @param domainName of the domain
   * @param recordId of the domain
   * @param domainRecord the domain record values domain Id, record type, data, name, priority,
   *     port, weight
   * @return {@link DomainRecord}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  DomainRecord updateDomainRecord(String domainName, Integer recordId, DomainRecord domainRecord)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method deletes the specified domain record from domain.
   *
   * @param domainName of the domain
   * @param recordId of the domain
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.1
   */
  Delete deleteDomainRecord(String domainName, Integer recordId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // SSH Key manipulation methods
  // ===========================================

  /**
   * Method lists all the available public SSH keys in your account that can be added to a droplet.
   *
   * @param pageNo for pagination
   * @return {@link Keys}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.2
   */
  Keys getAvailableKeys(Integer pageNo) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method shows a specific public SSH key information from your account that can be added to a
   * droplet.
   *
   * @param sshKeyId the SSH key Id
   * @return {@link Key}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.2
   */
  Key getKeyInfo(Integer sshKeyId) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method shows a specific public SSH key information from your account that can be added to a
   * droplet.
   *
   * @param fingerprint the SSH key fingerprint
   * @return {@link Key}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Key getKeyInfo(String fingerprint) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to add a new public SSH key to your account
   *
   * @param newKey the {@link Key} object with sshKeyName and sshPublicKey
   * @return {@link Key}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.2
   */
  Key createKey(Key newKey) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to modify an existing SSH key in your account.
   *
   * @param sshKeyId the SSH key Id
   * @param newSshKeyName the new name to give the SSH key
   * @return {@link Key}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.2
   */
  Key updateKey(Integer sshKeyId, String newSshKeyName)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to modify an existing SSH key in your account.
   *
   * @param fingerprint the SSH fingerprint
   * @param newSshKeyName the new name to give the SSH key
   * @return {@link Key}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Key updateKey(String fingerprint, String newSshKeyName)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method will delete the SSH key from your account.
   *
   * @param sshKeyId the SSH key Id, you would like to delete
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v1.2
   */
  Delete deleteKey(Integer sshKeyId) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method will delete the SSH key from your account.
   *
   * @param fingerprint the SSH fingerprint
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.0
   */
  Delete deleteKey(String fingerprint) throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Floating IP manipulation methods
  // ===========================================

  /**
   * Method will list all of the Floating IPs available from your account.
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link FloatingIPs}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  FloatingIPs getAvailableFloatingIPs(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method creates a new Floating IP and assigns to the Droplet.
   *
   * @param dropletId the id of the droplet
   * @return {@link FloatingIP}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  FloatingIP createFloatingIP(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method creates a new Floating IP and its reserved to a Region
   *
   * @param region name of the DigitalOcean region
   * @return {@link FloatingIP}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  FloatingIP createFloatingIP(String region)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method retrieves the information about given Floating IP
   *
   * @param ipAddress Floating IP address
   * @return {@link FloatingIP}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  FloatingIP getFloatingIPInfo(String ipAddress)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method deletes the Floating IP and removes it from your account.
   *
   * @param ipAddress Floating IP address
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  Delete deleteFloatingIP(String ipAddress)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method will assign Floating IP to a Droplet.
   *
   * @param dropletId the id of the droplet
   * @param ipAddress Floating IP address
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  Action assignFloatingIP(Integer dropletId, String ipAddress)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method will unassign Floating IP from a Droplet. The Floating IP will be reserved in the region
   * but not assigned to a Droplet.
   *
   * @param ipAddress Floating IP address
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.3
   */
  Action unassignFloatingIP(String ipAddress)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Tags manipulation methods
  // ===========================================
  /**
   * Method will list all of the Tags available from your account.
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Tags}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.5
   */
  Tags getAvailableTags(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method will create a Tag on DigitalOcean aacount.
   *
   * @param name Tag Name
   * @return {@link Tag}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.5
   */
  Tag createTag(String name) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method retrieves the Tag from DigitalOcean account.
   *
   * @param name Tag Name
   * @return {@link Tag}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.5
   */
  Tag getTag(String name) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method deletes the existing Tag from DigitalOcean account.
   *
   * @param name Tag Name
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.5
   */
  Delete deleteTag(String name) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method tags a tag to given list of resources on DigitalOcean account.
   *
   * @param name Tag Name
   * @param resources list of resource by {@link Resource} objects
   * @return {@link Response}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.5
   */
  Response tagResources(String name, List<Resource> resources)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method untags a tag from given list of resources on DigitalOcean account.
   *
   * @param name Tag Name
   * @param resources list of resource by {@link Resource} objects
   * @return {@link Response}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.5
   */
  Response untagResources(String name, List<Resource> resources)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Volumes manipulation methods
  // ===========================================

  /**
   * Method will list all of the Volumes available from your account.
   *
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @return {@link Volumes}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Volumes getAvailableVolumes(String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method creates new volume with given details
   *
   * @param volume details to create new volume
   * @return {@link Volume}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Volume createVolume(Volume volume) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method shows specific volume information by identifier
   *
   * @param volumeId volume identifier
   * @return {@link Volume}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Volume getVolumeInfo(String volumeId) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method shows volume information by name and region slug
   *
   * @param volumeName name of the volume
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @return {@link Volumes}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Volumes getVolumeInfo(String volumeName, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method deletes volume by identifier
   *
   * @param volumeId volume identifier
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Delete deleteVolume(String volumeId) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method deletes volume by name and region slug
   *
   * @param volumeName name of the volume
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Delete deleteVolume(String volumeName, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method attaches the given volume into droplet by identifier
   *
   * @param dropletId droplet identifier
   * @param volumeId volume identifier
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   */
  Action attachVolume(Integer dropletId, String volumeId, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method attaches the given volume into droplet by name
   *
   * @param dropletId droplet identifier
   * @param volumeName name of the volume
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Action attachVolumeByName(Integer dropletId, String volumeName, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method detaches volume from the droplet by identifier
   *
   * @param dropletId droplet identifier
   * @param volumeId volume identifier
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Action detachVolume(Integer dropletId, String volumeId, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method detaches volume from the droplet by volume name
   *
   * @param dropletId droplet identifier
   * @param volumeName name of the volume
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Action detachVolumeByName(Integer dropletId, String volumeName, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method resizes volume by identifier
   *
   * @param volumeId volume identifier
   * @param regionSlug is code name of the region aka DigitalOcean data centers
   * @param sizeGigabytes size value in GB
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Action resizeVolume(String volumeId, String regionSlug, Double sizeGigabytes)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method will get all the available volume action by volume identifier
   *
   * @param volumeId volume identifier
   * @return {@link Actions}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Actions getAvailableVolumeActions(String volumeId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns specific volume action by action and volume identifier
   *
   * @param volumeId volume identifier
   * @param actionId the id of action
   * @return {@link Action}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.7
   */
  Action getVolumeAction(String volumeId, Integer actionId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method return all of the snapshots for given volume Id
   *
   * @param volumeId volume identifier
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Snapshots}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.8
   */
  Snapshots getVolumeSnapshots(String volumeId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method take snapshot of given volume Id
   *
   * @param volumeId volume identifier
   * @param snapshotName the name the snapshot to be created
   * @return {@link Snapshot}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.8
   */
  Snapshot takeVolumeSnapshot(String volumeId, String snapshotName)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Snapshots manipulation methods
  // ===========================================

  /**
   * Method return all of the snapshots available on your account
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Snapshots}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.8
   */
  Snapshots getAvailableSnapshots(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method return all of the droplet snapshots available on your account
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Snapshots}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.8
   */
  Snapshots getAllDropletSnapshots(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method return all of the volume snapshots available on your account
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Snapshots}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.8
   */
  Snapshots getAllVolumeSnapshots(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns specific snapshot info by id
   *
   * @param snapshotId for snapshot
   * @return {@link Snapshot}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.8
   */
  Snapshot getSnaphotInfo(String snapshotId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method deletes snapshot by id
   *
   * @param snapshotId for snapsho
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.8
   */
  Delete deleteSnapshot(String snapshotId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Load balancers manipulation methods
  // ===========================================

  /**
   * Method allows you to add a new load balancer to distribute traffic across multiple Droplets.
   *
   * <p>The LoadBalancer object passed in param can have a list of dropletIds set as dropletIds
   * attribute. In this case, given Droplets will be assigned to the Load Balancer.
   *
   * <p>You may also use a Droplet tag to assign a group of Droplets to Load Balancer in place of a
   * list of Droplet IDs. In this case, set the tag attribute of the LoadBalancer object passed in
   * param.
   *
   * @param loadBalancer the instance of the loadBalancer class
   * @return {@link LoadBalancer}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  LoadBalancer createLoadBalancer(LoadBalancer loadBalancer)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns complete information for given load balancer ID.
   *
   * @param loadBalancerId the id of the droplet
   * @return {@link LoadBalancer}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  LoadBalancer getLoadBalancerInfo(String loadBalancerId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns all active loadbalancers that are currently running in your account. All
   * available API information is presented for each loadbalancer.
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link LoadBalancers}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  LoadBalancers getAvailableLoadBalancers(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to update an existign load balancer. The LoadBalancer object passed in param
   * should contain a full representation of the Load Balancer including existing attributes. It may
   * contain one of the dropletIds or tag parameter as they are mutually exclusive. Note that any
   * attribute that is not provided will be reset to its default value.
   *
   * @param loadBalancer the instance of the loadBalancer class
   * @return {@link LoadBalancer}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  LoadBalancer updateLoadBalancer(LoadBalancer loadBalancer)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to assign a Droplet to a Load Balancer instance.
   *
   * @param loadBalancerId the id of the loadBalancer
   * @param dropletIds an array containing the IDs of the Droplets to be assigned to the Load
   *     Balancer instance.
   * @return {@link Response}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  Response addDropletsToLoadBalancer(String loadBalancerId, List<Integer> dropletIds)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to remove a Droplet from a Load Balancer instance.
   *
   * @param loadBalancerId the id of the loadBalancer
   * @param dropletIds an array containing the IDs of the Droplets to be removed from the Load
   *     Balancer instance.
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  Delete removeDropletsFromLoadBalancer(String loadBalancerId, List<Integer> dropletIds)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to add an additional forwarding rule to a Load Balancer instance.
   *
   * @param loadBalancerId the id of the loadBalancer
   * @param forwardingRules an array containing the Forwarding Rules to add to the Load Balancer
   *     instance.
   * @return {@link Response}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  Response addForwardingRulesToLoadBalancer(
      String loadBalancerId, List<ForwardingRules> forwardingRules)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to remove forwarding rules from a Load Balancer instance.
   *
   * @param loadBalancerId the id of the loadBalancer
   * @param forwardingRules an array containing the Forwarding Rules to remove from the Load
   *     Balancer instance.
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  Delete removeForwardingRulesFromLoadBalancer(
      String loadBalancerId, List<ForwardingRules> forwardingRules)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to delete a Load Balancer instance, disassociating any Droplets assigned to
   * it and removing it from your account.
   *
   * @param loadBalancerId the id of the loadBalancer
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.11
   */
  Delete deleteLoadBalancer(String loadBalancerId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Certificates manipulation methods
  // ===========================================

  /**
   * Method returns all available Certificates from your account.
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link LoadBalancers}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.12
   */
  Certificates getAvailableCertificates(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to create a new certificate. See the required parameters section below for an
   * explanation of the variables that are needed to create a new certificate.
   *
   * <p>Create a instance of {@link Certificate} class and populated the certificate object
   * appropriately. Required values are -
   *
   * <pre>
   * {
   *    "name": "web-cert-01",
   *    "private_key": "-----BEGIN PRIVATE KEY----- ... -----END PRIVATE KEY-----",
   *    "leaf_certificate": "-----BEGIN CERTIFICATE----- ... -----END CERTIFICATE-----",
   *    "certificate_chain": "-----BEGIN CERTIFICATE----- ... -----END CERTIFICATE-----"
   * }
   * </pre>
   *
   * @param certificate the instance of the Certificate class
   * @return {@link Certificate}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.12
   */
  Certificate createCertificate(Certificate certificate)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to create a new Let's Encrypt certificate. See the required parameters
   * section below for an explanation of the variables that are needed to create a new Let's Encrypt
   * certificate.
   *
   * <p>Create a instance of {@link Certificate} class and populated the certificate object
   * appropriately. Required values are -
   *
   * <pre>
   * {
   *    "name": "le-cert-01",
   *    "type": "lets_encrypt",
   *    "dns_names": ["www.example.com","example.com"]
   * }
   * </pre>
   *
   * @param certificate the instance of the Certificate class
   * @return {@link Certificate}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.12
   */
  Certificate createLetsEncryptCertificate(Certificate certificate)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns the Certificate information for given certificate ID.
   *
   * @param certificateId the id of the certificate
   * @return {@link Certificate}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.12
   */
  Certificate getCertificateInfo(String certificateId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to delete a Certificate by Id from your account.
   *
   * @param certificateId the id of the certificate
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.12
   */
  Delete deleteCertificate(String certificateId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  // ===========================================
  // Firewalls manipulation methods
  // ===========================================

  /**
   * Method allows you to add a new firewall to restrict network access to and from a Droplet.
   *
   * <p>The Firewall object passed in param can have a list of dropletIds set as dropletIds
   * attribute. In this case, given Droplets will use the Firewall rules.
   *
   * <p>You may also use a Droplet tag to assign a group of Droplets to use the Firewall in place of
   * a list of Droplet IDs. In this case, set the tag attribute of the Firewall object passed in
   * param.
   *
   * @param firewall the instance of the firewall class
   * @return {@link Firewall}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.16
   */
  Firewall createFirewall(Firewall firewall)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns complete information for given firewall ID.
   *
   * @param firewallId the id of the firewall
   * @return {@link Firewall}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.16
   */
  Firewall getFirewallInfo(String firewallId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to update an existing fireawll. The Firewall object passed in param should
   * contain a full representation of the Firewall including existing attributes. It may contain one
   * of the dropletIds or tag parameter as they are mutually exclusive. Note that any attribute that
   * is not provided will be reset to its default value.
   *
   * @param firewall the instance of the firewall class
   * @return {@link Firewall}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.16
   */
  Firewall updateFirewall(Firewall firewall)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to delete a Firewall instance, disassociating any Droplets assigned to it and
   * removing it from your account.
   *
   * @param firewallId the id of the firewall
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.16
   */
  Delete deleteFirewall(String firewallId)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to assign a {@link Droplet} to a {@link Firewall} instance.
   *
   * @param firewallId the id of the firewall to add droplets to
   * @param dropletIds the list of of droplet IDs to add to the firewall
   * @return {@link Response} A generic HTTP status response
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.17
   */
  Response addDropletsToFirewall(String firewallId, List<Integer> dropletIds)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to remove a Droplet from a Load Balancer instance.
   *
   * @param firewallId the id of the firewall
   * @param dropletIds the list of droplet IDs to be removed from the firewall instance.
   * @return {@link Delete}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.17
   */
  Delete removeDropletsFromFirewall(String firewallId, List<Integer> dropletIds)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method returns all available firewalls that are currently running in your account. All
   * available API information is presented for each firewall.
   *
   * @param pageNo for pagination
   * @param perPage no. of items per page
   * @return {@link Firewalls}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   * @since v2.16
   */
  Firewalls getAvailableFirewalls(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to create a new project.
   *
   * @param project the project
   * @return {@link Project}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   */
  Project createProject(Project project) throws DigitalOceanException, RequestUnsuccessfulException;

  /**
   * Method allows you to get all the projects.
   *
   * @return {@link Projects}
   * @throws DigitalOceanException if request had interruption [ <code>
   *     HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>]
   * @throws RequestUnsuccessfulException if any RESTful request unsuccessful from wrapper method
   */
  Projects getAvailableProjects() throws DigitalOceanException, RequestUnsuccessfulException;

  Project updateProject(Project project) throws DigitalOceanException, RequestUnsuccessfulException;

  Project patchProject(Project project) throws DigitalOceanException, RequestUnsuccessfulException;

  Project getProject(String projectId) throws DigitalOceanException, RequestUnsuccessfulException;

  Project getDefaultProject() throws DigitalOceanException, RequestUnsuccessfulException;

  Project updateDefaultProject(Project project)
      throws DigitalOceanException, RequestUnsuccessfulException;

  Project patchDefaultProject(Project project)
      throws DigitalOceanException, RequestUnsuccessfulException;

  Delete deleteProject(String projectId) throws DigitalOceanException, RequestUnsuccessfulException;
}

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

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.Action;
import com.myjeeva.digitalocean.pojo.Actions;
import com.myjeeva.digitalocean.pojo.Backup;
import com.myjeeva.digitalocean.pojo.Backups;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.Domains;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.Images;
import com.myjeeva.digitalocean.pojo.Kernel;
import com.myjeeva.digitalocean.pojo.Kernels;
import com.myjeeva.digitalocean.pojo.Region;
import com.myjeeva.digitalocean.pojo.Regions;
import com.myjeeva.digitalocean.pojo.Size;
import com.myjeeva.digitalocean.pojo.Sizes;
import com.myjeeva.digitalocean.pojo.Snapshot;
import com.myjeeva.digitalocean.pojo.Snapshots;

/**
 * <p>
 * Junit Integration Test case for DigitalOcean API client wrapper methods
 * </p>
 * 
 * <p>
 * <strong>Please Note:</strong> <i>Kindly through and update private variable value before using
 * executing this test case(s).</i>
 * </p>
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
@Ignore
// Marked as Ignore since its a Integration Test case with real values
public class DigitalOceanIntegrationTest extends TestCase {

  private final Logger LOG = LoggerFactory.getLogger(DigitalOceanIntegrationTest.class);

  /**
   * This is testing values of my own respective to DigitalOcean account, to real-time integration
   * with API. So place your's for integration test case before use
   */
  private String authTokenRW = "";
  private String authTokenR = "";
  private Integer dropletIdForInfo = 10001; // to be placed before use
  private Integer dropletIdForDelete = 10002;
  private Integer imageId = 3445812; // Debian 7.0 x64 image id
  private String imageSlug = "ubuntu-12-04-x64";
  private String domainName = "";
  private String domainIp = "127.0.0.1";

  private DigitalOcean apiClient = new DigitalOceanClient(authTokenRW);

  // Droplet test cases

  @Test
  public void testGetAvailableDroplets() throws DigitalOceanException, RequestUnsuccessfulException {

    Droplets droplets = apiClient.getAvailableDroplets(1);

    assertNotNull(droplets);
    assertTrue((droplets.getDroplets().size() > 0));

    for (Droplet droplet : droplets.getDroplets()) {
      LOG.info(droplet.toString());
    }
  }

  @Test
  public void testGetAvailableKernels() throws DigitalOceanException, RequestUnsuccessfulException {

    Kernels kernels = apiClient.getAvailableKernels(dropletIdForInfo, 1);

    assertNotNull(kernels);
    assertTrue((kernels.getKernels().size() > 0));

    for (Kernel k : kernels.getKernels()) {
      LOG.info(k.toString());
    }
  }

  @Test
  public void testGetAvailableSnapshots() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Snapshots snapshots = apiClient.getAvailableSnapshots(dropletIdForInfo, 1);

    assertNotNull(snapshots);
    assertTrue((snapshots.getSnapshots().size() > 0));

    for (Snapshot s : snapshots.getSnapshots()) {
      LOG.info(s.toString());
    }
  }

  @Test
  public void testGetAvailableBackups() throws DigitalOceanException, RequestUnsuccessfulException {

    Backups backups = apiClient.getAvailableBackups(dropletIdForInfo, 1);

    assertNotNull(backups);
    assertTrue((backups.getBackups().size() > 0));

    for (Backup b : backups.getBackups()) {
      LOG.info(b.toString());
    }
  }

  @Test
  public void testGetDropletInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    Droplet droplet = apiClient.getDropletInfo(dropletIdForInfo);

    assertNotNull(droplet);

    LOG.info(droplet.toString());
  }

  @Test
  public void testCreateDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Droplet droplet = new Droplet();
    droplet.setName("api-client-test-host");
    droplet.setSize(new Size("512mb"));
    droplet.setImage(new Image(1601));
    droplet.setRegion(new Region("sgp1"));

    Droplet d = apiClient.createDroplet(droplet);

    assertNotNull(d);
    assertNotNull(d.getId());

    LOG.info(d.toString());
  }

  @Test
  public void testDeleteDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Boolean result = apiClient.deleteDroplet(2258153);

    assertNotNull(result);
    LOG.info("Delete Request Object: " + result);
  }

  @Test
  public void testRebootDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.rebootDroplet(2258136);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testPowerCycleDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.rebootDroplet(2258136);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testShutdownDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.shutdownDroplet(2258168);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testPowerOffDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.powerOffDroplet(2258168);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testPowerOnDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.powerOnDroplet(2258136);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testResetDropletPassword() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.resetDropletPassword(2258168);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testResizeDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.resizeDroplet(2258136, "1gb");

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testTakeDropletSnapshot() throws DigitalOceanException, RequestUnsuccessfulException {

    // Action action = apiClient.takeDropletSnapshot(2258168, "api-client-test-snapshot1");
    Action action = apiClient.takeDropletSnapshot(2258136);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testRestoreDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.restoreDroplet(2258168, 5489522); // Snapshot id

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testRebuildDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.rebuildDroplet(2258136, 3445812); // Debian 7.0 x64

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testDisableDropletBackups() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Action action = apiClient.disableDropletBackups(2258168);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testRenameDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.renameDroplet(2258168, "renamed-droplet-name-test");

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testChangeDropletKernel() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.changeDropletKernel(2258168, 1649);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testEnableDropletIpv6() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.enableDropletIpv6(2258168);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testEnableDropletPrivateNetworking() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Action action = apiClient.enableDropletPrivateNetworking(2258168);

    assertNotNull(action);
    LOG.info(action.toString());
  }


  // Action Test cases

  @Test
  public void testGetAvailableActions() throws DigitalOceanException, RequestUnsuccessfulException {

    Actions actions = apiClient.getAvailableActions(1);

    assertNotNull(actions);
    assertTrue((actions.getActions().size() > 0));

    for (Action a : actions.getActions()) {
      LOG.info(a.toString());
    }
  }

  @Test
  public void testGetActionInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.getActionInfo(28336352);

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testGetAvailableDropletActions() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Actions actions = apiClient.getAvailableDropletActions(dropletIdForInfo, 1);
    Actions actions3 = apiClient.getAvailableDropletActions(dropletIdForInfo, 3);

    assertNotNull(actions);
    assertTrue((actions.getActions().size() > 0));

    assertNotNull(actions3);
    assertTrue((actions3.getActions().size() > 0));

    for (Action a : actions.getActions()) {
      LOG.info(a.toString());
    }
  }

  @Test
  public void testGetAvailableImageActions() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Actions actions = apiClient.getAvailableImageActions(3794738, 1);

    assertNotNull(actions);
    assertTrue((actions.getActions().size() > 0));

    for (Action a : actions.getActions()) {
      LOG.info(a.toString());
    }
  }



  // Image test cases

  @Test
  public void testGetAvailableImages() throws DigitalOceanException, RequestUnsuccessfulException {

    Images images = apiClient.getAvailableImages(1);

    assertNotNull(images);
    assertTrue((images.getImages().size() > 0));

    for (Image img : images.getImages()) {
      LOG.info(img.toString());
    }
  }

  @Test
  public void testGetImageInfoById() throws DigitalOceanException, RequestUnsuccessfulException {

    Image image = apiClient.getImageInfo(imageId);

    assertNotNull(image);

    LOG.info(image.toString());
  }

  @Test
  public void testGetImageInfoBySlug() throws DigitalOceanException, RequestUnsuccessfulException {

    Image image = apiClient.getImageInfo(imageSlug);

    assertNotNull(image);

    LOG.info(image.toString());
  }

  @Test
  public void testUpdateImageInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    Image input = new Image();
    input.setId(3897539);
    input.setName("test-myjeeva.com-before-wp-upgrade");
    Image image = apiClient.updateImage(input);

    assertNotNull(image);

    LOG.info(image.toString());
  }

  @Test
  public void testDeleteImage() throws DigitalOceanException, RequestUnsuccessfulException {
    Boolean result = apiClient.deleteImage(3897539);

    assertNotNull(result);
    LOG.info("Delete Request result: " + result);
  }

  @Test
  public void testTransferImage() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.transferImage(5489522, "lon1");

    assertNotNull(action);
    LOG.info(action.toString());
  }


  // Regions test cases

  @Test
  public void testGetAvailableRegions() throws DigitalOceanException, RequestUnsuccessfulException {

    Regions regions = apiClient.getAvailableRegions(1);

    assertNotNull(regions);
    assertTrue((regions.getRegions().size() > 0));

    for (Region region : regions.getRegions()) {
      LOG.info(region.toString());
    }
  }


  // Sizes test cases

  @Test
  public void testGetAvailableSizes() throws DigitalOceanException, RequestUnsuccessfulException {

    Sizes sizes = apiClient.getAvailableSizes(1);

    assertNotNull(sizes);
    assertTrue((sizes.getSizes().size() > 0));

    for (Size size : sizes.getSizes()) {
      LOG.info(size.toString());
    }
  }


  // Domain test cases

  @Test
  public void testGetAvailableDomains() throws DigitalOceanException, RequestUnsuccessfulException {

    Domains domains = apiClient.getAvailableDomains(1);

    assertNotNull(domains);
    assertTrue((domains.getDomains().size() > 0));

    for (Domain d : domains.getDomains()) {
      LOG.info(d.toString());
    }
  }

  @Test
  public void testGetDomainInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    Domain domain = apiClient.getDomainInfo("jeeutil.com");

    assertNotNull(domain);
    LOG.info(domain.toString());
  }

  @Test
  public void testCreateDomain() throws DigitalOceanException, RequestUnsuccessfulException {

    Domain input = new Domain(domainName, domainIp);
    Domain domain = apiClient.createDomain(input);

    assertNotNull(domain);
    assertNotNull(domain.getName());

    LOG.info(domain.toString());
  }

  @Test
  public void testDeleteDomain() throws DigitalOceanException, RequestUnsuccessfulException {
    Boolean result = apiClient.deleteDomain(domainName);

    assertNotNull(result);
    LOG.info("Delete Request Object: " + result);
  }

}

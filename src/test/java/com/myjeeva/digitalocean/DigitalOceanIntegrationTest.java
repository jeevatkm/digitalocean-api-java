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

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myjeeva.digitalocean.common.DropletStatus;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.exception.ResourceNotFoundException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.Action;
import com.myjeeva.digitalocean.pojo.Actions;
import com.myjeeva.digitalocean.pojo.Backup;
import com.myjeeva.digitalocean.pojo.Backups;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.Domains;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.Images;
import com.myjeeva.digitalocean.pojo.Kernel;
import com.myjeeva.digitalocean.pojo.Kernels;
import com.myjeeva.digitalocean.pojo.Region;
import com.myjeeva.digitalocean.pojo.Regions;
import com.myjeeva.digitalocean.pojo.Response;
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
// @Ignore
// Marked as Ignore since its a Integration Test case with real values
public class DigitalOceanIntegrationTest extends TestCase {

  private final Logger LOG = LoggerFactory.getLogger(DigitalOceanIntegrationTest.class);

  /**
   * This is testing values of my own respective to DigitalOcean account, to real-time integration
   * with API. So place your's for integration test case before use
   */
  private String authTokenRW = "25adb6659b3e7ea020d764bb635ee38636d628d9a39d4ce396921d5b71efadec";
  private String authTokenR = "9329030aaab7dcdd4451e24ca6f3cd92d1c9ff0c3b5949012d36cc40f39fa457";
  private Integer dropletIdForInfo = 188913; // to be placed before use
  private Integer dropletIdForDelete = 2243507;
  private Integer imageId = 3445812; // Debian 7.0 x64 image id
  private String imageSlug = "ubuntu-12-04-x64";

  private DigitalOcean apiClient = new DigitalOceanClient(authTokenR);

  // Droplet test cases

  @Test
  public void testGetAvailableDroplets() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Droplets droplets = apiClient.getAvailableDroplets(1);

    assertNotNull(droplets);
    assertTrue((droplets.getDroplets().size() > 0));

    for (Droplet droplet : droplets.getDroplets()) {
      LOG.info(droplet.toString());
    }
  }

  @Test
  public void testGetAvailableKernels() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Kernels kernels = apiClient.getAvailableKernels(dropletIdForInfo, 1);

    assertNotNull(kernels);
    assertTrue((kernels.getKernels().size() > 0));

    for (Kernel k : kernels.getKernels()) {
      LOG.info(k.toString());
    }
  }

  @Test
  public void testGetAvailableSnapshots() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Snapshots snapshots = apiClient.getAvailableSnapshots(dropletIdForInfo, 1);

    assertNotNull(snapshots);
    assertTrue((snapshots.getSnapshots().size() > 0));

    for (Snapshot s : snapshots.getSnapshots()) {
      LOG.info(s.toString());
    }
  }

  @Test
  public void testGetAvailableBackups() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Backups backups = apiClient.getAvailableBackups(dropletIdForInfo, 1);

    assertNotNull(backups);
    assertTrue((backups.getBackups().size() > 0));

    for (Backup b : backups.getBackups()) {
      LOG.info(b.toString());
    }
  }

  @Test
  public void testGetAvailableActions() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Actions actions = apiClient.getAvailableActions(dropletIdForInfo, 1);
    Actions actions3 = apiClient.getAvailableActions(dropletIdForInfo, 3);

    assertNotNull(actions);
    assertTrue((actions.getActions().size() > 0));

    assertNotNull(actions3);
    assertTrue((actions3.getActions().size() > 0));

    for (Action a : actions.getActions()) {
      LOG.info(a.toString());
    }
  }

  @Test
  public void testGetDropletInfo() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Droplet droplet = apiClient.getDropletInfo(dropletIdForInfo);

    assertNotNull(droplet);

    LOG.info(droplet.toString());
  }

  @Test
  public void testCreateDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Droplet droplet = new Droplet();
    droplet.setName("api-client-test-host2");
    droplet.setSize(new Size("512mb"));
    droplet.setImage(new Image(imageId));
    droplet.setRegion(new Region("sgp1"));

    Droplet d = apiClient.createDroplet(droplet);

    assertNotNull(d);
    assertNotNull(d.getId());

    LOG.info(d.toString());
  }

  @Test
  public void testDeleteDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Boolean response = apiClient.deleteDroplet(2243517);

    assertNotNull(response);
    LOG.info("Delete Request response: " + response);
  }


  // Image test cases

  @Test
  public void testGetAvailableImages() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Images images = apiClient.getAvailableImages(1);

    assertNotNull(images);
    assertTrue((images.getImages().size() > 0));

    for (Image img : images.getImages()) {
      LOG.info(img.toString());
    }
  }

  @Test
  public void testGetImageInfoById() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Image image = apiClient.getImageInfo(imageId);

    assertNotNull(image);

    LOG.info(image.toString());
  }

  @Test
  public void testGetImageInfoBySlug() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Image image = apiClient.getImageInfo(imageSlug);

    assertNotNull(image);

    LOG.info(image.toString());
  }

  @Test
  public void testUpdateImageInfo() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Image input = new Image();
    input.setId(3897539);
    input.setName("test-myjeeva.com-before-wp-upgrade");
    Image image = apiClient.updateImage(input);

    assertNotNull(image);

    LOG.info(image.toString());
  }


  // Regions test cases

  @Test
  public void testGetAvailableRegions() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Regions regions = apiClient.getAvailableRegions(1);

    assertNotNull(regions);
    assertTrue((regions.getRegions().size() > 0));

    for (Region region : regions.getRegions()) {
      LOG.info(region.toString());
    }
  }


  // Sizes test cases

  @Test
  public void testGetAvailableSizes() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Sizes sizes = apiClient.getAvailableSizes(1);

    assertNotNull(sizes);
    assertTrue((sizes.getSizes().size() > 0));

    for (Size size : sizes.getSizes()) {
      LOG.info(size.toString());
    }
  }


  // Domain test cases

  @Test
  public void testGetAvailableDomains() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Domains domains = apiClient.getAvailableDomains(1);

    assertNotNull(domains);
    assertTrue((domains.getDomains().size() > 0));

    for (Domain d : domains.getDomains()) {
      LOG.info(d.toString());
    }
  }

  @Test
  public void testGetDomainInfo() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {
    Domain domain = apiClient.getDomainInfo("jeeutil.com");

    assertNotNull(domain);
    LOG.info(domain.toString());
  }

  @Test
  public void testCreateDomain() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Domain input = new Domain("rakeshshetty.me", "198.199.81.201");
    Domain domain = apiClient.createDomain(input);

    assertNotNull(domain);
    assertNotNull(domain.getName());

    LOG.info(domain.toString());
  }

  @Test
  public void testDeleteDomain() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {
    Boolean response = apiClient.deleteDomain("rakeshshetty.me");

    assertNotNull(response);
    LOG.info("Delete Request response: " + response);
  }



  @Test
  public void testRebootDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.rebootDroplet(dropletIdForInfo);

    assertAndLogResponseValue(response);
  }

  @Test
  public void testPowerCyleDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {
    assertAndLogResponseValue(apiClient.powerCyleDroplet(dropletIdForInfo));
  }

  @Test
  public void testShutdownDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.shutdownDroplet(dropletIdForInfo);

    assertAndLogResponseValue(response);
  }

  @Test
  public void testPowerOffDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.powerOffDroplet(dropletIdForInfo);

    assertAndLogResponseValue(response);
  }

  @Test
  public void testPowerOnDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.powerOnDroplet(dropletIdForInfo);

    assertAndLogResponseValue(response);
  }

  @Test
  public void testResetDropletPassword() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.resetDropletPassword(dropletIdForInfo);

    assertAndLogResponseValue(response);
  }

  @Test
  public void testResizeDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {


  }

  @Test
  public void testTakeDropletSnapshot() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.takeDropletSnapshot(dropletIdForInfo);

    assertAndLogResponseValue(response);
  }

  @Test
  public void testTakeDropletSnapshotWithCustomName() throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {

    Response response =
        apiClient.takeDropletSnapshot(dropletIdForInfo, "droplet-snapshot-20130607-1");

    assertAndLogResponseValue(response);
  }

  @Test
  public void testRestoreDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {


  }

  @Test
  public void testRebuildDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {


  }

  @Test
  public void testEnableDropletBackups() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.enableDropletBackups(dropletIdForInfo);

    assertAndLogResponseValue(response);
  }

  @Test
  public void testDisableDropletBackups() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.disableDropletBackups(dropletIdForInfo);

    assertAndLogResponseValue(response);
  }

  @Test
  public void testRenameDroplet() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.renameDroplet(dropletIdForInfo, "api-client-test-host-rename");

    assertAndLogResponseValue(response);
  }



  @Test
  public void testDeleteImage() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

  }

  @Test
  public void testTransferImage() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

  }



  @Test
  public void testGetDomainRecords() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {


  }

  @Test
  public void testGetDomainRecordInfo() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {


  }

  @Test
  public void testCreateDomainRecordForA() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {


  }

  @Test
  public void testCreateDomainRecordForSubdomain() throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {

    DomainRecord domainRecord = new DomainRecord();
    // domainRecord.setDomainId(domainId);
    domainRecord.setData("@");
    domainRecord.setName("www");
    domainRecord.setType("CNAME");

    DomainRecord dr = apiClient.createDomainRecord(domainRecord);

    assertNotNull(dr.getId());
    assertNotNull(dr);

    logDomainRecordValues(dr);
  }

  @Test
  public void testEditDomainRecord() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    DomainRecord domainRecord = new DomainRecord();
    domainRecord.setId(298952);
    // domainRecord.setDomainId(domainId);
    domainRecord.setData("@");
    domainRecord.setName("static");
    domainRecord.setType("CNAME");

    DomainRecord dr = apiClient.editDomainRecord(domainRecord);

    assertNotNull(dr.getId());
    assertNotNull(dr);

    logDomainRecordValues(dr);
  }

  @Test
  public void testDeleteDomainRecord() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {


  }

  @Test
  public void testGetEventProgress() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {

    Response response = apiClient.getEventProgress(6125598);

    assertNotNull(response);

    LOG.info("Response Status: " + response.getStatus());
    LOG.info("Response Object: " + response);
  }

  @Test
  public void testDropletStates() {

    Droplet droplet = new Droplet();
    droplet.setStatus(DropletStatus.NEW);
    assertTrue(droplet.isNew());
    assertFalse(droplet.isActive());

    droplet.setStatus(DropletStatus.ACTIVE);
    assertTrue(droplet.isActive());
    assertFalse(droplet.isNew());

    droplet.setStatus(DropletStatus.OFF);
    assertTrue(droplet.isOff());
    assertFalse(droplet.isActive());

  }

  private void assertAndLogResponseValue(Response response) {

    assertNotNull(response.getEventId());
    assertNotNull(response.getStatus());

    LOG.info("Response Status: " + response.getStatus() + ", Event Id: " + response.getEventId());
  }

  private void logDomainRecordValues(DomainRecord dr) {
    LOG.info("Domain Record Id: " + dr.getId() + ", Record Name: " + dr.getName()
        + ", Record Type: " + dr.getType() + ", Record Data: " + dr.getData()
        + ", Record Priority: " + dr.getPriority() + ", Record Port: " + dr.getPort()
        + ", Record Weight: " + dr.getWeight());
  }
}

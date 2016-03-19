/*
 * The MIT License
 * 
 * Copyright (c) 2010-2015 Jeevanandam M. (myjeeva.com)
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myjeeva.digitalocean.common.ActionType;
import com.myjeeva.digitalocean.common.ResourceType;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.Account;
import com.myjeeva.digitalocean.pojo.Action;
import com.myjeeva.digitalocean.pojo.Actions;
import com.myjeeva.digitalocean.pojo.Backup;
import com.myjeeva.digitalocean.pojo.Backups;
import com.myjeeva.digitalocean.pojo.Delete;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.DomainRecords;
import com.myjeeva.digitalocean.pojo.Domains;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.FloatingIP;
import com.myjeeva.digitalocean.pojo.FloatingIPs;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.Images;
import com.myjeeva.digitalocean.pojo.Kernel;
import com.myjeeva.digitalocean.pojo.Kernels;
import com.myjeeva.digitalocean.pojo.Key;
import com.myjeeva.digitalocean.pojo.Keys;
import com.myjeeva.digitalocean.pojo.Neighbors;
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
  private Integer dropletIdForInfo = 10001; // to be placed before use
  private Integer imageId = 3445812; // Debian 7.0 x64 image id
  private String imageSlug = "ubuntu-12-04-x64";
  private String domainName = "";
  private String domainIp = "127.0.0.1";

  private DigitalOcean apiClient = new DigitalOceanClient(authTokenRW);

  // Droplet test cases

  @Test
  public void testGetAvailableDroplets() throws DigitalOceanException, RequestUnsuccessfulException {

    Droplets droplets = apiClient.getAvailableDroplets(1, null);

    assertNotNull(droplets);
    assertTrue((droplets.getDroplets().size() > 0));

    int i = 1;
    for (Droplet droplet : droplets.getDroplets()) {
      LOG.info(i++ + " -> " + droplet.toString());
    }
  }

  @Test
  public void testGetAvailableKernels() throws DigitalOceanException, RequestUnsuccessfulException {

    Kernels kernels = apiClient.getAvailableKernels(dropletIdForInfo, 1, 20);

    assertNotNull(kernels);
    assertTrue((kernels.getKernels().size() > 0));

    int i = 1;
    for (Kernel k : kernels.getKernels()) {
      LOG.info(i++ + " -> " + k.toString());
    }
  }

  @Test
  public void testGetAvailableSnapshots() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Snapshots snapshots = apiClient.getAvailableSnapshots(dropletIdForInfo, 1, 20);

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
  public void testCreateDropletByImageId() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Droplet droplet = new Droplet();
    droplet.setName("api-client-test-host-byid");
    droplet.setSize("512mb");
    droplet.setImage(new Image(1601));
    droplet.setRegion(new Region("sgp1"));
    droplet.setEnableBackup(Boolean.TRUE);
    droplet.setEnableIpv6(Boolean.TRUE);
    droplet.setEnablePrivateNetworking(Boolean.TRUE);

    // Adding SSH key info
    List<Key> keys = new ArrayList<Key>();
    keys.add(new Key(513618));
    droplet.setKeys(keys);

    // Adding Metadata API - User Data
    /*
     * droplet .setUserData("#!/bin/bash" + "apt-get -y update" + "apt-get -y install nginx" +
     * "export HOSTNAME=$(curl -s http://169.254.169.254/metadata/v1/hostname)" +
     * "export PUBLIC_IPV4=$(curl -s http://169.254.169.254/metadata/v1/interfaces/public/0/ipv4/address)"
     * + "echo Droplet: $HOSTNAME, IP Address: $PUBLIC_IPV4 > /usr/share/nginx/html/index.html");
     */

    Droplet d = apiClient.createDroplet(droplet);

    assertNotNull(d);
    assertNotNull(d.getId());

    LOG.info(d.toString());
  }

  @Test
  public void testCreateDropletByImageSlug() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Droplet droplet = new Droplet();
    droplet.setName("api-client-test-host-byslug1");
    droplet.setSize("512mb");
    droplet.setImage(new Image("centos-5-8-x64"));
    droplet.setRegion(new Region("sgp1"));
    droplet.setEnableBackup(Boolean.TRUE);
    droplet.setEnableIpv6(Boolean.TRUE);
    droplet.setEnablePrivateNetworking(Boolean.TRUE);

    // Adding SSH key info
    List<Key> keys = new ArrayList<Key>();
    keys.add(new Key(513618));
    droplet.setKeys(keys);

    Droplet d = apiClient.createDroplet(droplet);

    assertNotNull(d);
    assertNotNull(d.getId());

    LOG.info(d.toString());
  }

  @Test
  public void testCreateDropletsByImageSlug() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Droplet droplet = new Droplet();
    droplet.setNames(Arrays.asList("sub-01.example.com", "sub-02.example.com"));
    droplet.setName("not allowed");
    droplet.setSize("512mb");
    droplet.setImage(new Image("ubuntu-14-04-x64"));
    droplet.setRegion(new Region("sgp1"));

    Droplets droplets = apiClient.createDroplets(droplet);

    assertNotNull(droplets);
    assertTrue((droplets.getDroplets().size() > 0));

    for (Droplet d : droplets.getDroplets()) {
      LOG.info(d.toString());
    }
  }

  @Test
  public void testDeleteDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Delete result = apiClient.deleteDroplet(2258153);

    assertNotNull(result);
    LOG.info("Delete Request Object: " + result);
  }

  @Test
  public void testGetDropletNeighbors() throws DigitalOceanException, RequestUnsuccessfulException {

    Droplets droplets = apiClient.getDropletNeighbors(dropletIdForInfo, 1);

    assertNotNull(droplets);

    for (Droplet d : droplets.getDroplets()) {
      LOG.info(d.toString());
    }
  }

  @Test
  public void testGetAllDropletNeighbors() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Neighbors neighbors = apiClient.getAllDropletNeighbors(1);

    assertNotNull(neighbors);

    for (Droplet d : neighbors.getNeighbors()) {
      LOG.info(d.toString());
    }
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

    Action action = apiClient.shutdownDroplet(4124871); // 2258168

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
  public void testEnableDropletBackups() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.enableDropletBackups(9662284);

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


  // Account Test cases

  @Test
  public void testGetAccountInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    Account account = apiClient.getAccountInfo();

    assertNotNull(account);
    LOG.info(account.toString());
  }


  // Action Test cases

  @Test
  public void testGetAvailableActions() throws DigitalOceanException, RequestUnsuccessfulException {

    Actions actions = apiClient.getAvailableActions(2, 30);

    assertNotNull(actions);
    assertTrue((actions.getActions().size() > 0));

    LOG.info(actions.getLinks().toString());

    int i = 1;
    for (Action a : actions.getActions()) {
      LOG.info(i++ + " -> " + a.toString());
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

    Actions actions = apiClient.getAvailableDropletActions(dropletIdForInfo, 1, 20);
    Actions actions3 = apiClient.getAvailableDropletActions(dropletIdForInfo, 3, 20);

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

    Actions actions = apiClient.getAvailableImageActions(3794738, 1, 20);

    assertNotNull(actions);
    assertTrue((actions.getActions().size() > 0));

    for (Action a : actions.getActions()) {
      LOG.info(a.toString());
    }
  }


  @Test
  public void testGetAvailableFloatingIPActions() throws DigitalOceanException,
      RequestUnsuccessfulException {
    Actions actions = apiClient.getAvailableFloatingIPActions("159.203.146.100", 1, 10);

    LOG.info(actions.toString());

    assertNotNull(actions);

    int i = 1;
    for (Action action : actions.getActions()) {
      LOG.info(i++ + " -> " + action.toString());
    }
  }

  @Test
  public void testGetFloatingIPActionInfo() throws DigitalOceanException,
      RequestUnsuccessfulException {
    Action action = apiClient.getFloatingIPActionInfo("159.203.146.100", 76697074);

    LOG.info(action.toString());

    assertNotNull(action);
  }



  // Image test cases

  @Test
  public void testGetAvailableImages() throws DigitalOceanException, RequestUnsuccessfulException {

    Images images = apiClient.getAvailableImages(1, 20);

    assertNotNull(images);
    assertTrue((images.getImages().size() > 0));

    for (Image img : images.getImages()) {
      LOG.info(img.toString());
    }
  }

  @Test
  public void testGetAvailableImagesByDistribution() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Images images = apiClient.getAvailableImages(1, 20, ActionType.DISTRIBUTION);

    assertNotNull(images);
    assertTrue((images.getImages().size() > 0));

    for (Image img : images.getImages()) {
      LOG.info(img.toString());
    }
  }

  @Test
  public void testGetAvailableImagesByApplication() throws DigitalOceanException,
      RequestUnsuccessfulException {

    Images images = apiClient.getAvailableImages(1, 20, ActionType.APPLICATION);

    assertNotNull(images);
    assertTrue((images.getImages().size() > 0));

    for (Image img : images.getImages()) {
      LOG.info(img.toString());
    }
  }

  @Test
  public void testGetAvailableImagesByIncorrrect() throws DigitalOceanException,
      RequestUnsuccessfulException {
    try {
      apiClient.getAvailableImages(1, 20, ActionType.BACKUP);
    } catch (DigitalOceanException doe) {
      LOG.info(doe.getMessage());
    }
  }

  @Test
  public void testGetUserImages() throws DigitalOceanException, RequestUnsuccessfulException {

    Images images = apiClient.getUserImages(1, 20);

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
    Delete result = apiClient.deleteImage(3897539);

    assertNotNull(result);
    LOG.info("Delete Request result: " + result);
  }

  @Test
  public void testTransferImage() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.transferImage(5489522, "lon1");

    assertNotNull(action);
    LOG.info(action.toString());
  }

  @Test
  public void testConvertImage() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.convertImage(5489522);

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
    Delete result = apiClient.deleteDomain(domainName);

    assertNotNull(result);
    LOG.info("Delete Request Object: " + result);
  }

  @Test
  public void testGetDomainRecords() throws DigitalOceanException, RequestUnsuccessfulException {

    DomainRecords domainRecords = apiClient.getDomainRecords("jeeutil.com", 1, null);

    assertNotNull(domainRecords);
    assertTrue((domainRecords.getDomainRecords().size() > 0));

    for (DomainRecord dr : domainRecords.getDomainRecords()) {
      LOG.info(dr.toString());
    }
  }

  @Test
  public void testGetDomainRecordInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    DomainRecord domainRecord = apiClient.getDomainRecordInfo("jeeutil.com", 160448);

    assertNotNull(domainRecord);
    LOG.info(domainRecord.toString());
  }

  @Test
  public void testCreateDomainRecord() throws DigitalOceanException, RequestUnsuccessfulException {

    DomainRecord input = new DomainRecord("test1", "@", "CNAME");
    DomainRecord domainRecord = apiClient.createDomainRecord("jeeutil.com", input);

    assertNotNull(domainRecord);
    LOG.info(domainRecord.toString());
  }

  @Test
  public void testUpdateDomainRecord() throws DigitalOceanException, RequestUnsuccessfulException {

    DomainRecord record = new DomainRecord("testjs", "@", "CNAME");
    DomainRecord domainRecord = apiClient.updateDomainRecord("example.me", 10989796, record);

    assertNotNull(domainRecord);
    LOG.info(domainRecord.toString());
  }

  @Test
  public void testDeleteDomainRecord() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteDomainRecord("jeeutil.com", 3253734);

    assertNotNull(result);
    LOG.info("Delete Request Object: " + result.toString());
  }

  // SSH Key test cases

  @Test
  public void testGetAvailableKeys() throws DigitalOceanException, RequestUnsuccessfulException {

    Keys keys = apiClient.getAvailableKeys(1);

    assertNotNull(keys);
    assertTrue((keys.getKeys().size() > 0));

    for (Key k : keys.getKeys()) {
      LOG.info(k.toString());
    }
  }

  @Test
  public void testCreateKey() throws DigitalOceanException, RequestUnsuccessfulException {

    // Key key = new Key("TestKey1",
    // "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQDDiyGRkL26BEFPkce1Xtv8u05t8IYHZ63EVDEoAfvg/HCM7SEauBogDGknd4aMd7p9XtxuEIiojiNDIpTnoYbS0RojzhtomefQ/Lx02Rpfsbj1U3zg1H/MMObgJILGIYyHwfT+1rkkRxJQBVcs2Yj7IOmsrmE6SkAZaDLnMxq74HWzd7sPHxx/Dmv6fE0VMaZa+l7Fwr/2Tm46RMF5vzb93QwwmShV+08Ik/0NjGgP7QcNzT11lrI1eCjwCFyT00sGXR+xa4n+M80NB3b8GqDJDAMKqxELcFkpGyGAqESlYt4DXoCRDmnUwhhHReOuutOUHqrSMCym94FFeJ6p6M1f jenkinsci@dexmedia.com");
    Key key =
        new Key(
            "TestKey1",
            "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCqMycrkDQdWJUuI5/yx5+Du2BD/W5CXRPnd1xr60MV1zSaRA7OI6sShBontgdI7iepq33hMNaBdr1VttagCjeVJpIsM78Dkcq2NmJ2DKqPnzmfcuJOfcVXO0al2kn4wkYhCKoCV1u3YFCSBW5h3KWOXnptUq30cLUnjgOpAHpugNatJS5Wk8h9V53V2m06FOOty9TY3L8BLQlG3Btl201XMQasFb25izoablwupRLeItzzOHSlwbXWDcrkEQz7o+doOsgpdUfPdQrC1Nv9ujV/Va7BIuUBVVQSznBddCvxmIv/9LIRR7S+Hk+jB8ZgSBcFdmfjdzdQxU39xri5OFTF madanje");

    Key resultKey = apiClient.createKey(key);

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    LOG.info(resultKey.toString());
  }

  @Test
  public void testGetKeyById() throws DigitalOceanException, RequestUnsuccessfulException {
    Key resultKey = apiClient.getKeyInfo(245798);

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    LOG.info(resultKey.toString());
  }

  @Test
  public void testGetKeyByFingerprint() throws DigitalOceanException, RequestUnsuccessfulException {
    Key resultKey = apiClient.getKeyInfo("3b:0b:99:54:ef:75:cb:88:88:66:3c:8d:10:64:74:32");

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    LOG.info(resultKey.toString());
  }

  @Test
  public void testUpdateKeyById() throws DigitalOceanException, RequestUnsuccessfulException {
    Key resultKey = apiClient.updateKey(245798, "TestKey5");

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    LOG.info(resultKey.toString());
  }

  @Test
  public void testUpdateKeyByFingerprint() throws DigitalOceanException,
      RequestUnsuccessfulException {
    Key resultKey =
        apiClient.updateKey("3b:0b:99:54:ef:75:cb:88:88:66:3c:8d:10:64:74:32", "TestKey4");

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    LOG.info(resultKey.toString());
  }

  @Test
  public void testDeleteKeyById() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteKey(245798);

    assertNotNull(result);
    LOG.info("Delete Key Request Object: " + result);
  }

  @Test
  public void testDeleteKeyByFingerprint() throws DigitalOceanException,
      RequestUnsuccessfulException {
    Delete result = apiClient.deleteKey("3b:0b:99:54:ef:75:cb:88:88:66:3c:8d:10:64:74:32");

    assertNotNull(result);
    LOG.info("Delete Key Request Object: " + result);
  }

  // Floating IPs test cases
  @Test
  public void testGetAvailableFloatingIPs() throws DigitalOceanException,
      RequestUnsuccessfulException {

    FloatingIPs floatingIPs = apiClient.getAvailableFloatingIPs(1, 10);

    LOG.info(floatingIPs.toString());

    assertNotNull(floatingIPs);
    // assertTrue((floatingIPs.getFloatingIPs().size() > 0));

    int i = 1;
    for (FloatingIP floatingIP : floatingIPs.getFloatingIPs()) {
      LOG.info(i++ + " -> " + floatingIP.toString());
    }
  }

  @Test
  public void testCreateFloatingIPForDroplet() throws DigitalOceanException,
      RequestUnsuccessfulException {

    FloatingIP floatingIP = apiClient.createFloatingIP(9674996);

    assertNotNull(floatingIP);

    LOG.info(floatingIP.toString());
  }

  @Test
  public void testCreateFloatingIPForRegion() throws DigitalOceanException,
      RequestUnsuccessfulException {

    FloatingIP floatingIP = apiClient.createFloatingIP("nyc3");

    assertNotNull(floatingIP);

    LOG.info(floatingIP.toString());
  }

  @Test
  public void testGetFloatingIPInfo(String ipAddress) throws DigitalOceanException,
      RequestUnsuccessfulException {

    FloatingIP floatingIP = apiClient.getFloatingIPInfo("159.203.146.100");

    assertNotNull(floatingIP);

    LOG.info(floatingIP.toString());
  }

  @Test
  public void testDeleteFloatingIP() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteFloatingIP("159.203.146.109");

    assertNotNull(result);
    LOG.info("Delete Floating IP Request Object: " + result);
  }

  @Test
  public void testAssignFloatingIP() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.assignFloatingIP(9674996, "159.203.146.100");

    LOG.info(action.toString());

    assertNotNull(action);
    assertEquals(ActionType.ASSIGN_FLOATING_IP, action.getType());
    assertEquals(ResourceType.FLOATING_IP, action.getResourceType());
  }

  @Test
  public void testUnassignFloatingIP() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.unassignFloatingIP("159.203.146.100");

    LOG.info(action.toString());

    assertNotNull(action);
    assertEquals(ActionType.UNASSIGN_FLOATING_IP, action.getType());
    assertEquals(ResourceType.FLOATING_IP, action.getResourceType());
  }

}

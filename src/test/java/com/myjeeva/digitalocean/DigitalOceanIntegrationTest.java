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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.myjeeva.digitalocean.common.ActionType;
import com.myjeeva.digitalocean.common.Environment;
import com.myjeeva.digitalocean.common.LoadBalancingAlgorithm;
import com.myjeeva.digitalocean.common.Protocol;
import com.myjeeva.digitalocean.common.ResourceType;
import com.myjeeva.digitalocean.common.StickySessionType;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.Account;
import com.myjeeva.digitalocean.pojo.Action;
import com.myjeeva.digitalocean.pojo.Actions;
import com.myjeeva.digitalocean.pojo.Backup;
import com.myjeeva.digitalocean.pojo.Backups;
import com.myjeeva.digitalocean.pojo.Certificate;
import com.myjeeva.digitalocean.pojo.Delete;
import com.myjeeva.digitalocean.pojo.Destinations;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.DomainRecords;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Firewall;
import com.myjeeva.digitalocean.pojo.FloatingIP;
import com.myjeeva.digitalocean.pojo.ForwardingRules;
import com.myjeeva.digitalocean.pojo.HealthCheck;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.InboundRules;
import com.myjeeva.digitalocean.pojo.Kernel;
import com.myjeeva.digitalocean.pojo.Kernels;
import com.myjeeva.digitalocean.pojo.Key;
import com.myjeeva.digitalocean.pojo.LoadBalancer;
import com.myjeeva.digitalocean.pojo.Neighbors;
import com.myjeeva.digitalocean.pojo.OutboundRules;
import com.myjeeva.digitalocean.pojo.Project;
import com.myjeeva.digitalocean.pojo.Projects;
import com.myjeeva.digitalocean.pojo.Region;
import com.myjeeva.digitalocean.pojo.Resource;
import com.myjeeva.digitalocean.pojo.Response;
import com.myjeeva.digitalocean.pojo.Snapshot;
import com.myjeeva.digitalocean.pojo.Snapshots;
import com.myjeeva.digitalocean.pojo.Sources;
import com.myjeeva.digitalocean.pojo.StickySessions;
import com.myjeeva.digitalocean.pojo.Tag;
import com.myjeeva.digitalocean.pojo.Volume;
import com.myjeeva.digitalocean.pojo.Volumes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Junit Integration Test case for DigitalOcean API client wrapper methods
 *
 * <p><strong>Please Note:</strong> <i>Kindly go through and update private variable value before
 * using executing this test case(s).</i>
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
// Marked as Ignore since its a Integration Test case with real values
@Ignore
@RunWith(JUnit4.class)
public class DigitalOceanIntegrationTest {

  private final Logger log = LoggerFactory.getLogger(DigitalOceanIntegrationTest.class);

  /**
   * This is testing values of my own respective to DigitalOcean account, to real-time integration
   * with API. So place your's for integration test case before use
   */
  private final String authTokenRW = "";

  private final Integer dropletIdForInfo = 10001; // to be placed before use
  private final String volumeIdForInfo = "10001"; // to be placed before use
  private final String volumeNameForInfo = "test-volume"; // to be placed before use, should have
  private final String loadBalancerIdForInfo =
      "155fa6cd-3e74-406d-90bd-5671488c7157"; // to be placed
  // before use
  private final String firewallIdForInfo =
      "190ceeb7-779a-4b04-9091-4dd175de65ec"; // to be placed before use

  private final String imageIdForDropletCreation =
      "1601"; // to be placed before use. Same is used for updating the
  // image info

  private final String domainName = "";

  private String projectId;

  private final DigitalOcean apiClient = new DigitalOceanClient(authTokenRW);

  // Droplet test cases

  @Test
  public void testGetDropletKernels() throws DigitalOceanException, RequestUnsuccessfulException {

    Kernels kernels = apiClient.getDropletKernels(dropletIdForInfo, 1, 20);

    assertNotNull(kernels);
    assertFalse((kernels.getKernels().isEmpty()));

    int i = 1;
    for (Kernel k : kernels.getKernels()) {
      log.info(i++ + " -> " + k.toString());
    }
  }

  @Test
  public void testGetDropletSnapshots() throws DigitalOceanException, RequestUnsuccessfulException {

    Snapshots snapshots = apiClient.getDropletSnapshots(dropletIdForInfo, 1, 20);

    assertNotNull(snapshots);
    assertFalse((snapshots.getSnapshots().isEmpty()));

    for (Snapshot s : snapshots.getSnapshots()) {
      log.info(s.toString());
    }
  }

  @Test
  public void testGetDropletBackups() throws DigitalOceanException, RequestUnsuccessfulException {

    Backups backups = apiClient.getDropletBackups(dropletIdForInfo, 1, 10);

    assertNotNull(backups);
    assertFalse((backups.getBackups().isEmpty()));

    for (Backup b : backups.getBackups()) {
      log.info(b.toString());
    }
  }

  @Test
  public void testGetDropletInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    Droplet droplet = apiClient.getDropletInfo(dropletIdForInfo);

    assertNotNull(droplet);

    log.info(droplet.toString());
  }

  @Test
  public void testCreateDropletByImageId()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Droplet droplet = new Droplet();
    droplet.setName("api-client-test-host-byid");
    droplet.setSize("512mb");
    droplet.setImage(new Image(imageIdForDropletCreation));
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

    log.info(d.toString());
  }

  @Test
  public void testCreateDropletByImageSlug()
      throws DigitalOceanException, RequestUnsuccessfulException {

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

    log.info(d.toString());
  }

  @Test
  public void testCreateDropletsByImageSlug()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Droplet droplet = new Droplet();
    droplet.setNames(Arrays.asList("sub-01.example.com", "sub-02.example.com"));
    droplet.setName("not allowed");
    droplet.setSize("512mb");
    droplet.setImage(new Image("ubuntu-14-04-x64"));
    droplet.setRegion(new Region("sgp1"));

    Droplets droplets = apiClient.createDroplets(droplet);

    assertNotNull(droplets);
    assertFalse((droplets.getDroplets().isEmpty()));

    for (Droplet d : droplets.getDroplets()) {
      log.info(d.toString());
    }
  }

  @Test
  public void testDeleteDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Delete result = apiClient.deleteDroplet(2258153);

    assertNotNull(result);
    log.info("Delete Request Object: " + result);
  }

  @Test
  public void testDeleteDropletByTagName()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Delete result = apiClient.deleteDropletByTagName("delete-me");

    assertNotNull(result);
    log.info("Delete by tag name Request Object: " + result);
  }

  @Test
  public void testGetDropletNeighbors() throws DigitalOceanException, RequestUnsuccessfulException {

    Droplets droplets = apiClient.getDropletNeighbors(dropletIdForInfo, 1);

    assertNotNull(droplets);

    for (Droplet d : droplets.getDroplets()) {
      log.info(d.toString());
    }
  }

  @Test
  public void testGetAllDropletNeighbors()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Neighbors neighbors = apiClient.getAllDropletNeighbors(1);

    assertNotNull(neighbors);

    for (Droplet d : neighbors.getNeighbors()) {
      log.info(d.toString());
    }
  }

  @Test
  public void testGetAvailableDropletsByTagName()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Droplets droplets = apiClient.getAvailableDropletsByTagName("mytagtest1", 1, 25);

    assertNotNull(droplets);

    for (Droplet d : droplets.getDroplets()) {
      log.info(d.toString());
    }
  }

  @Test
  public void testRebootDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.rebootDroplet(2258136);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testPowerCycleDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.rebootDroplet(2258136);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testShutdownDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.shutdownDroplet(4124871); // 2258168

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testPowerOffDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.powerOffDroplet(2258168);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testPowerOnDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.powerOnDroplet(2258136);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testResetDropletPassword()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.resetDropletPassword(2258168);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testResizeDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.resizeDroplet(2258136, "1gb");

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testTakeDropletSnapshot() throws DigitalOceanException, RequestUnsuccessfulException {

    // Action action = apiClient.takeDropletSnapshot(2258168, "api-client-test-snapshot1");
    Action action = apiClient.takeDropletSnapshot(2258136);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testRestoreDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.restoreDroplet(2258168, 5489522); // Snapshot id

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testRebuildDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.rebuildDroplet(2258136, 3445812); // Debian 7.0 x64

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testEnableDropletBackups()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.enableDropletBackups(9662284);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testDisableDropletBackups()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.disableDropletBackups(2258168);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testRenameDroplet() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.renameDroplet(2258168, "renamed-droplet-name-test");

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testChangeDropletKernel() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.changeDropletKernel(2258168, 1649);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testEnableDropletIpv6() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.enableDropletIpv6(2258168);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testEnableDropletPrivateNetworking()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.enableDropletPrivateNetworking(2258168);

    assertNotNull(action);
    log.info(action.toString());
  }

  // Account Test cases

  @Test
  public void testGetAccountInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    Account account = apiClient.getAccountInfo();

    assertNotNull(account);
    log.info(account.toString());
  }

  // Action Test cases

  @Test
  public void testGetAvailableActions() throws DigitalOceanException, RequestUnsuccessfulException {

    Actions actions = apiClient.getAvailableActions(2, 30);

    assertNotNull(actions);
    assertFalse((actions.getActions().isEmpty()));

    log.info(actions.getLinks().toString());

    int i = 1;
    for (Action a : actions.getActions()) {
      log.info(i++ + " -> " + a.toString());
    }
  }

  @Test
  public void testGetActionInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    Action action = apiClient.getActionInfo(28336352);

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testGetAvailableDropletActions()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Actions actions = apiClient.getAvailableDropletActions(dropletIdForInfo, 1, 20);
    Actions actions3 = apiClient.getAvailableDropletActions(dropletIdForInfo, 3, 20);

    assertNotNull(actions);
    assertFalse((actions.getActions().isEmpty()));

    assertNotNull(actions3);
    assertFalse((actions3.getActions().isEmpty()));

    for (Action a : actions.getActions()) {
      log.info(a.toString());
    }
  }

  @Test
  public void testGetAvailableImageActions()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Actions actions = apiClient.getAvailableImageActions(3794738, 1, 20);

    assertNotNull(actions);
    assertFalse((actions.getActions().isEmpty()));

    for (Action a : actions.getActions()) {
      log.info(a.toString());
    }
  }

  @Test
  public void testGetAvailableFloatingIPActions()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Actions actions = apiClient.getAvailableFloatingIPActions("159.203.146.100", 1, 10);

    log.info(actions.toString());

    assertNotNull(actions);

    int i = 1;
    for (Action action : actions.getActions()) {
      log.info(i++ + " -> " + action.toString());
    }
  }

  @Test
  public void testGetFloatingIPActionInfo()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.getFloatingIPActionInfo("159.203.146.100", 76697074);

    log.info(action.toString());

    assertNotNull(action);
  }

  // Image test cases

  @Test
  public void testCreateCustomImage() throws DigitalOceanException, RequestUnsuccessfulException {

    Image input =
        new Image(
            "ubuntu-18.04-minimal",
            "http://cloud-images.ubuntu.com/minimal/releases/bionic/release/ubuntu-18.04-minimal-cloudimg-amd64.img",
            "nyc3");
    input.setDescription("Cloud-optimized image w/ small footprint");
    input.setDistribution("Ubuntu");
    input.setTags(Arrays.asList("base-image", "prod"));

    Image image = apiClient.updateImage(input);

    assertNotNull(image);

    log.info(image.toString());
  }

  @Test
  public void testUpdateImageInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    Image input = new Image();
    input.setId(imageIdForDropletCreation);
    input.setName("test-myjeeva.com-before-wp-upgrade");
    Image image = apiClient.updateImage(input);

    assertNotNull(image);

    log.info(image.toString());
  }

  @Test
  public void testDeleteImage() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteImage(3897539);

    assertNotNull(result);
    log.info("Delete Request result: " + result);
  }

  @Test
  public void testTransferImage() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.transferImage(5489522, "lon1");

    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testConvertImage() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.convertImage(5489522);

    assertNotNull(action);
    log.info(action.toString());
  }

  // Domain test cases

  @Test
  public void testCreateDomain() throws DigitalOceanException, RequestUnsuccessfulException {

    String domainIp = "127.0.0.1";
    Domain input = new Domain(domainName, domainIp);
    Domain domain = apiClient.createDomain(input);

    assertNotNull(domain);
    assertNotNull(domain.getName());

    log.info(domain.toString());
  }

  @Test
  public void testDeleteDomain() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteDomain(domainName);

    assertNotNull(result);
    log.info("Delete Request Object: " + result);
  }

  @Test
  public void testGetDomainRecords() throws DigitalOceanException, RequestUnsuccessfulException {

    DomainRecords domainRecords = apiClient.getDomainRecords("jeeutil.com", 1, null);

    assertNotNull(domainRecords);
    assertFalse((domainRecords.getDomainRecords().isEmpty()));

    for (DomainRecord dr : domainRecords.getDomainRecords()) {
      log.info(dr.toString());
    }
  }

  @Test
  public void testGetDomainRecordInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    DomainRecord domainRecord = apiClient.getDomainRecordInfo("jeeutil.com", 160448);

    assertNotNull(domainRecord);
    log.info(domainRecord.toString());
  }

  @Test
  public void testCreateDomainRecord() throws DigitalOceanException, RequestUnsuccessfulException {

    DomainRecord input = new DomainRecord("test1", "@", "CNAME");
    DomainRecord domainRecord = apiClient.createDomainRecord("jeeutil.com", input);

    assertNotNull(domainRecord);
    log.info(domainRecord.toString());
  }

  @Test
  public void testUpdateDomainRecord() throws DigitalOceanException, RequestUnsuccessfulException {

    DomainRecord record = new DomainRecord("testjs", "@", "CNAME");
    DomainRecord domainRecord = apiClient.updateDomainRecord("example.me", 10989796, record);

    assertNotNull(domainRecord);
    log.info(domainRecord.toString());
  }

  @Test
  public void testDeleteDomainRecord() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteDomainRecord("jeeutil.com", 3253734);

    assertNotNull(result);
    log.info("Delete Request Object: " + result.toString());
  }

  // SSH Key test cases

  @Test
  public void testCreateKey() throws DigitalOceanException, RequestUnsuccessfulException {

    // Key key = new Key("TestKey1",
    // "ssh-rsa
    // AAAAB3NzaC1yc2EAAAADAQABAAABAQDDiyGRkL26BEFPkce1Xtv8u05t8IYHZ63EVDEoAfvg/HCM7SEauBogDGknd4aMd7p9XtxuEIiojiNDIpTnoYbS0RojzhtomefQ/Lx02Rpfsbj1U3zg1H/MMObgJILGIYyHwfT+1rkkRxJQBVcs2Yj7IOmsrmE6SkAZaDLnMxq74HWzd7sPHxx/Dmv6fE0VMaZa+l7Fwr/2Tm46RMF5vzb93QwwmShV+08Ik/0NjGgP7QcNzT11lrI1eCjwCFyT00sGXR+xa4n+M80NB3b8GqDJDAMKqxELcFkpGyGAqESlYt4DXoCRDmnUwhhHReOuutOUHqrSMCym94FFeJ6p6M1f
    // jenkinsci@dexmedia.com");
    Key key =
        new Key(
            "TestKey1",
            "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCqMycrkDQdWJUuI5/yx5+Du2BD/W5CXRPnd1xr60MV1zSaRA7OI6sShBontgdI7iepq33hMNaBdr1VttagCjeVJpIsM78Dkcq2NmJ2DKqPnzmfcuJOfcVXO0al2kn4wkYhCKoCV1u3YFCSBW5h3KWOXnptUq30cLUnjgOpAHpugNatJS5Wk8h9V53V2m06FOOty9TY3L8BLQlG3Btl201XMQasFb25izoablwupRLeItzzOHSlwbXWDcrkEQz7o+doOsgpdUfPdQrC1Nv9ujV/Va7BIuUBVVQSznBddCvxmIv/9LIRR7S+Hk+jB8ZgSBcFdmfjdzdQxU39xri5OFTF madanje");

    Key resultKey = apiClient.createKey(key);

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    log.info(resultKey.toString());
  }

  @Test
  public void testGetKeyById() throws DigitalOceanException, RequestUnsuccessfulException {
    Key resultKey = apiClient.getKeyInfo(245798);

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    log.info(resultKey.toString());
  }

  @Test
  public void testGetKeyByFingerprint() throws DigitalOceanException, RequestUnsuccessfulException {
    Key resultKey = apiClient.getKeyInfo("3b:0b:99:54:ef:75:cb:88:88:66:3c:8d:10:64:74:32");

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    log.info(resultKey.toString());
  }

  @Test
  public void testUpdateKeyById() throws DigitalOceanException, RequestUnsuccessfulException {
    Key resultKey = apiClient.updateKey(245798, "TestKey5");

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    log.info(resultKey.toString());
  }

  @Test
  public void testUpdateKeyByFingerprint()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Key resultKey =
        apiClient.updateKey("3b:0b:99:54:ef:75:cb:88:88:66:3c:8d:10:64:74:32", "TestKey4");

    assertNotNull(resultKey);
    assertNotNull(resultKey.getId());
    log.info(resultKey.toString());
  }

  @Test
  public void testDeleteKeyById() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteKey(245798);

    assertNotNull(result);
    log.info("Delete Key Request Object: " + result);
  }

  @Test
  public void testDeleteKeyByFingerprint()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteKey("3b:0b:99:54:ef:75:cb:88:88:66:3c:8d:10:64:74:32");

    assertNotNull(result);
    log.info("Delete Key Request Object: " + result);
  }

  // Floating IPs test cases

  @Test
  public void testCreateFloatingIPForDroplet()
      throws DigitalOceanException, RequestUnsuccessfulException {

    FloatingIP floatingIP = apiClient.createFloatingIP(9674996);

    assertNotNull(floatingIP);

    log.info(floatingIP.toString());
  }

  @Test
  public void testCreateFloatingIPForRegion()
      throws DigitalOceanException, RequestUnsuccessfulException {

    FloatingIP floatingIP = apiClient.createFloatingIP("nyc3");

    assertNotNull(floatingIP);

    log.info(floatingIP.toString());
  }

  @Test
  public void testGetFloatingIPInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    FloatingIP floatingIP = apiClient.getFloatingIPInfo("159.203.146.100");

    assertNotNull(floatingIP);

    log.info(floatingIP.toString());
  }

  @Test
  public void testDeleteFloatingIP() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteFloatingIP("159.203.146.109");

    assertNotNull(result);
    log.info("Delete Floating IP Request Object: " + result);
  }

  @Test
  public void testAssignFloatingIP() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.assignFloatingIP(9674996, "159.203.146.100");

    log.info(action.toString());

    assertNotNull(action);
    assertEquals(ActionType.ASSIGN_FLOATING_IP, action.getType());
    assertEquals(ResourceType.FLOATING_IP, action.getResourceType());
  }

  @Test
  public void testUnassignFloatingIP() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.unassignFloatingIP("159.203.146.100");

    log.info(action.toString());

    assertNotNull(action);
    assertEquals(ActionType.UNASSIGN_FLOATING_IP, action.getType());
    assertEquals(ResourceType.FLOATING_IP, action.getResourceType());
  }

  @Test
  public void testCreateTag() throws DigitalOceanException, RequestUnsuccessfulException {
    Tag tag = apiClient.createTag("blr");

    log.info(tag.toString());

    assertNotNull(tag);
    assertEquals("blr", tag.getName());
  }

  @Test
  public void testGetTag() throws DigitalOceanException, RequestUnsuccessfulException {
    Tag tag = apiClient.getTag("blog");

    log.info(tag.toString());

    assertNotNull(tag);
    assertEquals("blog", tag.getName());
  }

  @Test
  public void testDeleteTag() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteTag("blr");

    assertNotNull(result);
    log.info("Delete Tag Object: " + result);
  }

  @Test
  public void testTagResources() throws DigitalOceanException, RequestUnsuccessfulException {
    List<Resource> resources = new ArrayList<Resource>();
    resources.add(new Resource("3794738", ResourceType.DROPLET));

    Response result = apiClient.tagResources("lab", resources);

    assertNotNull(result);
    log.info("Response of Tag resources: " + result);
  }

  @Test
  public void testUntagResources() throws DigitalOceanException, RequestUnsuccessfulException {
    List<Resource> resources = new ArrayList<Resource>();
    resources.add(new Resource("3794738", ResourceType.DROPLET));

    Response result = apiClient.untagResources("lab", resources);

    assertNotNull(result);
    log.info("Response of Tag resources: " + result);
  }

  @Test
  public void testCreateVolume() throws DigitalOceanException, RequestUnsuccessfulException {
    Volume volume = new Volume();
    volume.setName("api-client-test-host-volume");
    volume.setDescription("Test Volume Description");
    volume.setRegion(new Region("nyc1"));
    volume.setSize(1);
    // different ID from volumeIdForInfo and created
    // in nyc1 region
    // to be placed before use
    String volumeSnapshotIdForCreate = "197e26b6-c242-11e7-bd8b-0242ac113802";
    volume.setSnapshotId(volumeSnapshotIdForCreate);

    Volume v = apiClient.createVolume(volume);

    assertNotNull(v);
    assertNotNull(v.getId());

    log.info(v.toString());
  }

  @Test
  public void testGetVolumeInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    Volume volume = apiClient.getVolumeInfo(volumeIdForInfo);

    assertNotNull(volume);

    log.info(volume.toString());
  }

  @Test
  public void testGetVolumeInfoByName() throws DigitalOceanException, RequestUnsuccessfulException {
    Volumes volumes = apiClient.getVolumeInfo(volumeNameForInfo, "nyc1");

    assertNotNull(volumes);
    assertFalse((volumes.getVolumes().isEmpty()));

    int i = 1;
    for (Volume volume : volumes.getVolumes()) {
      log.info(i++ + " -> " + volume.toString());
    }
  }

  @Test
  public void testAtachVolume() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.attachVolume(dropletIdForInfo, volumeIdForInfo, "nyc1");
    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testAttachVolumeByName() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.attachVolumeByName(dropletIdForInfo, volumeNameForInfo, "nyc1");
    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testResizeVolume() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.resizeVolume(volumeIdForInfo, "nyc1", 3D);
    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testDeachVolume() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.detachVolume(dropletIdForInfo, volumeIdForInfo, "nyc1");
    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testDetachVolumeByName() throws DigitalOceanException, RequestUnsuccessfulException {
    Action action = apiClient.detachVolumeByName(dropletIdForInfo, volumeNameForInfo, "nyc1");
    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testAvailableVolumeActions()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Actions actions = apiClient.getAvailableVolumeActions(volumeIdForInfo);

    assertNotNull(actions);
    assertFalse((actions.getActions().isEmpty()));

    log.info(actions.getLinks().toString());

    int i = 1;
    for (Action a : actions.getActions()) {
      log.info(i++ + " -> " + a.toString());
    }
  }

  @Test
  public void testAvailableVolumeAction()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Actions actions = apiClient.getAvailableVolumeActions(volumeIdForInfo);

    assertNotNull(actions);
    assertFalse((actions.getActions().isEmpty()));

    Action action = actions.getActions().get(0);

    apiClient.getVolumeAction(volumeIdForInfo, action.getId());
    assertNotNull(action);
    log.info(action.toString());
  }

  @Test
  public void testDeleteVolume() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteVolume(volumeIdForInfo);

    assertNotNull(result);
    log.info("Delete Request Object: " + result);
  }

  @Test
  public void testDeleteVolumeByName() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteVolume(volumeNameForInfo, "nyc1");

    assertNotNull(result);
    log.info("Delete Request Object: " + result);
  }

  @Test
  public void testGetVolumeSnapshots() throws DigitalOceanException, RequestUnsuccessfulException {

    Snapshots snapshots =
        apiClient.getVolumeSnapshots("82a48a18-873f-11e6-96bf-000f53315a41", 1, 10);

    assertNotNull(snapshots);
    assertFalse((snapshots.getSnapshots().isEmpty()));

    for (Snapshot s : snapshots.getSnapshots()) {
      log.info(s.toString());
    }
  }

  @Test
  public void testTakeVolumeSnapshot() throws DigitalOceanException, RequestUnsuccessfulException {
    Snapshot snapshot =
        apiClient.takeVolumeSnapshot(
            "fbe805e8-866b-11e6-96bf-000f53315a41", "api-test-volume-snapshot");

    assertNotNull(snapshot);

    log.info(snapshot.toString());
  }

  @Test
  public void testGetSnapshotInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    Snapshot snapshot = apiClient.getSnaphotInfo("fbe805e8-866b-11e6-96bf-000f53315a41");

    assertNotNull(snapshot);

    log.info(snapshot.toString());
  }

  @Test
  public void testDeleteSnapshot() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteSnapshot("fbe805e8-866b-11e6-96bf-000f53315a41");

    assertNotNull(result);
    log.info("Delete Request Object: " + result);
  }

  @Test
  public void testCreateLoadBalancer() throws DigitalOceanException, RequestUnsuccessfulException {

    LoadBalancer loadBalancer = new LoadBalancer();
    loadBalancer.setName("api-client-test-loadbalancer");
    loadBalancer.setRegion(new Region("ams3"));

    ForwardingRules rule = new ForwardingRules();
    rule.setEntryProtocol(Protocol.HTTP);
    rule.setTargetProtocol(Protocol.HTTP);
    rule.setEntryPort(80);
    rule.setTargetPort(80);
    loadBalancer.setForwardingRules(Arrays.asList(rule));

    HealthCheck healthCheck = new HealthCheck();
    healthCheck.setProtocol(Protocol.HTTP);
    healthCheck.setPort(80);
    loadBalancer.setHealthCheck(healthCheck);

    StickySessions stickySessions = new StickySessions();
    stickySessions.setType(StickySessionType.Cookies);
    stickySessions.setCookieName("mycookie");
    stickySessions.setCookieTtlInSeconds(20);
    loadBalancer.setStickySessions(stickySessions);

    LoadBalancer lb = apiClient.createLoadBalancer(loadBalancer);

    assertNotNull(lb);
    assertNotNull(lb.getId());

    log.info(lb.toString());
  }

  @Test
  public void testUpdateLoadBalancer() throws DigitalOceanException, RequestUnsuccessfulException {

    LoadBalancer lb = apiClient.getLoadBalancerInfo(loadBalancerIdForInfo);
    lb.setAlgorithm(LoadBalancingAlgorithm.LEAST_CONNECTIONS);
    lb.setDropletIds(Arrays.asList("5428878"));

    LoadBalancer loadBalancer = apiClient.updateLoadBalancer(lb);

    assertNotNull(loadBalancer);
    assertEquals(loadBalancerIdForInfo, loadBalancer.getId());
    assertEquals(LoadBalancingAlgorithm.LEAST_CONNECTIONS, loadBalancer.getAlgorithm());

    log.info(lb.toString());
  }

  @Test
  public void testGetLoadBalancerInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    LoadBalancer lb = apiClient.getLoadBalancerInfo(loadBalancerIdForInfo);
    assertNotNull(lb);
    log.info(lb.toString());
  }

  @Test
  public void testAddDropletsToLoadBalancer()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Response result =
        apiClient.addDropletsToLoadBalancer(loadBalancerIdForInfo, Arrays.asList(dropletIdForInfo));
    assertNotNull(result);
  }

  @Test
  public void testRemoveDropletsFromLoadBalancer()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Delete result =
        apiClient.removeDropletsFromLoadBalancer(
            loadBalancerIdForInfo, Arrays.asList(dropletIdForInfo));
    assertNotNull(result);
  }

  @Test
  public void testAddForwardingRulesToLoadBalancer()
      throws DigitalOceanException, RequestUnsuccessfulException {

    ForwardingRules rule = new ForwardingRules();
    rule.setEntryProtocol(Protocol.HTTP);
    rule.setTargetProtocol(Protocol.HTTP);
    rule.setEntryPort(8080);
    rule.setTargetPort(8080);

    Response result =
        apiClient.addForwardingRulesToLoadBalancer(loadBalancerIdForInfo, Arrays.asList(rule));
    assertNotNull(result);
  }

  @Test
  public void testRemoveForwardingRulesFromLoadBalancer()
      throws DigitalOceanException, RequestUnsuccessfulException {

    ForwardingRules rule = new ForwardingRules();
    rule.setEntryProtocol(Protocol.HTTP);
    rule.setTargetProtocol(Protocol.HTTP);
    rule.setEntryPort(8080);
    rule.setTargetPort(8080);

    Response result =
        apiClient.removeForwardingRulesFromLoadBalancer(loadBalancerIdForInfo, Arrays.asList(rule));
    assertNotNull(result);
  }

  @Test
  public void testDeleteLoadBalancer() throws DigitalOceanException, RequestUnsuccessfulException {

    Delete result = apiClient.deleteLoadBalancer(loadBalancerIdForInfo);
    assertNotNull(result);
  }

  @Test
  public void testCreateCertificate() throws DigitalOceanException, RequestUnsuccessfulException {

    Certificate certificate =
        new Certificate("my-cert", "privateKey", "leafCertificate", "certificateChain");

    Certificate result = apiClient.createCertificate(certificate);
    assertNotNull(result);

    log.info(result.toString());
  }

  @Test
  public void testLetsEncryptCreateCertificate()
      throws DigitalOceanException, RequestUnsuccessfulException {

    String[] dnsNames = {"example.com", "www.example.com"};
    Certificate certificate =
        new Certificate("my-let-encrypt-cert", "lets_encrypt", Arrays.asList(dnsNames));

    Certificate result = apiClient.createLetsEncryptCertificate(certificate);
    assertNotNull(result);

    log.info(result.toString());
  }

  @Test
  public void testGetCertifacteInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    Certificate certificate = apiClient.getCertificateInfo("9620c5d3-783c-4096-90f3-a2e363aa10fd");

    assertNotNull(certificate);
    log.info(certificate.toString());
  }

  @Test
  public void testDeleteCertificate() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteCertificate("9620c5d3-783c-4096-90f3-a2e363aa10fd");

    assertNotNull(result);
    log.info("Delete Request Object: " + result);
  }

  @Test
  public void testCreateFirewall() throws DigitalOceanException, RequestUnsuccessfulException {
    List<String> addressesInbound = Arrays.asList("18.0.0.0/8");

    Sources sources = new Sources();
    sources.setAddresses(addressesInbound);

    InboundRules inboundRules = new InboundRules();
    inboundRules.setProtocol("tcp");
    inboundRules.setPorts("22");
    inboundRules.setSources(sources);

    List<String> addressesOutbound = Arrays.asList("0.0.0.0/0", "::/0");

    Destinations destinations = new Destinations();
    destinations.setAddresses(addressesOutbound);

    OutboundRules outboundRules = new OutboundRules();
    outboundRules.setProtocol("tcp");
    outboundRules.setPorts("80");
    outboundRules.setDestinations(destinations);

    Firewall firewall = new Firewall();
    firewall.setName("integration-firewall");
    firewall.setInboundRules(Arrays.asList(inboundRules));
    firewall.setOutboundRules(Arrays.asList(outboundRules));

    Firewall fw = apiClient.createFirewall(firewall);

    assertNotNull(fw);
    assertNotNull(fw.getId());

    log.info(fw.toString());
  }

  @Test
  public void testGetFirewallInfo() throws DigitalOceanException, RequestUnsuccessfulException {

    Firewall fw = apiClient.getFirewallInfo(firewallIdForInfo);
    assertNotNull(fw);
    log.info(fw.toString());
  }

  @Test
  public void testUpdateFirewallInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    Firewall firewall = apiClient.getFirewallInfo(firewallIdForInfo);

    firewall.setName("edited-firewall");
    Firewall fw = apiClient.updateFirewall(firewall);

    assertNotNull(fw);
    assertEquals(fw.getName(), firewall.getName());
    assertEquals(fw.getId(), firewall.getId());

    log.info(fw.toString());
  }

  @Test
  public void testDeleteFirewall() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteFirewall(firewallIdForInfo);
    assertNotNull(result);
  }

  @Test
  public void createProject() throws DigitalOceanException, RequestUnsuccessfulException {
    Project project = new Project();
    project.setDescription("test description");
    project.setName("test name");
    project.setPurpose("custom project purpose");
    project.setEnvironment(Environment.DEVELOPMENT);

    Project p = apiClient.createProject(project);
    projectId = p.getId();

    assertNotNull(p);
    assertNotNull(p.getId());

    log.info(p.toString());
  }

  @Test
  public void testGetAvailableProjects()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Projects projects = apiClient.getAvailableProjects();

    assertNotNull(projects);
    assertFalse(projects.getProjects().isEmpty());

    for (Project project : projects.getProjects()) {
      log.info(project.toString());
    }
  }

  @Test
  public void testUpdateProject() throws DigitalOceanException, RequestUnsuccessfulException {
    Project project = apiClient.getProject(projectId);
    project.setName("updated project name");

    Project p = apiClient.updateProject(project);

    assertNotNull(p);
    assertNotNull(p.getId());

    log.info(p.toString());
  }

  @Test
  public void testPatchProject() throws DigitalOceanException, RequestUnsuccessfulException {
    Project project = apiClient.getProject(projectId);
    project.setName("updated project name");

    Project p = apiClient.patchProject(project);

    assertNotNull(p);
    assertNotNull(p.getId());

    log.info(p.toString());
  }

  @Test
  public void testGetDefaultProject() throws DigitalOceanException, RequestUnsuccessfulException {
    Project project = apiClient.getDefaultProject();

    assertNotNull(project);
    assertNotNull(project.getId());

    log.info(project.toString());
  }

  @Test
  public void testPatchDefaultProject() throws DigitalOceanException, RequestUnsuccessfulException {
    Project project = apiClient.getDefaultProject();
    project.setName("Updated default project name");

    Project p = apiClient.patchDefaultProject(project);

    assertNotNull(p);
    assertNotNull(p.getId());

    log.info(p.toString());
  }

  @Test
  public void testUpdateDefaultProject()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Project project = apiClient.getDefaultProject();
    project.setDescription("Updated default project description");
    project.setName("Updated default project name");

    Project p = apiClient.updateDefaultProject(project);

    assertNotNull(p);
    assertNotNull(p.getId());

    log.info(p.toString());
  }

  @Test
  public void testDeleteProject() throws DigitalOceanException, RequestUnsuccessfulException {
    Delete result = apiClient.deleteProject(projectId);
    assertNotNull(result);
  }
}

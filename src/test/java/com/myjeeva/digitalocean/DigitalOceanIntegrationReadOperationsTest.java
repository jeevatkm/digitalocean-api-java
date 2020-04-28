package com.myjeeva.digitalocean;

import static org.junit.Assert.*;

import com.myjeeva.digitalocean.common.ActionType;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Junit Integration Test case for DigitalOcean API client wrapper ReadOnly methods. So that it call
 *
 * <p><strong>Please Note:</strong> <i>Kindly go through and update private variable value before
 * using executing this test case(s).</i>
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
// Marked as Ignore since its a Integration Test case with real values
@Ignore
@RunWith(JUnit4.class)
public class DigitalOceanIntegrationReadOperationsTest {

  private final Logger log = LoggerFactory.getLogger(DigitalOceanIntegrationTest.class);

  /**
   * This is testing values of my own respective to DigitalOcean account, to real-time integration
   * with API. So place your's for integration test case before use
   */
  private final String authTokenR = "";

  private final DigitalOcean apiClient = new DigitalOceanClient(authTokenR);

  @Test
  public void testGetAvailableDroplets()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Droplets droplets = apiClient.getAvailableDroplets(1, null);

    assertNotNull(droplets);
    assertFalse((droplets.getDroplets().isEmpty()));

    int i = 1;
    for (Droplet droplet : droplets.getDroplets()) {
      log.info(i++ + " -> " + droplet.toString());
    }
  }

  @Test
  public void testGetAvailableImages() throws DigitalOceanException, RequestUnsuccessfulException {

    Images images = apiClient.getAvailableImages(1, 20);

    assertNotNull(images);
    assertFalse((images.getImages().isEmpty()));

    for (Image img : images.getImages()) {
      log.info(img.toString());
    }
  }

  @Test
  public void testGetAvailableImagesByDistribution()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Images images = apiClient.getAvailableImages(1, 20, ActionType.DISTRIBUTION);

    assertNotNull(images);
    assertFalse((images.getImages().isEmpty()));

    for (Image img : images.getImages()) {
      log.info(img.toString());
    }
  }

  @Test
  public void testGetAvailableImagesByApplication()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Images images = apiClient.getAvailableImages(1, 20, ActionType.APPLICATION);

    assertNotNull(images);
    assertFalse((images.getImages().isEmpty()));

    for (Image img : images.getImages()) {
      log.info(img.toString());
    }
  }

  @Test
  public void testGetAvailableImagesByIncorrrect()
      throws DigitalOceanException, RequestUnsuccessfulException {
    try {
      apiClient.getAvailableImages(1, 20, ActionType.BACKUP);
    } catch (DigitalOceanException doe) {
      log.info(doe.getMessage());
    }
  }

  @Test
  public void testGetUserImages() throws DigitalOceanException, RequestUnsuccessfulException {

    Images images = apiClient.getUserImages(1, 20);

    assertNotNull(images);
    assertFalse((images.getImages().isEmpty()));

    for (Image img : images.getImages()) {
      log.info(img.toString());
    }
  }

  @Test
  public void testGetImageInfoById() throws DigitalOceanException, RequestUnsuccessfulException {

    // Ubuntu 18.04 x64 20191022
    Integer imageId = 53893572;
    Image image = apiClient.getImageInfo(imageId);

    assertNotNull(image);

    log.info(image.toString());
  }

  @Test
  public void testGetImageInfoBySlug() throws DigitalOceanException, RequestUnsuccessfulException {

    String imageSlug = "ubuntu-18-04-x64";
    Image image = apiClient.getImageInfo(imageSlug);

    assertNotNull(image);

    log.info(image.toString());
  }

  // Regions test cases

  @Test
  public void testGetAvailableRegions() throws DigitalOceanException, RequestUnsuccessfulException {

    Regions regions = apiClient.getAvailableRegions(1);

    assertNotNull(regions);
    assertFalse((regions.getRegions().isEmpty()));

    for (Region region : regions.getRegions()) {
      log.info(region.toString());
    }
  }

  // Sizes test cases

  @Test
  public void testGetAvailableSizes() throws DigitalOceanException, RequestUnsuccessfulException {

    Sizes sizes = apiClient.getAvailableSizes(1);

    assertNotNull(sizes);
    assertFalse((sizes.getSizes().isEmpty()));

    for (Size size : sizes.getSizes()) {
      log.info(size.toString());
    }
  }

  @Test
  public void testGetAvailableDomains() throws DigitalOceanException, RequestUnsuccessfulException {

    Domains domains = apiClient.getAvailableDomains(1);

    assertNotNull(domains);
    assertFalse((domains.getDomains().isEmpty()));

    for (Domain d : domains.getDomains()) {
      log.info(d.toString());
    }
  }

  @Test
  public void testGetDomainInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    Domain domain = apiClient.getDomainInfo("myjeeva.com");

    assertNotNull(domain);
    log.info(domain.toString());
  }

  @Test
  public void testGetAvailableKeys() throws DigitalOceanException, RequestUnsuccessfulException {

    Keys keys = apiClient.getAvailableKeys(1);

    assertNotNull(keys);
    assertFalse((keys.getKeys().isEmpty()));

    for (Key k : keys.getKeys()) {
      log.info(k.toString());
    }
  }

  @Test
  public void testGetAvailableFloatingIPs()
      throws DigitalOceanException, RequestUnsuccessfulException {

    FloatingIPs floatingIPs = apiClient.getAvailableFloatingIPs(1, 10);

    log.info(floatingIPs.toString());

    assertNotNull(floatingIPs);
    // assertFalse((floatingIPs.getFloatingIPs().isEmpty()));

    int i = 1;
    for (FloatingIP floatingIP : floatingIPs.getFloatingIPs()) {
      log.info(i++ + " -> " + floatingIP.toString());
    }
  }

  @Test
  public void testGetAvailableTags() throws DigitalOceanException, RequestUnsuccessfulException {
    Tags tags = apiClient.getAvailableTags(1, 10);

    log.info(tags.toString());

    assertNotNull(tags);
  }

  @Test
  public void testGetAvailableVolumes() throws DigitalOceanException, RequestUnsuccessfulException {
    Volumes volumes = apiClient.getAvailableVolumes("nyc1");

    assertNotNull(volumes);
    assertFalse((volumes.getVolumes().isEmpty()));

    int i = 1;
    for (Volume volume : volumes.getVolumes()) {
      log.info(i++ + " -> " + volume.toString());
    }
  }

  @Test
  public void testGetAvailableLoadBalancers()
      throws DigitalOceanException, RequestUnsuccessfulException {

    LoadBalancers lbs = apiClient.getAvailableLoadBalancers(1, null);
    assertNotNull(lbs);

    assertTrue((lbs.getLoadBalancers().isEmpty()));

    int i = 0;
    for (LoadBalancer lb : lbs.getLoadBalancers()) {
      log.info(i++ + " -> " + lb.toString());
    }
  }

  @Test
  public void testGetAvailableCertificates()
      throws DigitalOceanException, RequestUnsuccessfulException {

    Certificates certificates = apiClient.getAvailableCertificates(1, 10);

    assertNotNull(certificates);
    assertFalse(certificates.getCertificates().isEmpty());

    for (Certificate c : certificates.getCertificates()) {
      log.info(c.toString());
    }
  }

  @Test
  public void testGetAvailableFirewalls()
      throws DigitalOceanException, RequestUnsuccessfulException {
    Firewalls firewalls = apiClient.getAvailableFirewalls(1, null);

    assertNotNull(firewalls);
    assertNotNull(firewalls.getFirewalls());
    assertFalse(firewalls.getFirewalls().isEmpty());

    int i = 0;
    for (Firewall firewall : firewalls.getFirewalls()) {
      log.info(i++ + " -> " + firewall.toString());
    }
  }
}

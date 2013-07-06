package com.myjeeva.digitalocean;

import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myjeeva.digitalocean.exception.AccessDeniedException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.exception.ResourceNotFoundException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.DropletImage;

public class DigitalOceanTest extends TestCase {

	private final Logger LOG = LoggerFactory.getLogger(DigitalOceanTest.class);

	private String clientId = "PIxoj3UAcLyvP7MTVeRdS";

	private String apiKey = "jRO0RLHC8jyBJIcD8w1zSZPBO0TJLu9u7HsZnprGf";

	private Integer dropletId = 188913;

	private DigitalOcean apiClient = new DigitalOceanClient(clientId, apiKey);

	@After
	public void after() {
		apiClient = null;
	}

	@Test
	public void testGetAvailableDroplets() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		List<Droplet> droplets = apiClient.getAvailableDroplets();
		assertTrue((droplets.size() > 0));
		assertNotNull(droplets);

		Droplet droplet = droplets.get(0);
		LOG.info("Droplet Id: " + droplet.getId() + ", Name: "
				+ droplet.getName() + ", Status: " + droplet.getStatus());
	}

	@Test
	public void testGetDropletInfo() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		Droplet droplet = apiClient.getDropletInfo(dropletId);
		assertNotNull(droplet);

		LOG.info("Droplet Id: " + droplet.getId() + ", Name: "
				+ droplet.getName() + ", Status: " + droplet.getStatus()
				+ ", Backup Active: " + droplet.getBackupsActive());
	}

	@Test
	public void testGetAvailableImages() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		List<DropletImage> images = apiClient.getAvailableImages();
		assertTrue((images.size() > 0));
		assertNotNull(images);

		DropletImage img = images.get(0);
		LOG.info("Image Id: " + img.getId() + ", Name: " + img.getName()
				+ ", Distribution: " + img.getDistribution());
	}

}

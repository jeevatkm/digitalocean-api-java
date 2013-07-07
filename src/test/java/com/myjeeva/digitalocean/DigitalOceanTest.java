/* The MIT License
 *
 * Copyright (c) 2010-2013 Jeevanandam M. (myjeeva.com)
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

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myjeeva.digitalocean.exception.AccessDeniedException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.exception.ResourceNotFoundException;
import com.myjeeva.digitalocean.impl.DigitalOceanClient;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.DropletImage;
import com.myjeeva.digitalocean.pojo.DropletSize;
import com.myjeeva.digitalocean.pojo.Region;
import com.myjeeva.digitalocean.pojo.Response;

/**
 * Junit Test case for DigitalOcean API client wrapper methods
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
@Ignore
public class DigitalOceanTest extends TestCase {

	private final Logger LOG = LoggerFactory.getLogger(DigitalOceanTest.class);

	/**
	 * place your client id for test case
	 */
	private String clientId = "";

	/**
	 * place your apiKey for test case
	 */
	private String apiKey = "";

	/**
	 * y testing droplet id, replace it with your for test case
	 */
	private Integer dropletId = 273221;

	/**
	 * 1GB plan
	 */
	private Integer sizeId = 63;

	/**
	 * my droplet data backup image id, replace it with your backup image id
	 */
	private Integer restoreImageId = 502930;

	/**
	 * Debian 7.0 x64 image id
	 */
	private Integer imageId = 308287;

	/**
	 * Region Id 1 is New York
	 */
	private Integer regionId = 1;

	private DigitalOcean apiClient = new DigitalOceanClient(clientId, apiKey);

	@Test
	public void testGetAvailableDroplets() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		List<Droplet> droplets = apiClient.getAvailableDroplets();
		assertTrue((droplets.size() > 0));
		assertNotNull(droplets);

		Droplet droplet = droplets.get(0);
		LOG.info("Droplet Id: " + droplet.getId() + ", Name: "
				+ droplet.getName() + ", Status: " + droplet.getStatus());
		LOG.info("All Droplets: " + droplets);
	}

	@Test
	public void testCreateDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		Droplet droplet = new Droplet();
		droplet.setName("api-cliet-test-host");
		droplet.setSizeId(66);
		droplet.setRegionId(3);
		droplet.setImageId(473123);

		Droplet response = apiClient.createDroplet(droplet);
		LOG.info("Droplet Id: " + response.getId() + ", Name: "
				+ response.getName() + ", Image Id: " + response.getImageId()
				+ ", Event Id: " + response.getEventId());
	}

	@Test
	public void testGetDropletInfo() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		Droplet droplet = apiClient.getDropletInfo(dropletId);
		assertNotNull(droplet);

		LOG.info("Droplet Id: " + droplet.getId() + ", Droplet Name: "
				+ droplet.getName() + ", Status: " + droplet.getStatus()
				+ ", Backup Active: " + droplet.getBackupsActive());
	}

	@Test
	public void testRebootDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.rebootDroplet(dropletId));
	}

	@Test
	public void testPowerCyleDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.powerCyleDroplet(dropletId));
	}

	@Test
	public void testShutdownDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.shutdownDroplet(dropletId));
	}

	@Test
	public void testPowerOffDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.powerOffDroplet(dropletId));
	}

	@Test
	public void testPowerOnDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.powerOnDroplet(dropletId));
	}

	@Test
	public void testResetDropletPassword() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.resetDropletPassword(dropletId));
	}

	@Test
	public void testResizeDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.resizeDroplet(dropletId, sizeId));
	}

	@Test
	public void testTakeDropletSnapshot() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.takeDropletSnapshot(dropletId));
	}

	@Test
	public void testTakeDropletSnapshotWithCustomName()
			throws AccessDeniedException, ResourceNotFoundException,
			RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.takeDropletSnapshot(dropletId,
				"droplet-snapshot-20130607"));
	}

	@Test
	public void testRestoreDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.restoreDroplet(dropletId,
				restoreImageId));
	}

	@Test
	public void testRebuildDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.rebuildDroplet(dropletId, imageId));
	}

	@Test
	public void testEnableDropletBackups() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.enableDropletBackups(dropletId));
	}

	@Test
	public void testDisableDropletBackups() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.disableDropletBackups(dropletId));
	}

	@Test
	public void testRenameDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.renameDroplet(dropletId,
				"api-cliet-test-host-rename"));
	}

	@Test
	public void testDeleteDroplet() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.deleteDroplet(dropletId));
	}

	@Test
	public void testGetAvailableRegions() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		List<Region> regions = apiClient.getAvailableRegions();
		assertTrue((regions.size() > 0));
		assertNotNull(regions);

		Region region = regions.get(1);
		LOG.info("Region Id: " + region.getId() + ", Region Name: "
				+ region.getName() + ", Slug: " + region.getSlug());
	}

	@Test
	public void testGetAvailableImages() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		List<DropletImage> images = apiClient.getAvailableImages();
		assertTrue((images.size() > 0));
		assertNotNull(images);

		DropletImage img = images.get(1);
		LOG.info("Image Id: " + img.getId() + ", Image Name: " + img.getName()
				+ ", Distribution: " + img.getDistribution());
	}

	@Test
	public void testGetImageInfo() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		DropletImage dropletImage = apiClient.getImageInfo(imageId);
		assertNotNull(dropletImage);

		LOG.info("Image Id: " + dropletImage.getId() + ", Image Name: "
				+ dropletImage.getName() + ", Distribution: "
				+ dropletImage.getDistribution() + ", Slug: "
				+ dropletImage.getSlug());
	}

	@Test
	public void testDeleteImage() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.deleteImage(restoreImageId));
	}

	@Test
	public void testTransferImage() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		assertAndLogResponseValue(apiClient.transerImage(restoreImageId,
				regionId));
	}

	@Test
	public void testGetAvailableSizes() throws AccessDeniedException,
			ResourceNotFoundException, RequestUnsuccessfulException {
		List<DropletSize> sizes = apiClient.getAvailableSizes();
		assertTrue((sizes.size() > 0));
		assertNotNull(sizes);

		DropletSize size = sizes.get(2);
		LOG.info("Size Id: " + size.getId() + ", Size Name: " + size.getName()
				+ ", Slug: " + size.getSlug());
	}

	private void assertAndLogResponseValue(Response response) {
		assertNotNull(response.getEventId());
		assertNotNull(response.getStatus());

		LOG.info("Response Status: " + response.getStatus() + ", Event Id: "
				+ response.getEventId());
	}

}

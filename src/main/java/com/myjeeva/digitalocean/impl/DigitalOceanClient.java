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
package com.myjeeva.digitalocean.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.common.ApiAction;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
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
 * DigitalOcean API client wrapper methods Implementation
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
@SuppressWarnings("unchecked")
public class DigitalOceanClient extends ClientHelper implements DigitalOcean {

  private final Logger LOG = LoggerFactory.getLogger(DigitalOceanClient.class);

  public DigitalOceanClient(String authToken) {
    this("v2", authToken);
  }

  /**
   * Constructor for initializing DigitalOcean Client
   * 
   * @param apiVersion a {@link String} object
   * @param authToken a {@link String} object
   */
  public DigitalOceanClient(String apiVersion, String authToken) {

    if (!"v2".equalsIgnoreCase(apiVersion)) {
      throw new IllegalArgumentException("Only API version 2 is supported.");
    }

    this.apiVersion = apiVersion;
    this.authToken = authToken;

    // Initializing required variable(s)
    this.httpClient = new DefaultHttpClient(new PoolingClientConnectionManager());
  }

  // =======================================
  // Droplet access/manipulation methods
  // =======================================

  @Override
  public Droplets getAvailableDroplets(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (Droplets) invokeAction(new ApiRequest(ApiAction.AVAILABLE_DROPLETS, pageNo));
  }

  @Override
  public Kernels getAvailableKernels(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Kernels) invokeAction(new ApiRequest(ApiAction.AVAILABLE_DROPLETS_KERNELS, params,
        pageNo));
  }

  @Override
  public Snapshots getAvailableSnapshots(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Snapshots) invokeAction(new ApiRequest(ApiAction.GET_DROPLET_SNAPSHOTS, params, pageNo));
  }

  @Override
  public Backups getAvailableBackups(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Backups) invokeAction(new ApiRequest(ApiAction.GET_DROPLET_BACKUPS, params, pageNo));
  }

  @Override
  public Actions getAvailableActions(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Actions) invokeAction(new ApiRequest(ApiAction.GET_DROPLET_ACTIONS, params, pageNo));
  }

  @Override
  public Droplet getDropletInfo(Integer dropletId) throws DigitalOceanException,
      RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Droplet) invokeAction(new ApiRequest(ApiAction.GET_DROPLET_INFO, params));
  }

  @Override
  public Droplet createDroplet(Droplet droplet) throws DigitalOceanException,
      RequestUnsuccessfulException {
    if (null == droplet || null == droplet.getName() || null == droplet.getRegion()
        || null == droplet.getSize() || null == droplet.getImage()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Region Slug, Size Slug, Image Id]");
    }

    return (Droplet) invokeAction(new ApiRequest(ApiAction.CREATE_DROPLET, droplet));
  }

  @Override
  public Boolean deleteDroplet(Integer dropletId) throws DigitalOceanException,
      RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Boolean) invokeAction(new ApiRequest(ApiAction.DELETE_DROPLET, params));
  }



  // =======================================
  // Images access/manipulation methods
  // =======================================

  @Override
  public Images getAvailableImages(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Images) invokeAction(new ApiRequest(ApiAction.AVAILABLE_IMAGES, pageNo));
  }

  @Override
  public Image getImageInfo(Integer imageId) throws DigitalOceanException,
      RequestUnsuccessfulException {
    checkNullAndThrowError(imageId, "Missing required parameter - imageId.");

    Object[] params = {imageId};
    return (Image) invokeAction(new ApiRequest(ApiAction.GET_IMAGE_INFO, params));
  }

  @Override
  public Image getImageInfo(String slug) throws DigitalOceanException, RequestUnsuccessfulException {
    checkEmptyAndThrowError(slug, "Missing required parameter - slug.");

    Object[] params = {slug};
    return (Image) invokeAction(new ApiRequest(ApiAction.GET_IMAGE_INFO, params));
  }

  @Override
  public Image updateImage(Image image) throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == image || null == image.getName()) {
      throw new IllegalArgumentException("Missing required parameter - image object.");
    }

    Object[] params = {image.getId()};
    return (Image) invokeAction(new ApiRequest(ApiAction.UPDATE_IMAGE_INFO, image, params));
  }
  
  @Override
  public Boolean deleteImage(Integer imageId) throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(imageId, "Missing required parameter - imageId.");

    Object[] params = {imageId};
    return (Boolean) invokeAction(new ApiRequest(ApiAction.DELETE_IMAGE, params));
  }



  // =======================================
  // Regions methods
  // =======================================

  @Override
  public Regions getAvailableRegions(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Regions) invokeAction(new ApiRequest(ApiAction.AVAILABLE_REGIONS, pageNo));
  }


  // =======================================
  // Sizes methods
  // =======================================

  @Override
  public Sizes getAvailableSizes(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Sizes) invokeAction(new ApiRequest(ApiAction.AVAILABLE_SIZES, pageNo));
  }


  // =======================================
  // Domain methods
  // =======================================

  @Override
  public Domains getAvailableDomains(Integer pageNo) throws DigitalOceanException,
      RequestUnsuccessfulException {
    return (Domains) invokeAction(new ApiRequest(ApiAction.AVAILABLE_DOMAINS, pageNo));
  }

  @Override
  public Domain getDomainInfo(String domainName) throws DigitalOceanException,
      RequestUnsuccessfulException {
    checkEmptyAndThrowError(domainName, "Missing required parameter - domainId.");

    Object[] params = {domainName};
    return (Domain) invokeAction(new ApiRequest(ApiAction.GET_DOMAIN_INFO, params));
  }

  @Override
  public Domain createDomain(Domain domain) throws DigitalOceanException,
      RequestUnsuccessfulException {
    checkEmptyAndThrowError(domain.getName(), "Missing required parameter - domainId.");
    checkEmptyAndThrowError(domain.getIpAddress(), "Missing required parameter - ipAddress.");

    return (Domain) invokeAction(new ApiRequest(ApiAction.CREATE_DOMAIN, domain));
  }

  @Override
  public Boolean deleteDomain(String domainName) throws DigitalOceanException,
      RequestUnsuccessfulException {
    checkEmptyAndThrowError(domainName, "Missing required parameter - domainId.");

    Object[] params = {domainName};
    return (Boolean) invokeAction(new ApiRequest(ApiAction.DELETE_DOMAIN, params));
  }



  private Object invokeAction(ApiRequest request) throws DigitalOceanException,
      RequestUnsuccessfulException {
    ApiResponse response = performAction(request);
    return response.getData();
  }

  // =======================================
  // Validation methods
  // =======================================

  private void validateDropletIdAndPageNo(Integer dropletId, Integer pageNo) {
    validateDropletId(dropletId);
    validatePageNo(pageNo);
  }

  private void validateDropletId(Integer dropletId) {
    checkNullAndThrowError(dropletId, "Missing required parameter - dropletId.");
  }

  private void validatePageNo(Integer pageNo) {
    checkNullAndThrowError(pageNo, "Missing required parameter - pageNo.");
  }

  private void checkNullAndThrowError(Integer integer, String msg) {
    if (null == integer) {
      throw new IllegalArgumentException(msg);
    }
  }

  private void checkEmptyAndThrowError(String str, String msg) {
    if (StringUtils.isEmpty(str)) {
      throw new IllegalArgumentException(msg);
    }
  }
}

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

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.common.ApiAction;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.exception.ResourceNotFoundException;
import com.myjeeva.digitalocean.pojo.Actions;
import com.myjeeva.digitalocean.pojo.Backups;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.Domains;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.Images;
import com.myjeeva.digitalocean.pojo.Kernels;
import com.myjeeva.digitalocean.pojo.Key;
import com.myjeeva.digitalocean.pojo.Regions;
import com.myjeeva.digitalocean.pojo.Response;
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
      ResourceNotFoundException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (Droplets) invokeAction(new ApiRequest(ApiAction.AVAILABLE_DROPLETS, pageNo));
  }

  @Override
  public Kernels getAvailableKernels(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Kernels) invokeAction(new ApiRequest(ApiAction.AVAILABLE_DROPLETS_KERNELS, params,
        pageNo));
  }

  @Override
  public Snapshots getAvailableSnapshots(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Snapshots) invokeAction(new ApiRequest(ApiAction.GET_DROPLET_SNAPSHOTS, params, pageNo));
  }

  @Override
  public Backups getAvailableBackups(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Backups) invokeAction(new ApiRequest(ApiAction.GET_DROPLET_BACKUPS, params, pageNo));
  }

  @Override
  public Actions getAvailableActions(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Actions) invokeAction(new ApiRequest(ApiAction.GET_DROPLET_ACTIONS, params, pageNo));
  }

  @Override
  public Droplet getDropletInfo(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Droplet) invokeAction(new ApiRequest(ApiAction.GET_DROPLET_INFO, params));
  }

  @Override
  public Droplet createDroplet(Droplet droplet) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    if (null == droplet || null == droplet.getName() || null == droplet.getRegion()
        || null == droplet.getSize() || null == droplet.getImage()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Region Slug, Size Slug, Image Id]");
    }

    return (Droplet) invokeAction(new ApiRequest(ApiAction.CREATE_DROPLET, droplet));
  }

  @Override
  public Boolean deleteDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Boolean) invokeAction(new ApiRequest(ApiAction.DELETE_DROPLET, params));
  }



  // =======================================
  // Images access/manipulation methods
  // =======================================

  @Override
  public Images getAvailableImages(Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Images) invokeAction(new ApiRequest(ApiAction.AVAILABLE_IMAGES, pageNo));
  }

  @Override
  public Image getImageInfo(Integer imageId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    checkNullAndThrowError(imageId, "Missing required parameter - imageId.");

    Object[] params = {imageId};
    return (Image) invokeAction(new ApiRequest(ApiAction.GET_IMAGE_INFO, params));
  }

  @Override
  public Image getImageInfo(String slug) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {
    checkEmptyAndThrowError(slug, "Missing required parameter - slug.");

    Object[] params = {slug};
    return (Image) invokeAction(new ApiRequest(ApiAction.GET_IMAGE_INFO, params));
  }

  @Override
  public Image updateImage(Image image) throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {
    if (null == image || null == image.getName()) {
      throw new IllegalArgumentException("Missing required parameter - image object.");
    }

    Object[] params = {image.getId()};
    return (Image) invokeAction(new ApiRequest(ApiAction.UPDATE_IMAGE_INFO, image, params));
  }



  // =======================================
  // Regions methods
  // =======================================

  @Override
  public Regions getAvailableRegions(Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Regions) invokeAction(new ApiRequest(ApiAction.AVAILABLE_REGIONS, pageNo));
  }


  // =======================================
  // Sizes methods
  // =======================================

  @Override
  public Sizes getAvailableSizes(Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Sizes) invokeAction(new ApiRequest(ApiAction.AVAILABLE_SIZES, pageNo));
  }


  // =======================================
  // Domain methods
  // =======================================

  @Override
  public Domains getAvailableDomains(Integer pageNo) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    return (Domains) invokeAction(new ApiRequest(ApiAction.AVAILABLE_DOMAINS, pageNo));
  }

  @Override
  public Domain getDomainInfo(String domainName) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    checkEmptyAndThrowError(domainName, "Missing required parameter - domainId.");

    Object[] params = {domainName};
    return (Domain) invokeAction(new ApiRequest(ApiAction.GET_DOMAIN_INFO, params));
  }

  @Override
  public Domain createDomain(Domain domain) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    checkEmptyAndThrowError(domain.getName(), "Missing required parameter - domainId.");
    checkEmptyAndThrowError(domain.getIpAddress(), "Missing required parameter - ipAddress.");

    return (Domain) invokeAction(new ApiRequest(ApiAction.CREATE_DOMAIN, domain));
  }

  @Override
  public Boolean deleteDomain(String domainName) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    checkEmptyAndThrowError(domainName, "Missing required parameter - domainId.");

    Object[] params = {domainName};
    return (Boolean) invokeAction(new ApiRequest(ApiAction.DELETE_DOMAIN, params));
  }



  private Object invokeAction(ApiRequest request) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
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


  // ===========================================================//



  @Override
  public Droplet createDroplet(Droplet droplet, String sshKeyIds) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }



  @Override
  public Response rebootDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response powerCyleDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response shutdownDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response powerOffDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response powerOnDroplet(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response resetDropletPassword(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response resizeDroplet(Integer dropletId, Integer sizeId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response takeDropletSnapshot(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response takeDropletSnapshot(Integer dropletId, String snapshotName)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response restoreDroplet(Integer dropletId, Integer imageId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response rebuildDroplet(Integer dropletId, Integer imageId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response enableDropletBackups(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response disableDropletBackups(Integer dropletId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response renameDroplet(Integer dropletId, String name) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }



  @Override
  public Response deleteImage(Integer imageId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response transferImage(Integer imageId, Integer regionId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Key> getAvailableSshKeys() throws DigitalOceanException, ResourceNotFoundException,
      RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Key getSshKeyInfo(Integer sshKeyId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Key addSshKey(String sshKeyName, String sshPublicKey) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Key editSshKey(Integer sshKeyId, String newSshPublicKey) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response deleteSshKey(Integer sshKeyId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }



  @Override
  public List<DomainRecord> getDomainRecords(Integer domainId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DomainRecord createDomainRecord(DomainRecord domainRecord) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DomainRecord getDomainRecordInfo(Integer domainId, Integer recordId)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public DomainRecord editDomainRecord(DomainRecord domainRecord) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response deleteDomainRecord(Integer domainId, Integer recordId)
      throws DigitalOceanException, ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Response getEventProgress(Integer eventId) throws DigitalOceanException,
      ResourceNotFoundException, RequestUnsuccessfulException {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * @Override public Droplet createDroplet(Droplet droplet) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return createDroplet(droplet, null);
   * }
   * 
   * @Override public Droplet createDroplet(Droplet droplet, String sshKeyIds) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException {
   * 
   * Map<String, String> qp = new HashMap<String, String>(); qp.put(PARAM_NAME, droplet.getName());
   * /* qp.put(PARAM_SIDE_ID, String.valueOf(droplet.getSizeId())); qp.put(PARAM_IMAGE_ID,
   * String.valueOf(droplet.getImageId())); qp.put(PARAM_REGION_ID,
   * String.valueOf(droplet.getRegionId()));
   * 
   * 
   * if (null != sshKeyIds) { qp.put(PARAM_SSH_KEY_IDS, sshKeyIds); }
   * 
   * return (Droplet) processByScope(ApiAction.CREATE_DROPLET, Droplet.class, qp); }
   * 
   * @Override public Droplet getDropletInfo(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (Droplet)
   * processByScope(ApiAction.GET_DROPLET_INFO, Droplet.class, dropletId); }
   * 
   * @Override public Response rebootDroplet(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.REBOOT_DROPLET, dropletId); }
   * 
   * @Override public Response powerCyleDroplet(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.POWER_CYCLE_DROPLET, dropletId); }
   * 
   * @Override public Response shutdownDroplet(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.SHUTDOWN_DROPLET, dropletId); }
   * 
   * @Override public Response powerOffDroplet(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.POWER_OFF_DROPLET, dropletId); }
   * 
   * @Override public Response powerOnDroplet(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.POWER_ON_DROPLET, dropletId); }
   * 
   * @Override public Response resetDropletPassword(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.RESET_PASSWORD_DROPLET, dropletId); }
   * 
   * @Override public Response resizeDroplet(Integer dropletId, Integer sizeId) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Map<String,
   * String> qp = new HashMap<String, String>(); qp.put(PARAM_SIDE_ID, String.valueOf(sizeId));
   * Object[] params = {dropletId};
   * 
   * return process(ApiAction.RESIZE_DROPLET, params, qp); }
   * 
   * @Override public Response takeDropletSnapshot(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return takeDropletSnapshot(dropletId,
   * null); }
   * 
   * @Override public Response takeDropletSnapshot(Integer dropletId, String snapshotName) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Response
   * response = null; if (null == snapshotName) { response =
   * process(ApiAction.TAKE_DROPLET_SNAPSHOT, dropletId); } else { Map<String, String> qp = new
   * HashMap<String, String>(); qp.put(PARAM_NAME, snapshotName); Object[] params = {dropletId};
   * response = process(ApiAction.TAKE_DROPLET_SNAPSHOT, params, qp); }
   * 
   * return response; }
   * 
   * @Override public Response restoreDroplet(Integer dropletId, Integer imageId) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Map<String,
   * String> qp = new HashMap<String, String>(); qp.put(PARAM_IMAGE_ID, String.valueOf(imageId));
   * Object[] params = {dropletId};
   * 
   * return process(ApiAction.RESTORE_DROPLET, params, qp); }
   * 
   * @Override public Response rebuildDroplet(Integer dropletId, Integer imageId) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Map<String,
   * String> qp = new HashMap<String, String>(); qp.put(PARAM_IMAGE_ID, String.valueOf(imageId));
   * Object[] params = {dropletId};
   * 
   * return process(ApiAction.REBUILD_DROPLET, params, qp); }
   * 
   * @Override public Response enableDropletBackups(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.ENABLE_AUTOMATIC_BACKUPS, dropletId); }
   * 
   * @Override public Response disableDropletBackups(Integer dropletId) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.DISABLE_AUTOMATIC_BACKUPS, dropletId); }
   * 
   * @Override public Response renameDroplet(Integer dropletId, String name) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Map<String,
   * String> qp = new HashMap<String, String>(); qp.put(PARAM_NAME, String.valueOf(name)); Object[]
   * params = {dropletId};
   * 
   * return process(ApiAction.RENAME_DROPLET, params, qp); }
   * 
   * @Override public Response deleteDroplet(Integer dropletId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.DELETE_DROPLET, dropletId); }
   * 
   * @Override public List<Region> getAvailableRegions() throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (List<Region>)
   * processByScope(ApiAction.AVAILABLE_REGIONS, TYPE_REGION_LIST); }
   * 
   * @Override public List<Image> getAvailableImages() throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (List<Image>)
   * processByScope(ApiAction.AVAILABLE_IMAGES, TYPE_IMAGE_LIST); }
   * 
   * @Override public Image getImageInfo(Integer imageId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (Image)
   * processByScope(ApiAction.GET_IMAGE_INFO, Image.class, imageId); }
   * 
   * @Override public Response deleteImage(Integer imageId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.DELETE_IMAGE, imageId); }
   * 
   * @Override public Response transferImage(Integer imageId, Integer regionId) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Map<String,
   * String> qp = new HashMap<String, String>(); qp.put(PARAM_REGION_ID, String.valueOf(regionId));
   * Object[] params = {imageId};
   * 
   * return process(ApiAction.TRANSFER_IMAGE, params, qp); }
   * 
   * @Override public List<Key> getAvailableSshKeys() throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (List<Key>)
   * processByScope(ApiAction.AVAILABLE_SSH_KEYS, TYPE_SSH_KEY_LIST); }
   * 
   * @Override public Key getSshKeyInfo(Integer sshKeyId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (Key)
   * processByScope(ApiAction.GET_SSH_KEY, Key.class, sshKeyId); }
   * 
   * @Override public Key addSshKey(String sshKeyName, String sshPublicKey) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Map<String,
   * String> qp = new HashMap<String, String>(); qp.put(PARAM_NAME, sshKeyName);
   * qp.put(PARAM_SSH_PUB_KEY, sshPublicKey);
   * 
   * return (Key) processByScope(ApiAction.CREATE_SSH_KEY, Key.class, qp); }
   * 
   * @Override public Key editSshKey(Integer sshKeyId, String newSshPublicKey) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Map<String,
   * String> qp = new HashMap<String, String>(); qp.put(PARAM_SSH_PUB_KEY, newSshPublicKey);
   * 
   * return (Key) processByScope(ApiAction.EDIT_SSH_KEY, Key.class, qp, sshKeyId); }
   * 
   * @Override public Response deleteSshKey(Integer sshKeyId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.DELETE_SSH_KEY, sshKeyId); }
   * 
   * @Override public List<Size> getAvailableSizes() throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (List<Size>)
   * processByScope(ApiAction.AVAILABLE_SIZES, TYPE_SIZE_LIST); }
   * 
   * @Override public List<Domain> getAvailableDomains() throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (List<Domain>)
   * processByScope(ApiAction.AVAILABLE_DOMAINS, TYPE_DOMAIN_LIST); }
   * 
   * @Override public Domain getDomainInfo(Integer domainId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return (Domain)
   * processByScope(ApiAction.GET_DOMAIN_INFO, Domain.class, domainId); }
   * 
   * @Override public Domain createDomain(String domainName, String ipAddress) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Map<String,
   * String> qp = new HashMap<String, String>(); qp.put(PARAM_NAME, domainName);
   * qp.put(PARAM_IP_ADDRESS, ipAddress);
   * 
   * return (Domain) processByScope(ApiAction.CREATE_DOMAIN, Domain.class, qp); }
   * 
   * @Override public Response deleteDomain(Integer domainId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.DELETE_DOMAIN, domainId); }
   * 
   * @Override public List<DomainRecord> getDomainRecords(Integer domainId) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { return
   * (List<DomainRecord>) processByScope(ApiAction.GET_DOMAIN_RECORDS, TYPE_DOMAIN_RECORD_LIST,
   * domainId); }
   * 
   * @Override public DomainRecord getDomainRecordInfo(Integer domainId, Integer recordId) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Object[]
   * params = {domainId, recordId}; return (DomainRecord)
   * processByScope(ApiAction.GET_DOMAIN_RECORD_INFO, DomainRecord.class, params); }
   * 
   * @Override public DomainRecord createDomainRecord(DomainRecord domainRecord) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { return
   * (DomainRecord) processByScope(ApiAction.CREATE_DOMAIN_RECORD, DomainRecord.class,
   * Helper.prepareDomainRecordParams(domainRecord), domainRecord.getId()); }
   * 
   * @Override public DomainRecord editDomainRecord(DomainRecord domainRecord) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Object[]
   * params = {domainRecord.getId(), domainRecord.getId()}; return (DomainRecord)
   * processByScope(ApiAction.EDIT_DOMAIN_RECORD, DomainRecord.class,
   * Helper.prepareDomainRecordParams(domainRecord), params); }
   * 
   * @Override public Response deleteDomainRecord(Integer domainId, Integer recordId) throws
   * AccessDeniedException, ResourceNotFoundException, RequestUnsuccessfulException { Object[]
   * params = {domainId, recordId}; return process(ApiAction.DELETE_DOMAIN_RECORD, params); }
   * 
   * @Override public Response getEventProgress(Integer eventId) throws AccessDeniedException,
   * ResourceNotFoundException, RequestUnsuccessfulException { return
   * process(ApiAction.GET_EVENT_PROGRESS, eventId); }
   */

}

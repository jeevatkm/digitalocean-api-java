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
package com.myjeeva.digitalocean.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.myjeeva.digitalocean.DigitalOcean;
import com.myjeeva.digitalocean.common.ActionType;
import com.myjeeva.digitalocean.common.ApiAction;
import com.myjeeva.digitalocean.common.Constants;
import com.myjeeva.digitalocean.common.RequestMethod;
import com.myjeeva.digitalocean.exception.DigitalOceanException;
import com.myjeeva.digitalocean.exception.RequestUnsuccessfulException;
import com.myjeeva.digitalocean.http.client.methods.CustomHttpDelete;
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
import com.myjeeva.digitalocean.pojo.DropletAction;
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Firewall;
import com.myjeeva.digitalocean.pojo.Firewalls;
import com.myjeeva.digitalocean.pojo.FloatingIP;
import com.myjeeva.digitalocean.pojo.FloatingIPAction;
import com.myjeeva.digitalocean.pojo.FloatingIPs;
import com.myjeeva.digitalocean.pojo.ForwardingRules;
import com.myjeeva.digitalocean.pojo.HealthCheck;
import com.myjeeva.digitalocean.pojo.Image;
import com.myjeeva.digitalocean.pojo.ImageAction;
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
import com.myjeeva.digitalocean.pojo.Resources;
import com.myjeeva.digitalocean.pojo.Response;
import com.myjeeva.digitalocean.pojo.Sizes;
import com.myjeeva.digitalocean.pojo.Snapshot;
import com.myjeeva.digitalocean.pojo.Snapshots;
import com.myjeeva.digitalocean.pojo.Tag;
import com.myjeeva.digitalocean.pojo.Tags;
import com.myjeeva.digitalocean.pojo.Volume;
import com.myjeeva.digitalocean.pojo.VolumeAction;
import com.myjeeva.digitalocean.pojo.Volumes;
import com.myjeeva.digitalocean.serializer.DropletSerializer;
import com.myjeeva.digitalocean.serializer.FirewallSerializer;
import com.myjeeva.digitalocean.serializer.LoadBalancerSerializer;
import com.myjeeva.digitalocean.serializer.VolumeSerializer;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DigitalOcean API client wrapper methods Implementation
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class DigitalOceanClient implements DigitalOcean, Constants {

  private static final Logger log = LoggerFactory.getLogger(DigitalOceanClient.class);

  /** Http client */
  protected CloseableHttpClient httpClient;

  /** OAuth Authorization Token for Accessing DigitalOcean API */
  protected String authToken;

  /** DigitalOcean API version. defaults to v2 from constructor */
  protected String apiVersion;

  /** DigitalOcean API Host is <code>api.digitalocean.com</code> */
  protected String apiHost = "api.digitalocean.com";

  /** Gson Parser instance for deserialize */
  private Gson deserialize;

  /** Gson Parser instance for serialize */
  private Gson serialize;

  /** API Request Header */
  private Header[] requestHeaders;

  /**
   * DigitalOcean Client Constructor
   *
   * @param authToken a {@link String} object
   */
  public DigitalOceanClient(String authToken) {
    this("v2", authToken);
  }

  /**
   * DigitalOcean Client Constructor
   *
   * @param apiVersion a {@link String} object
   * @param authToken a {@link String} object
   */
  public DigitalOceanClient(String apiVersion, String authToken) {
    this(apiVersion, authToken, null);
  }

  /**
   * DigitalOcean Client Constructor
   *
   * @param apiVersion a {@link String} object
   * @param authToken a {@link String} object
   * @param httpClient a {@link CloseableHttpClient} object
   */
  public DigitalOceanClient(String apiVersion, String authToken, CloseableHttpClient httpClient) {

    if (!"v2".equalsIgnoreCase(apiVersion)) {
      throw new IllegalArgumentException("Only API version 2 is supported.");
    }

    this.apiVersion = apiVersion;
    this.authToken = authToken;
    this.httpClient = httpClient;
    initialize();
  }

  /** @return the httpClient */
  public HttpClient getHttpClient() {
    return httpClient;
  }

  /** @param httpClient the httpClient to set */
  public void setHttpClient(CloseableHttpClient httpClient) {
    this.httpClient = httpClient;
  }

  /** @return the authToken */
  public String getAuthToken() {
    return authToken;
  }

  /** @param authToken the authToken to set */
  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  /** @return the apiVersion */
  public String getApiVersion() {
    return apiVersion;
  }

  /** @param apiVersion the apiVersion to set */
  public void setApiVersion(String apiVersion) {
    this.apiVersion = apiVersion;
  }

  // =======================================
  // Droplet access/manipulation methods
  // =======================================

  @Override
  public Droplets getAvailableDroplets(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (Droplets)
        perform(new ApiRequest(ApiAction.AVAILABLE_DROPLETS, pageNo, perPage)).getData();
  }

  @Override
  public Kernels getDropletKernels(Integer dropletId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Kernels)
        perform(new ApiRequest(ApiAction.GET_DROPLETS_KERNELS, params, pageNo, perPage)).getData();
  }

  @Override
  public Snapshots getDropletSnapshots(Integer dropletId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Snapshots)
        perform(new ApiRequest(ApiAction.GET_DROPLET_SNAPSHOTS, params, pageNo, perPage)).getData();
  }

  @Override
  public Backups getDropletBackups(Integer dropletId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Backups)
        perform(new ApiRequest(ApiAction.GET_DROPLET_BACKUPS, params, pageNo, perPage)).getData();
  }

  @Override
  public Droplet getDropletInfo(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Droplet) perform(new ApiRequest(ApiAction.GET_DROPLET_INFO, params)).getData();
  }

  @Override
  public Droplet createDroplet(Droplet droplet)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == droplet
        || StringUtils.isBlank(droplet.getName())
        || null == droplet.getRegion()
        || null == droplet.getSize()
        || (null == droplet.getImage()
            || (null == droplet.getImage().getId() && null == droplet.getImage().getSlug()))) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Region Slug, Size Slug, Image Id/Slug] for create droplet.");
    }

    return (Droplet) perform(new ApiRequest(ApiAction.CREATE_DROPLET, droplet)).getData();
  }

  @Override
  public Droplets createDroplets(Droplet droplet)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == droplet
        || (null == droplet.getNames() || droplet.getNames().isEmpty())
        || null == droplet.getRegion()
        || null == droplet.getSize()
        || (null == droplet.getImage()
            || (null == droplet.getImage().getId() && null == droplet.getImage().getSlug()))) {
      throw new IllegalArgumentException(
          "Missing required parameters [Names, Region Slug, Size Slug, Image Id/Slug] for creating multiple droplets.");
    }

    if (StringUtils.isNotBlank(droplet.getName())) {
      throw new IllegalArgumentException(
          "Name parameter is not allowed, while creating multiple droplet instead use 'names' attributes.");
    }

    return (Droplets) perform(new ApiRequest(ApiAction.CREATE_DROPLETS, droplet)).getData();
  }

  @Override
  public Delete deleteDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_DROPLET, params)).getData();
  }

  @Override
  public Delete deleteDropletByTagName(String tagName)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(tagName, "Missing required parameter - tagName.");

    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("tag_name", tagName);
    return (Delete)
        perform(new ApiRequest(ApiAction.DELETE_DROPLET_BY_TAG_NAME, null, queryParams, null))
            .getData();
  }

  @Override
  public Droplets getDropletNeighbors(Integer dropletId, Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Droplets)
        perform(new ApiRequest(ApiAction.GET_DROPLET_NEIGHBORS, params, pageNo, null)).getData();
  }

  @Override
  public Neighbors getAllDropletNeighbors(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (Neighbors) perform(new ApiRequest(ApiAction.ALL_DROPLET_NEIGHBORS, pageNo)).getData();
  }

  @Override
  public Droplets getAvailableDropletsByTagName(String tagName, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(tagName, "Missing required parameter - tagName.");
    validatePageNo(pageNo);

    Map<String, String> queryParams = new HashMap<String, String>();
    queryParams.put("tag_name", tagName);

    return (Droplets)
        perform(new ApiRequest(ApiAction.AVAILABLE_DROPLETS, pageNo, queryParams, perPage))
            .getData();
  }

  // Droplet action methods

  @Override
  public Action rebootDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.REBOOT_DROPLET, new DropletAction(ActionType.REBOOT), params))
            .getData();
  }

  @Override
  public Action powerCycleDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.POWER_CYCLE_DROPLET,
                    new DropletAction(ActionType.POWER_CYCLE),
                    params))
            .getData();
  }

  @Override
  public Action shutdownDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.SHUTDOWN_DROPLET, new DropletAction(ActionType.SHUTDOWN), params))
            .getData();
  }

  @Override
  public Action powerOffDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.POWER_OFF_DROPLET, new DropletAction(ActionType.POWER_OFF), params))
            .getData();
  }

  @Override
  public Action powerOnDroplet(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.POWER_ON_DROPLET, new DropletAction(ActionType.POWER_ON), params))
            .getData();
  }

  @Override
  public Action resetDropletPassword(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.RESET_DROPLET_PASSWORD,
                    new DropletAction(ActionType.PASSWORD_RESET),
                    params))
            .getData();
  }

  @Override
  public Action resizeDroplet(Integer dropletId, String size)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    DropletAction action = new DropletAction(ActionType.RESIZE);
    action.setSize(size);
    return (Action) perform(new ApiRequest(ApiAction.RESIZE_DROPLET, action, params)).getData();
  }

  @Override
  public Action resizeDroplet(Integer dropletId, String size, Boolean disk)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    DropletAction action = new DropletAction(ActionType.RESIZE);
    action.setSize(size);
    action.setDisk(disk);
    return (Action) perform(new ApiRequest(ApiAction.RESIZE_DROPLET, action, params)).getData();
  }

  @Override
  public Action takeDropletSnapshot(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.SNAPSHOT_DROPLET, new DropletAction(ActionType.SNAPSHOT), params))
            .getData();
  }

  @Override
  public Action takeDropletSnapshot(Integer dropletId, String snapshotName)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    DropletAction action = new DropletAction(ActionType.SNAPSHOT);
    action.setName(snapshotName);
    return (Action) perform(new ApiRequest(ApiAction.SNAPSHOT_DROPLET, action, params)).getData();
  }

  @Override
  public Action restoreDroplet(Integer dropletId, Integer imageId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    DropletAction action = new DropletAction(ActionType.RESTORE);
    action.setImage(imageId);
    return (Action) perform(new ApiRequest(ApiAction.RESTORE_DROPLET, action, params)).getData();
  }

  @Override
  public Action rebuildDroplet(Integer dropletId, Integer imageId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    DropletAction action = new DropletAction(ActionType.REBUILD);
    action.setImage(imageId);
    return (Action) perform(new ApiRequest(ApiAction.REBUILD_DROPLET, action, params)).getData();
  }

  @Override
  public Action enableDropletBackups(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ENABLE_DROPLET_BACKUPS,
                    new DropletAction(ActionType.ENABLE_BACKUPS),
                    params))
            .getData();
  }

  @Override
  public Action disableDropletBackups(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.DISABLE_DROPLET_BACKUPS,
                    new DropletAction(ActionType.DISABLE_BACKUPS),
                    params))
            .getData();
  }

  @Override
  public Action renameDroplet(Integer dropletId, String name)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    DropletAction action = new DropletAction(ActionType.RENAME);
    action.setName(name);
    return (Action) perform(new ApiRequest(ApiAction.RENAME_DROPLET, action, params)).getData();
  }

  @Override
  public Action changeDropletKernel(Integer dropletId, Integer kernelId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    DropletAction action = new DropletAction(ActionType.CHANGE_KERNEL);
    action.setKernel(kernelId);
    return (Action)
        perform(new ApiRequest(ApiAction.CHANGE_DROPLET_KERNEL, action, params)).getData();
  }

  @Override
  public Action enableDropletIpv6(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ENABLE_DROPLET_IPV6,
                    new DropletAction(ActionType.ENABLE_IPV6),
                    params))
            .getData();
  }

  @Override
  public Action enableDropletPrivateNetworking(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    Object[] params = {dropletId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ENABLE_DROPLET_PRIVATE_NETWORKING,
                    new DropletAction(ActionType.ENABLE_PRIVATE_NETWORKING),
                    params))
            .getData();
  }

  // ==============================================
  // Account manipulation/access methods
  // ==============================================

  @Override
  public Account getAccountInfo() throws DigitalOceanException, RequestUnsuccessfulException {
    return (Account) perform(new ApiRequest(ApiAction.GET_ACCOUNT_INFO)).getData();
  }

  // ==============================================
  // Actions manipulation/access methods
  // ==============================================

  @Override
  public Actions getAvailableActions(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Actions)
        perform(new ApiRequest(ApiAction.AVAILABLE_ACTIONS, pageNo, perPage)).getData();
  }

  @Override
  public Action getActionInfo(Integer actionId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(actionId, "Missing required parameter - actionId");

    Object[] params = {actionId};
    return (Action) perform(new ApiRequest(ApiAction.GET_ACTION_INFO, params)).getData();
  }

  @Override
  public Actions getAvailableDropletActions(Integer dropletId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletIdAndPageNo(dropletId, pageNo);

    Object[] params = {dropletId};
    return (Actions)
        perform(new ApiRequest(ApiAction.GET_DROPLET_ACTIONS, params, pageNo, perPage)).getData();
  }

  @Override
  public Actions getAvailableImageActions(Integer imageId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(imageId, "Missing required parameter - imageId.");
    validatePageNo(pageNo);

    Object[] params = {imageId};
    return (Actions)
        perform(new ApiRequest(ApiAction.GET_IMAGE_ACTIONS, params, pageNo, perPage)).getData();
  }

  @Override
  public Actions getAvailableFloatingIPActions(String ipAddress, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(ipAddress, "Missing required parameter - ipAddress.");
    validatePageNo(pageNo);

    Object[] params = {ipAddress};
    return (Actions)
        perform(new ApiRequest(ApiAction.GET_FLOATING_IP_ACTIONS, params, pageNo, perPage))
            .getData();
  }

  @Override
  public Action getFloatingIPActionInfo(String ipAddress, Integer actionId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(ipAddress, "Missing required parameter - ipAddress.");
    checkNullAndThrowError(actionId, "Missing required parameter - actionId.");

    Object[] params = {ipAddress, actionId};
    return (Action)
        perform(new ApiRequest(ApiAction.GET_FLOATING_IP_ACTION_INFO, params)).getData();
  }

  @Override
  public Actions getAvailableVolumeActions(String volumeId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");

    Object[] params = {volumeId};
    return (Actions) perform(new ApiRequest(ApiAction.GET_VOLUME_ACTIONS, params)).getData();
  }

  @Override
  public Action getVolumeAction(String volumeId, Integer actionId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");
    checkNullAndThrowError(actionId, "Missing required parameter - actionId.");

    Object[] params = {volumeId, actionId};
    return (Action) perform(new ApiRequest(ApiAction.GET_VOLUME_ACTION, params)).getData();
  }

  // =======================================
  // Images access/manipulation methods
  // =======================================

  @Override
  public Images getAvailableImages(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Images) perform(new ApiRequest(ApiAction.AVAILABLE_IMAGES, pageNo, perPage)).getData();
  }

  @Override
  public Images getAvailableImages(Integer pageNo, Integer perPage, ActionType type)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    Map<String, String> qp;
    if (ActionType.DISTRIBUTION.equals(type) || ActionType.APPLICATION.equals(type)) {
      qp = new HashMap<String, String>();
      qp.put("type", type.toString());
    } else {
      throw new DigitalOceanException(
          "Incorrect type value [Allowed: DISTRIBUTION or APPLICATION].");
    }

    return (Images)
        perform(new ApiRequest(ApiAction.AVAILABLE_IMAGES, pageNo, qp, perPage)).getData();
  }

  @Override
  public Images getUserImages(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    Map<String, String> qp = new HashMap<String, String>();
    qp.put("private", "true");
    return (Images)
        perform(new ApiRequest(ApiAction.AVAILABLE_IMAGES, pageNo, qp, perPage)).getData();
  }

  @Override
  public Image getImageInfo(Integer imageId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(imageId, "Missing required parameter - imageId.");

    Object[] params = {imageId};
    return (Image) perform(new ApiRequest(ApiAction.GET_IMAGE_INFO, params)).getData();
  }

  @Override
  public Image getImageInfo(String slug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(slug, "Missing required parameter - slug.");

    Object[] params = {slug};
    return (Image) perform(new ApiRequest(ApiAction.GET_IMAGE_INFO, params)).getData();
  }

  @Override
  public Image createCustomImage(Image image)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == image
        || StringUtils.isBlank(image.getName())
        || StringUtils.isBlank(image.getUrl())
        || StringUtils.isBlank(image.getRegion())) {
      throw new IllegalArgumentException(
          "Missing required parameter to create custom image [name, url, or region].");
    }

    return (Image) perform(new ApiRequest(ApiAction.CREATE_CUSTOM_IMAGE, image)).getData();
  }

  @Override
  public Image updateImage(Image image) throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == image || null == image.getName()) {
      throw new IllegalArgumentException("Missing required parameter - image object.");
    }

    Object[] params = {image.getId()};
    return (Image) perform(new ApiRequest(ApiAction.UPDATE_IMAGE_INFO, image, params)).getData();
  }

  @Override
  public Delete deleteImage(Integer imageId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(imageId, "Missing required parameter - imageId.");

    Object[] params = {imageId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_IMAGE, params)).getData();
  }

  @Override
  public Action transferImage(Integer imageId, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(imageId, "Missing required parameter - imageId.");
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    Object[] params = {imageId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.TRANSFER_IMAGE,
                    new ImageAction(ActionType.TRANSFER, regionSlug),
                    params))
            .getData();
  }

  @Override
  public Action convertImage(Integer imageId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(imageId, "Missing required parameter - imageId.");

    Object[] params = {imageId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.CONVERT_IMAGE, new ImageAction(ActionType.CONVERT), params))
            .getData();
  }

  // =======================================
  // Regions methods
  // =======================================

  @Override
  public Regions getAvailableRegions(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Regions)
        perform(new ApiRequest(ApiAction.AVAILABLE_REGIONS, pageNo, DEFAULT_PAGE_SIZE)).getData();
  }

  // =======================================
  // Sizes methods
  // =======================================

  @Override
  public Sizes getAvailableSizes(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    return (Sizes)
        perform(new ApiRequest(ApiAction.AVAILABLE_SIZES, pageNo, DEFAULT_PAGE_SIZE)).getData();
  }

  // =======================================
  // Domain methods
  // =======================================

  @Override
  public Domains getAvailableDomains(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    return (Domains)
        perform(new ApiRequest(ApiAction.AVAILABLE_DOMAINS, pageNo, DEFAULT_PAGE_SIZE)).getData();
  }

  @Override
  public Domain getDomainInfo(String domainName)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(domainName, "Missing required parameter - domainName.");

    Object[] params = {domainName};
    return (Domain) perform(new ApiRequest(ApiAction.GET_DOMAIN_INFO, params)).getData();
  }

  @Override
  public Domain createDomain(Domain domain)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(domain.getName(), "Missing required parameter - domainName.");
    // #89 - removed the validation in-favor of
    // https://developers.digitalocean.com/documentation/changelog/api-v2/create-domains-without-providing-an-ip-address/
    // checkBlankAndThrowError(domain.getIpAddress(), "Missing required parameter - ipAddress.");

    return (Domain) perform(new ApiRequest(ApiAction.CREATE_DOMAIN, domain)).getData();
  }

  @Override
  public Delete deleteDomain(String domainName)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(domainName, "Missing required parameter - domainName.");

    Object[] params = {domainName};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_DOMAIN, params)).getData();
  }

  @Override
  public DomainRecords getDomainRecords(String domainName, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(domainName, "Missing required parameter - domainName.");

    Object[] params = {domainName};
    return (DomainRecords)
        perform(new ApiRequest(ApiAction.GET_DOMAIN_RECORDS, params, pageNo, perPage)).getData();
  }

  @Override
  public DomainRecord getDomainRecordInfo(String domainName, Integer recordId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(domainName, "Missing required parameter - domainName.");
    checkNullAndThrowError(recordId, "Missing required parameter - recordId.");

    Object[] params = {domainName, recordId};
    return (DomainRecord)
        perform(new ApiRequest(ApiAction.GET_DOMAIN_RECORD_INFO, params)).getData();
  }

  @Override
  public DomainRecord createDomainRecord(String domainName, DomainRecord domainRecord)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(domainName, "Missing required parameter - domainName.");
    checkNullAndThrowError(domainRecord, "Missing required parameter - domainRecord");

    Object[] params = {domainName};
    return (DomainRecord)
        perform(new ApiRequest(ApiAction.CREATE_DOMAIN_RECORD, domainRecord, params)).getData();
  }

  @Override
  public DomainRecord updateDomainRecord(
      String domainName, Integer recordId, DomainRecord domainRecord)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(domainName, "Missing required parameter - domainName.");
    checkNullAndThrowError(recordId, "Missing required parameter - recordId.");
    checkNullAndThrowError(domainRecord, "Missing required parameter - domainRecord");

    Object[] params = {domainName, recordId};
    return (DomainRecord)
        perform(new ApiRequest(ApiAction.UPDATE_DOMAIN_RECORD, domainRecord, params)).getData();
  }

  @Override
  public Delete deleteDomainRecord(String domainName, Integer recordId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(domainName, "Missing required parameter - domainName.");
    checkNullAndThrowError(recordId, "Missing required parameter - recordId.");

    Object[] params = {domainName, recordId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_DOMAIN_RECORD, params)).getData();
  }

  @Override
  public Keys getAvailableKeys(Integer pageNo)
      throws DigitalOceanException, RequestUnsuccessfulException {
    return (Keys)
        perform(new ApiRequest(ApiAction.AVAILABLE_KEYS, pageNo, DEFAULT_PAGE_SIZE)).getData();
  }

  @Override
  public Key getKeyInfo(Integer sshKeyId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(sshKeyId, "Missing required parameter - sshKeyId.");

    Object[] params = {sshKeyId};
    return (Key) perform(new ApiRequest(ApiAction.GET_KEY_INFO, params)).getData();
  }

  @Override
  public Key getKeyInfo(String fingerprint)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(fingerprint, "Missing required parameter - fingerprint.");

    Object[] params = {fingerprint};
    return (Key) perform(new ApiRequest(ApiAction.GET_KEY_INFO, params)).getData();
  }

  @Override
  public Key createKey(Key newKey) throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(newKey, "Missing required parameter - newKey");
    checkBlankAndThrowError(newKey.getName(), "Missing required parameter - name.");
    checkBlankAndThrowError(newKey.getPublicKey(), "Missing required parameter - publicKey.");

    return (Key) perform(new ApiRequest(ApiAction.CREATE_KEY, newKey)).getData();
  }

  @Override
  public Key updateKey(Integer sshKeyId, String newSshKeyName)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(sshKeyId, "Missing required parameter - sshKeyId.");
    checkBlankAndThrowError(newSshKeyName, "Missing required parameter - newSshKeyName.");

    Object[] params = {sshKeyId};
    return (Key)
        perform(new ApiRequest(ApiAction.UPDATE_KEY, new Key(newSshKeyName), params)).getData();
  }

  @Override
  public Key updateKey(String fingerprint, String newSshKeyName)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(fingerprint, "Missing required parameter - fingerprint.");
    checkBlankAndThrowError(newSshKeyName, "Missing required parameter - newSshKeyName.");

    Object[] params = {fingerprint};
    return (Key)
        perform(new ApiRequest(ApiAction.UPDATE_KEY, new Key(newSshKeyName), params)).getData();
  }

  @Override
  public Delete deleteKey(Integer sshKeyId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkNullAndThrowError(sshKeyId, "Missing required parameter - sshKeyId.");

    Object[] params = {sshKeyId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_KEY, params)).getData();
  }

  @Override
  public Delete deleteKey(String fingerprint)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(fingerprint, "Missing required parameter - fingerprint.");

    Object[] params = {fingerprint};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_KEY, params)).getData();
  }

  // =======================================
  // Floating IPs methods
  // =======================================

  @Override
  public FloatingIPs getAvailableFloatingIPs(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (FloatingIPs) perform(new ApiRequest(ApiAction.FLOATING_IPS, pageNo, perPage)).getData();
  }

  @Override
  public FloatingIP createFloatingIP(Integer dropletId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);

    return (FloatingIP)
        perform(new ApiRequest(ApiAction.CREATE_FLOATING_IP, new FloatingIPAction(dropletId)))
            .getData();
  }

  @Override
  public FloatingIP createFloatingIP(String region)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(region, "Missing required parameter - region.");

    return (FloatingIP)
        perform(new ApiRequest(ApiAction.CREATE_FLOATING_IP, new FloatingIPAction(region)))
            .getData();
  }

  @Override
  public FloatingIP getFloatingIPInfo(String ipAddress)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(ipAddress, "Missing required parameter - ipAddress.");

    Object[] params = {ipAddress};
    return (FloatingIP) perform(new ApiRequest(ApiAction.GET_FLOATING_IP_INFO, params)).getData();
  }

  @Override
  public Delete deleteFloatingIP(String ipAddress)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(ipAddress, "Missing required parameter - ipAddress.");

    Object[] params = {ipAddress};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_FLOATING_IP, params)).getData();
  }

  @Override
  public Action assignFloatingIP(Integer dropletId, String ipAddress)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);
    checkBlankAndThrowError(ipAddress, "Missing required parameter - ipAddress.");

    Object[] params = {ipAddress};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ASSIGN_FLOATING_IP,
                    new FloatingIPAction(dropletId, "assign"),
                    params))
            .getData();
  }

  @Override
  public Action unassignFloatingIP(String ipAddress)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(ipAddress, "Missing required parameter - ipAddress.");

    Object[] params = {ipAddress};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.UNASSIGN_FLOATING_IP, new FloatingIPAction(null, "unassign"), params))
            .getData();
  }

  // =======================================
  // Tags methods
  // =======================================

  @Override
  public Tags getAvailableTags(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (Tags) perform(new ApiRequest(ApiAction.AVAILABLE_TAGS, pageNo, perPage)).getData();
  }

  @Override
  public Tag createTag(String name) throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(name, "Missing required parameter - tag name");

    return (Tag) perform(new ApiRequest(ApiAction.CREATE_TAG, new Tag(name))).getData();
  }

  @Override
  public Tag getTag(String name) throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(name, "Missing required parameter - tag name");

    Object[] params = {name};
    return (Tag) perform(new ApiRequest(ApiAction.GET_TAG, params)).getData();
  }

  @Override
  public Delete deleteTag(String name) throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(name, "Missing required parameter - tag name");

    Object[] params = {name};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_TAG, params)).getData();
  }

  @Override
  public Response tagResources(String name, List<Resource> resources)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(name, "Missing required parameter - tag name");
    if (null == resources || resources.isEmpty()) {
      throw new IllegalArgumentException("Missing required parameter - list of resources for tag");
    }

    Object[] params = {name};
    return (Response)
        perform(new ApiRequest(ApiAction.TAG_RESOURCE, new Resources(resources), params)).getData();
  }

  @Override
  public Response untagResources(String name, List<Resource> resources)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(name, "Missing required parameter - tag name");
    if (null == resources || resources.isEmpty()) {
      throw new IllegalArgumentException(
          "Missing required parameter - list of resources for untag");
    }

    Object[] params = {name};
    return (Response)
        perform(new ApiRequest(ApiAction.UNTAG_RESOURCE, new Resources(resources), params))
            .getData();
  }

  // =======================================
  // Volume access/manipulation methods
  // =======================================

  @Override
  public Volumes getAvailableVolumes(String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    Map<String, String> data = new HashMap<String, String>();
    data.put("region", regionSlug);
    return (Volumes) perform(new ApiRequest(ApiAction.AVAILABLE_VOLUMES, data)).getData();
  }

  @Override
  public Volume createVolume(Volume volume)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == volume
        || StringUtils.isBlank(volume.getName())
        || null == volume.getRegion()
        || null == volume.getSize()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Region Slug, Size] for create volume.");
    }

    return (Volume) perform(new ApiRequest(ApiAction.CREATE_VOLUME, volume)).getData();
  }

  @Override
  public Volume getVolumeInfo(String volumeId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");

    Object[] params = {volumeId};
    return (Volume) perform(new ApiRequest(ApiAction.GET_VOLUME_INFO, params)).getData();
  }

  @Override
  public Volumes getVolumeInfo(String volumeName, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(volumeName, "Missing required parameter - volumeName.");
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    Map<String, String> data = new HashMap<String, String>();
    data.put("region", regionSlug);
    data.put("name", volumeName);
    return (Volumes) perform(new ApiRequest(ApiAction.GET_VOLUME_INFO_BY_NAME, data)).getData();
  }

  @Override
  public Delete deleteVolume(String volumeId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");

    Object[] params = {volumeId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_VOLUME, params)).getData();
  }

  @Override
  public Delete deleteVolume(String volumeName, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(volumeName, "Missing required parameter - volumeName.");
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    Map<String, String> data = new HashMap<String, String>();
    data.put("region", regionSlug);
    data.put("name", volumeName);
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_VOLUME_BY_NAME, data)).getData();
  }

  @Override
  public Action attachVolume(Integer dropletId, String volumeId, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    Object[] params = {volumeId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ACTIONS_VOLUME,
                    new VolumeAction(ActionType.ATTACH, dropletId, regionSlug),
                    params))
            .getData();
  }

  @Override
  public Action attachVolumeByName(Integer dropletId, String volumeName, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);
    checkBlankAndThrowError(volumeName, "Missing required parameter - volumeName.");
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ACTIONS_VOLUME_BY_NAME,
                    new VolumeAction(ActionType.ATTACH, dropletId, regionSlug, volumeName, null)))
            .getData();
  }

  @Override
  public Action detachVolume(Integer dropletId, String volumeId, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    Object[] params = {volumeId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ACTIONS_VOLUME,
                    new VolumeAction(ActionType.DETACH, dropletId, regionSlug),
                    params))
            .getData();
  }

  @Override
  public Action detachVolumeByName(Integer dropletId, String volumeName, String regionSlug)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateDropletId(dropletId);
    checkBlankAndThrowError(volumeName, "Missing required parameter - volumeName.");
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ACTIONS_VOLUME_BY_NAME,
                    new VolumeAction(ActionType.DETACH, dropletId, regionSlug, volumeName, null)))
            .getData();
  }

  @Override
  public Action resizeVolume(String volumeId, String regionSlug, Double sizeGigabytes)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");
    checkBlankAndThrowError(regionSlug, "Missing required parameter - regionSlug.");

    if (null == sizeGigabytes) {
      throw new IllegalArgumentException("Missing required parameter - sizeGigabytes.");
    }

    Object[] params = {volumeId};
    return (Action)
        perform(
                new ApiRequest(
                    ApiAction.ACTIONS_VOLUME,
                    new VolumeAction(ActionType.RESIZE, regionSlug, sizeGigabytes),
                    params))
            .getData();
  }

  @Override
  public Snapshots getVolumeSnapshots(String volumeId, Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");

    Object[] params = {volumeId};
    return (Snapshots)
        perform(new ApiRequest(ApiAction.GET_VOLUME_SNAPSHOTS, params, pageNo, perPage)).getData();
  }

  @Override
  public Snapshot takeVolumeSnapshot(String volumeId, String snapshotName)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(volumeId, "Missing required parameter - volumeId.");
    checkBlankAndThrowError(snapshotName, "Missing required parameter - snapshotName.");

    Map<String, String> data = new HashMap<String, String>();
    data.put("name", snapshotName);

    Object[] params = {volumeId};
    return (Snapshot) perform(new ApiRequest(ApiAction.SNAPSHOT_VOLUME, data, params)).getData();
  }

  // ===========================================
  // Snapshots manipulation methods
  // ===========================================

  @Override
  public Snapshots getAvailableSnapshots(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (Snapshots)
        perform(new ApiRequest(ApiAction.AVAILABLE_SNAPSHOTS, pageNo, perPage)).getData();
  }

  @Override
  public Snapshots getAllDropletSnapshots(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    Map<String, String> qp = new HashMap<String, String>();
    qp.put("resource_type", "droplet");

    return (Snapshots)
        perform(new ApiRequest(ApiAction.ALL_DROPLET_SNAPSHOTS, pageNo, qp, perPage)).getData();
  }

  @Override
  public Snapshots getAllVolumeSnapshots(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    Map<String, String> qp = new HashMap<String, String>();
    qp.put("resource_type", "volume");

    return (Snapshots)
        perform(new ApiRequest(ApiAction.ALL_VOLUME_SNAPSHOTS, pageNo, qp, perPage)).getData();
  }

  @Override
  public Snapshot getSnaphotInfo(String snapshotId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateSnapshotId(snapshotId);

    Object[] params = {snapshotId};
    return (Snapshot) perform(new ApiRequest(ApiAction.GET_SNAPSHOT_INFO, params)).getData();
  }

  @Override
  public Delete deleteSnapshot(String snapshotId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateSnapshotId(snapshotId);

    Object[] params = {snapshotId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_SNAPSHOT, params)).getData();
  }

  // ===========================================
  // Load balancers manipulation methods
  // ===========================================

  @Override
  public LoadBalancer createLoadBalancer(LoadBalancer loadBalancer)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == loadBalancer
        || StringUtils.isBlank(loadBalancer.getName())
        || null == loadBalancer.getRegion()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Region Slug] for create loadBalancer.");
    }
    validateForwardingRules(loadBalancer.getForwardingRules());
    validateHealthCheck(loadBalancer.getHealthCheck());

    return (LoadBalancer)
        perform(new ApiRequest(ApiAction.CREATE_LOAD_BALANCER, loadBalancer)).getData();
  }

  @Override
  public LoadBalancer getLoadBalancerInfo(String loadBalancerId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateLoadBalancerId(loadBalancerId);

    Object[] params = {loadBalancerId};
    return (LoadBalancer)
        perform(new ApiRequest(ApiAction.GET_LOAD_BALANCER_INFO, params)).getData();
  }

  @Override
  public LoadBalancers getAvailableLoadBalancers(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (LoadBalancers)
        perform(new ApiRequest(ApiAction.AVAILABLE_LOAD_BALANCERS, pageNo, perPage)).getData();
  }

  @Override
  public LoadBalancer updateLoadBalancer(LoadBalancer loadBalancer)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == loadBalancer
        || StringUtils.isBlank(loadBalancer.getId())
        || StringUtils.isBlank(loadBalancer.getName())
        || null == loadBalancer.getRegion()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Id, Name, Region Slug] for update loadBalancer.");
    }
    validateForwardingRules(loadBalancer.getForwardingRules());
    validateHealthCheck(loadBalancer.getHealthCheck());

    Object[] params = {loadBalancer.getId()};

    return (LoadBalancer)
        perform(new ApiRequest(ApiAction.UPDATE_LOAD_BALANCER, loadBalancer, params)).getData();
  }

  @Override
  public Response addDropletsToLoadBalancer(String loadBalancerId, List<Integer> dropletIds)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateLoadBalancerId(loadBalancerId);

    if (null == dropletIds || dropletIds.isEmpty()) {
      throw new IllegalArgumentException("Missing required parameters [dropletIds].");
    }

    Object[] params = {loadBalancerId};
    Map<String, List<Integer>> data = new HashMap<>();
    data.put("droplet_ids", dropletIds);
    return (Response)
        perform(new ApiRequest(ApiAction.ADD_DROPLET_TO_LOAD_BALANCER, data, params)).getData();
  }

  @Override
  public Delete removeDropletsFromLoadBalancer(String loadBalancerId, List<Integer> dropletIds)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateLoadBalancerId(loadBalancerId);

    if (null == dropletIds || dropletIds.isEmpty()) {
      throw new IllegalArgumentException("Missing required parameters [dropletIds].");
    }

    Object[] params = {loadBalancerId};
    Map<String, List<Integer>> data = new HashMap<>();
    data.put("droplet_ids", dropletIds);
    return (Delete)
        perform(new ApiRequest(ApiAction.REMOVE_DROPLET_FROM_LOAD_BALANCER, data, params))
            .getData();
  }

  @Override
  public Response addForwardingRulesToLoadBalancer(
      String loadBalancerId, List<ForwardingRules> forwardingRules)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateLoadBalancerId(loadBalancerId);

    if (null == forwardingRules || forwardingRules.isEmpty()) {
      throw new IllegalArgumentException("Missing required parameters [forwardingRules].");
    }

    Object[] params = {loadBalancerId};
    Map<String, List<ForwardingRules>> data = new HashMap<>();
    data.put("forwarding_rules", forwardingRules);
    return (Response)
        perform(new ApiRequest(ApiAction.ADD_FORWARDING_RULES_TO_LOAD_BALANCER, data, params))
            .getData();
  }

  @Override
  public Delete removeForwardingRulesFromLoadBalancer(
      String loadBalancerId, List<ForwardingRules> forwardingRules)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateLoadBalancerId(loadBalancerId);

    if (null == forwardingRules || forwardingRules.isEmpty()) {
      throw new IllegalArgumentException("Missing required parameters [forwardingRules].");
    }

    Object[] params = {loadBalancerId};
    Map<String, List<ForwardingRules>> data = new HashMap<>();
    data.put("forwarding_rules", forwardingRules);
    return (Delete)
        perform(new ApiRequest(ApiAction.REMOVE_FORWARDING_RULES_FROM_LOAD_BALANCER, data, params))
            .getData();
  }

  @Override
  public Delete deleteLoadBalancer(String loadBalancerId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validateLoadBalancerId(loadBalancerId);

    Object[] params = {loadBalancerId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_LOAD_BALANCER, params)).getData();
  }

  // ===========================================
  // Certificates manipulation methods
  // ===========================================

  @Override
  public Certificates getAvailableCertificates(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (Certificates)
        perform(new ApiRequest(ApiAction.AVAILABLE_CERTIFICATES, pageNo, perPage)).getData();
  }

  @Override
  public Certificate createCertificate(Certificate certificate)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == certificate
        || StringUtils.isBlank(certificate.getName())
        || StringUtils.isBlank(certificate.getPrivateKey())
        || StringUtils.isBlank(certificate.getLeafCertificate())
        || StringUtils.isBlank(certificate.getCertificateChain())) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Private Key, Leaf Certificate, Certificate Chain] for create certificate.");
    }

    return (Certificate)
        perform(new ApiRequest(ApiAction.CREATE_CERTIFICATE, certificate)).getData();
  }

  @Override
  public Certificate createLetsEncryptCertificate(Certificate certificate)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == certificate
        || StringUtils.isBlank(certificate.getName())
        || StringUtils.isBlank(certificate.getType())
        || certificate.getType() != "lets_encrypt"
        || certificate.getDnsNames() == null
        || certificate.getDnsNames().isEmpty()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Type(lets_encrypt), List of DNS Names] for create Let's Encrypt certificate.");
    }

    return (Certificate)
        perform(new ApiRequest(ApiAction.CREATE_CERTIFICATE, certificate)).getData();
  }

  @Override
  public Certificate getCertificateInfo(String certificateId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(certificateId, "Missing required parameter - certificateId.");

    Object[] params = {certificateId};
    return (Certificate) perform(new ApiRequest(ApiAction.GET_CERTIFICATE_INFO, params)).getData();
  }

  @Override
  public Delete deleteCertificate(String certificateId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(certificateId, "Missing required parameter - certificateId.");

    Object[] params = {certificateId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_CERTIFICATE, params)).getData();
  }

  // ===========================================
  // Firewall manipulation methods
  // ===========================================

  @Override
  public Firewall createFirewall(Firewall firewall)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == firewall
        || StringUtils.isBlank(firewall.getName())
        || firewall.getInboundRules().isEmpty()
        || firewall.getOutboundRules().isEmpty()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Inbound rules, Outbound rules] for create firewall.");
    }
    return (Firewall) perform(new ApiRequest(ApiAction.CREATE_FIREWALL, firewall)).getData();
  }

  @Override
  public Firewall getFirewallInfo(String firewallId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(
        firewallId, "Missing required parameters [FirewallID] for get firewall info.");

    Object[] params = {firewallId};
    return (Firewall) perform(new ApiRequest(ApiAction.GET_FIREWALL_INFO, params)).getData();
  }

  @Override
  public Firewall updateFirewall(Firewall firewall)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == firewall
        || StringUtils.isBlank(firewall.getName())
        || firewall.getInboundRules().isEmpty()
        || firewall.getOutboundRules().isEmpty()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Inbound rules, Outbound rules] for update firewall info.");
    }

    Object[] params = {firewall.getId()};
    return (Firewall)
        perform(new ApiRequest(ApiAction.UPDATE_FIREWALL, firewall, params)).getData();
  }

  @Override
  public Delete deleteFirewall(String firewallId)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(
        firewallId, "Missing required parameters [ID] for delete firewall info.");

    Object[] params = {firewallId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_FIREWALL, params)).getData();
  }

  @Override
  public Response addDropletsToFirewall(String firewallId, List<Integer> dropletIds)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(firewallId, "Missing required parameter [firewallId].");

    if (null == dropletIds || dropletIds.isEmpty()) {
      throw new IllegalArgumentException("Missing required parameters [dropletIds].");
    }

    Object[] params = {firewallId};
    Map<String, List<Integer>> data = new HashMap<>();
    data.put("droplet_ids", dropletIds);
    return (Response)
        perform(new ApiRequest(ApiAction.ADD_DROPLET_TO_FIREWALL, data, params)).getData();
  }

  @Override
  public Delete removeDropletsFromFirewall(String firewallId, List<Integer> dropletIds)
      throws DigitalOceanException, RequestUnsuccessfulException {
    checkBlankAndThrowError(firewallId, "Missing required parameter [firewallId].");

    if (null == dropletIds || dropletIds.isEmpty()) {
      throw new IllegalArgumentException("Missing required parameters [dropletIds].");
    }

    Object[] params = {firewallId};
    Map<String, List<Integer>> data = new HashMap<>();
    data.put("droplet_ids", dropletIds);
    return (Delete)
        perform(new ApiRequest(ApiAction.REMOVE_DROPLET_FROM_FIREWALL, data, params)).getData();
  }

  @Override
  public Firewalls getAvailableFirewalls(Integer pageNo, Integer perPage)
      throws DigitalOceanException, RequestUnsuccessfulException {
    validatePageNo(pageNo);

    return (Firewalls)
        perform(new ApiRequest(ApiAction.AVAILABLE_FIREWALLS, pageNo, perPage)).getData();
  }

  @Override
  public Project createProject(Project project)
      throws DigitalOceanException, RequestUnsuccessfulException {

    if (null == project || StringUtils.isBlank(project.getName()) || null == project.getPurpose()) {
      throw new IllegalArgumentException("Missing required parameters [Name, Purpose].");
    }

    return (Project) perform(new ApiRequest(ApiAction.CREATE_PROJECT, project)).getData();
  }

  @Override
  public Projects getAvailableProjects()
      throws DigitalOceanException, RequestUnsuccessfulException {
    return (Projects) perform(new ApiRequest(ApiAction.GET_ALL_PROJECTS)).getData();
  }

  @Override
  public Project updateProject(Project project)
      throws DigitalOceanException, RequestUnsuccessfulException {

    if (null == project
        || StringUtils.isBlank(project.getName())
        || StringUtils.isBlank(project.getDescription())
        || StringUtils.isBlank(project.getPurpose())) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Description, Purpose].");
    }

    Object[] params = {project.getId()};

    return (Project) perform(new ApiRequest(ApiAction.UPDATE_PROJECT, project, params)).getData();
  }

  @Override
  public Project patchProject(Project project)
      throws DigitalOceanException, RequestUnsuccessfulException {

    if (null == project
        || StringUtils.isBlank(project.getName())
        || StringUtils.isBlank(project.getDescription())
        || StringUtils.isBlank(project.getPurpose())) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Description, Purpose].");
    }

    Object[] params = {project.getId()};

    return (Project) perform(new ApiRequest(ApiAction.PATCH_PROJECT, project, params)).getData();
  }

  @Override
  public Project getProject(String projectId)
      throws DigitalOceanException, RequestUnsuccessfulException {

    validateProjectId(projectId);

    Object[] params = {projectId};
    return (Project) perform(new ApiRequest(ApiAction.GET_PROJECT, params)).getData();
  }

  @Override
  public Project getDefaultProject() throws DigitalOceanException, RequestUnsuccessfulException {
    return (Project) perform(new ApiRequest(ApiAction.GET_DEFAULT_PROJECT)).getData();
  }

  @Override
  public Project updateDefaultProject(Project project)
      throws DigitalOceanException, RequestUnsuccessfulException {

    if (null == project
        || StringUtils.isBlank(project.getName())
        || StringUtils.isBlank(project.getDescription())
        || StringUtils.isBlank(project.getPurpose())) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Description, Purpose].");
    }

    return (Project) perform(new ApiRequest(ApiAction.UPDATE_DEFAULT_PROJECT, project)).getData();
  }

  @Override
  public Project patchDefaultProject(Project project)
      throws DigitalOceanException, RequestUnsuccessfulException {

    if (null == project
        || StringUtils.isBlank(project.getName())
        || StringUtils.isBlank(project.getDescription())
        || StringUtils.isBlank(project.getPurpose())) {
      throw new IllegalArgumentException(
          "Missing required parameters [Name, Description, Purpose].");
    }

    return (Project) perform(new ApiRequest(ApiAction.PATCH_DEFAULT_PROJECT, project)).getData();
  }

  @Override
  public Delete deleteProject(String projectId)
      throws DigitalOceanException, RequestUnsuccessfulException {

    checkBlankAndThrowError(projectId, "Missing required parameter - projectId.");

    Object[] params = {projectId};
    return (Delete) perform(new ApiRequest(ApiAction.DELETE_PROJECT, params)).getData();
  }

  //
  // Private methods
  //

  private ApiResponse perform(ApiRequest request)
      throws DigitalOceanException, RequestUnsuccessfulException {

    URI uri = createUri(request);
    String response = null;

    if (RequestMethod.GET == request.getMethod()) {
      response = doGet(uri);
    } else if (RequestMethod.POST == request.getMethod()) {
      response = doPost(uri, createRequestData(request));
    } else if (RequestMethod.PUT == request.getMethod()) {
      response = doPut(uri, createRequestData(request));
    } else if (RequestMethod.DELETE == request.getMethod()) {
      response = doDelete(uri, createRequestData(request));
    } else if (RequestMethod.PATCH == request.getMethod()) {
      response = doPatch(uri, createRequestData(request));
    }

    ApiResponse apiResponse = new ApiResponse(request.getApiAction(), true);

    try {
      if (request.isCollectionElement()) {
        apiResponse.setData(deserialize.fromJson(response, request.getClazz()));
      } else {
        JsonObject rootObject = JsonParser.parseString(response).getAsJsonObject();
        JsonObject elementObject = rootObject.get(request.getElementName()).getAsJsonObject();
        fetchAddElement(Constants.RATE_LIMIT_ELEMENT_NAME, rootObject, elementObject);
        fetchAddElement(Constants.LINKS_ELEMENT_NAME, rootObject, elementObject);
        fetchAddElement(Constants.META_ELEMENT_NAME, rootObject, elementObject);
        apiResponse.setData(deserialize.fromJson(elementObject, request.getClazz()));
      }
    } catch (JsonSyntaxException jse) {
      log.error("Error occurred while parsing response", jse);
      apiResponse.setRequestSuccess(false);
    }

    log.debug("API Response:: " + apiResponse.toString());

    return apiResponse;
  }

  private void fetchAddElement(String key, JsonObject rootObject, JsonObject elementObject) {
    JsonElement ele = rootObject.get(key);
    if (null != ele) {
      elementObject.add(key, ele);
    }
  }

  private String doGet(URI uri) throws DigitalOceanException, RequestUnsuccessfulException {
    HttpGet get = new HttpGet(uri);
    get.setHeaders(requestHeaders);
    return executeHttpRequest(get);
  }

  private String doPost(URI uri, HttpEntity entity)
      throws DigitalOceanException, RequestUnsuccessfulException {
    HttpPost post = new HttpPost(uri);
    post.setHeaders(requestHeaders);

    if (null != entity) {
      post.setEntity(entity);
    }

    return executeHttpRequest(post);
  }

  private String doPut(URI uri, HttpEntity entity)
      throws DigitalOceanException, RequestUnsuccessfulException {
    HttpPut put = new HttpPut(uri);
    put.setHeaders(requestHeaders);

    if (null != entity) {
      put.setEntity(entity);
    }

    return executeHttpRequest(put);
  }

  private String doDelete(URI uri, HttpEntity entity)
      throws DigitalOceanException, RequestUnsuccessfulException {
    if (null == entity) {
      HttpDelete delete = new HttpDelete(uri);
      delete.setHeaders(requestHeaders);
      delete.setHeader(HttpHeaders.CONTENT_TYPE, JSON_CONTENT_TYPE);
      return executeHttpRequest(delete);
    }

    CustomHttpDelete delete = new CustomHttpDelete(uri);
    delete.setHeaders(requestHeaders);
    delete.setEntity(entity);
    return executeHttpRequest(delete);
  }

  private String doPatch(URI uri, HttpEntity entity)
      throws DigitalOceanException, RequestUnsuccessfulException {
    HttpPatch patch = new HttpPatch(uri);
    patch.setHeaders(requestHeaders);

    if (null != entity) {
      patch.setEntity(entity);
    }

    return executeHttpRequest(patch);
  }

  private String executeHttpRequest(HttpUriRequest request)
      throws DigitalOceanException, RequestUnsuccessfulException {
    log.debug("HTTP Request:: {} {}", request.getMethod(), request.getURI());
    String response = "";
    CloseableHttpResponse httpResponse = null;

    try {
      httpResponse = httpClient.execute(request);
      log.debug("HTTP Response Object:: {}", httpResponse);

      response = appendRateLimitValues(evaluateResponse(httpResponse), httpResponse);
      log.debug("Parsed Response:: {}", response);
    } catch (IOException ioe) {
      throw new RequestUnsuccessfulException(ioe.getMessage(), ioe);
    } finally {
      try {
        if (null != httpResponse) {
          httpResponse.close();
        }
      } catch (IOException e) {
        // Ignoring close exception, really no impact.
        // Since response object is 99.999999% success rate
        // this is nothing to do with DigitalOcean, its
        // typical handling of HttpClient request/response
        log.error("Error occurred while closing a response.", e);
      }
    }

    return response;
  }

  private String evaluateResponse(HttpResponse httpResponse) throws DigitalOceanException {
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    String response = "";

    if (HttpStatus.SC_OK == statusCode
        || HttpStatus.SC_CREATED == statusCode
        || HttpStatus.SC_ACCEPTED == statusCode) {
      response = httpResponseToString(httpResponse);
    } else if (HttpStatus.SC_NO_CONTENT == statusCode) {
      // in a way its always true from client perspective if there is no exception.
      response = String.format(NO_CONTENT_JSON_STRUCT, statusCode);
    }

    if (statusCode >= 400 && statusCode < 510) {
      String jsonStr = httpResponseToString(httpResponse);
      log.debug("JSON Response: {}", jsonStr);

      JsonObject jsonObj = null;
      String errorMsg = StringUtils.EMPTY;
      String id = StringUtils.EMPTY;
      try {
        jsonObj = JsonParser.parseString(jsonStr).getAsJsonObject();
        id = jsonObj.get("id").getAsString();
        errorMsg = jsonObj.get("message").getAsString();
      } catch (JsonSyntaxException e) {
        errorMsg =
            "Digital Oceans server are on maintenance. Wait for official messages "
                + "from digital ocean itself. Such as 'Cloud Control Panel, API & Support Ticket System Unavailable'";
      }

      String errorMsgFull =
          String.format(
              "\nHTTP Status Code: %s\nError Id: %s\nError Message: %s", statusCode, id, errorMsg);
      log.debug(errorMsgFull);

      throw new DigitalOceanException(errorMsg, id, statusCode);
    }

    return response;
  }

  private String httpResponseToString(HttpResponse httpResponse) {
    String response = StringUtils.EMPTY;
    if (null != httpResponse.getEntity()) {
      try {
        response = EntityUtils.toString(httpResponse.getEntity(), UTF_8);
      } catch (ParseException pe) {
        log.error(pe.getMessage(), pe);
      } catch (IOException ioe) {
        log.error(ioe.getMessage(), ioe);
      }
    }
    return response;
  }

  private URI createUri(ApiRequest request) {
    URIBuilder ub = new URIBuilder();
    ub.setScheme(HTTPS_SCHEME);
    ub.setHost(apiHost);
    ub.setPath(createPath(request));

    if (null != request.getPageNo()) {
      ub.setParameter(PARAM_PAGE_NO, request.getPageNo().toString());
    }

    if (RequestMethod.GET == request.getMethod()) {
      if (null == request.getPerPage()) {
        ub.setParameter(PARAM_PER_PAGE, String.valueOf(DEFAULT_PAGE_SIZE)); // As per DO
        // documentation
      } else {
        ub.setParameter(PARAM_PER_PAGE, request.getPerPage().toString());
      }
    }

    if (null != request.getQueryParams()) {
      for (Map.Entry<String, String> entry : request.getQueryParams().entrySet()) {
        ub.setParameter(entry.getKey(), entry.getValue());
      }
    }

    URI uri = null;
    try {
      uri = ub.build();
    } catch (URISyntaxException use) {
      log.error(use.getMessage(), use);
    }

    return uri;
  }

  private String createPath(ApiRequest request) {
    String path = URL_PATH_SEPARATOR + apiVersion + request.getApiAction().getPath();
    return (null == request.getPathParams() ? path : String.format(path, request.getPathParams()));
  }

  private HttpEntity createRequestData(ApiRequest request) {
    StringEntity data = null;

    if (null != request.getData()) {
      String inputData = serialize.toJson(request.getData());
      try {
        data = new StringEntity(inputData);
      } catch (UnsupportedEncodingException e) {
        log.error(e.getMessage(), e);
      }
    }

    return data;
  }

  private String appendRateLimitValues(String response, HttpResponse httpResponse) {
    if (StringUtils.isBlank(response)) {
      return StringUtils.EMPTY;
    }

    // Occasionally the DigitalOcean API will fail to send rate limit headers.
    // Simply omit rate limit data in that case.
    String rateLimit = getSimpleHeaderValue(HDR_RATE_LIMIT, httpResponse);
    String rateRemaining = getSimpleHeaderValue(HDR_RATE_REMAINING, httpResponse);
    String rateReset = getSimpleHeaderValue(HDR_RATE_RESET, httpResponse);
    if (rateLimit == null || rateRemaining == null || rateReset == null) {
      return response;
    }

    String rateLimitData =
        String.format(
            RATE_LIMIT_JSON_STRUCT,
            rateLimit,
            rateRemaining,
            getDateString(rateReset, DATE_FORMAT));
    log.debug("RateLimitData:: {}", rateLimitData);

    return StringUtils.substringBeforeLast(response, "}") + ", " + rateLimitData + "}";
  }

  private String getDateString(String epochString, String dateFormat) {
    long epoch = Long.parseLong(epochString);
    Date expiry = new Date(epoch * 1000);

    SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
    String dateString = formatter.format(expiry);
    log.debug(dateString);

    return dateString;
  }

  /** Easy method for HTTP header values (first/last) */
  private String getSimpleHeaderValue(String header, HttpResponse httpResponse, boolean first) {
    if (StringUtils.isBlank(header)) {
      return StringUtils.EMPTY;
    }

    Header h;
    if (first) {
      h = httpResponse.getFirstHeader(header);
    } else {
      h = httpResponse.getLastHeader(header);
    }
    if (h == null) {
      return null;
    }
    return h.getValue();
  }

  /** Easy method for HTTP header values. defaults to first one. */
  private String getSimpleHeaderValue(String header, HttpResponse httpResponse) {
    return getSimpleHeaderValue(header, httpResponse, true);
  }

  // =======================================
  // Validation methods
  // =======================================

  private void validateDropletIdAndPageNo(Integer dropletId, Integer pageNo) {
    validateDropletId(dropletId);
    validatePageNo(pageNo);
  }

  private void validateSnapshotId(String snapshotId) {
    checkBlankAndThrowError(snapshotId, "Missing required parameter - snapshotId.");
  }

  private void validateDropletId(Integer dropletId) {
    checkNullAndThrowError(dropletId, "Missing required parameter - dropletId.");
  }

  private void validateLoadBalancerId(String loadBalancerId) {
    checkBlankAndThrowError(loadBalancerId, "Missing required parameter - loadBalancerId.");
  }

  private void validatePageNo(Integer pageNo) {
    checkNullAndThrowError(pageNo, "Missing required parameter - pageNo.");
  }

  private void validateProjectId(String projectId) {
    checkNullAndThrowError(projectId, "Missing required parameter - projectId.");
  }

  private void checkNullAndThrowError(Object obj, String msg) {
    if (null == obj) {
      log.error(msg);
      throw new IllegalArgumentException(msg);
    }
  }

  // It checks for null, whitespace and length
  private void checkBlankAndThrowError(String str, String msg) {
    if (StringUtils.isBlank(str)) {
      log.error(msg);
      throw new IllegalArgumentException(msg);
    }
  }

  private void validateForwardingRules(List<ForwardingRules> rules) {
    if (null == rules || rules.isEmpty()) {
      throw new IllegalArgumentException("Missing required parameters [ForwardingRules]");
    }

    for (ForwardingRules rule : rules) validateForwardingRule(rule);
  }

  private void validateForwardingRule(ForwardingRules rule) {
    if (null == rule
        || null == rule.getEntryProtocol()
        || null == rule.getEntryPort()
        || null == rule.getTargetProtocol()
        || null == rule.getTargetPort()) {
      throw new IllegalArgumentException(
          "Missing required parameters [Entry Protocol, Entry Port, Target Protocol, Target Port] for forwarding rules.");
    }
  }

  private void validateHealthCheck(HealthCheck healthCheck) {
    if (null != healthCheck
        && (null == healthCheck.getProtocol() || null == healthCheck.getPort())) {
      throw new IllegalArgumentException(
          "Missing required parameters [Protocol, Port] for health check");
    }
  }

  private void initialize() {
    this.deserialize = new GsonBuilder().setDateFormat(DATE_FORMAT).create();

    this.serialize =
        new GsonBuilder()
            .setDateFormat(DATE_FORMAT)
            .registerTypeAdapter(Droplet.class, new DropletSerializer())
            .registerTypeAdapter(Volume.class, new VolumeSerializer())
            .registerTypeAdapter(LoadBalancer.class, new LoadBalancerSerializer())
            .registerTypeAdapter(Firewall.class, new FirewallSerializer())
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    Header[] headers = {
      new BasicHeader(HDR_USER_AGENT, USER_AGENT),
      new BasicHeader(HDR_CONTENT_TYPE, JSON_CONTENT_TYPE),
      new BasicHeader(HDR_AUTHORIZATION, "Bearer " + authToken)
    };
    log.debug("API Request Headers:: " + headers);

    this.requestHeaders = headers;

    if (null == this.httpClient) {
      this.httpClient = HttpClients.createDefault();
    }
  }
}

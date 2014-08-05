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
package com.myjeeva.digitalocean.common;

import com.myjeeva.digitalocean.pojo.Action;
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
 * Enumeration of DigitalOcean API and its path and element name
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * 
 * @since v2.0
 */
public enum ApiAction {

  // Droplet
  AVAILABLE_DROPLETS("/droplets", "droplets", RequestMethod.GET, Droplets.class),
  AVAILABLE_DROPLETS_KERNELS("/droplets/%s/kernels", "kernels", RequestMethod.GET, Kernels.class),
  GET_DROPLET_SNAPSHOTS("/droplets/%s/snapshots", "snapshots", RequestMethod.GET, Snapshots.class),
  GET_DROPLET_BACKUPS("/droplets/%s/backups", "backups", RequestMethod.GET, Backups.class),
  GET_DROPLET_ACTIONS("/droplets/%s/actions", "actions", RequestMethod.GET, Actions.class),
  GET_DROPLET_INFO("/droplets/%s", "droplet", RequestMethod.GET, Droplet.class),
  CREATE_DROPLET("/droplets", "droplet", RequestMethod.POST, Droplet.class),
  DELETE_DROPLET("/droplets/%s", RequestMethod.DELETE),
  REBOOT_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  POWER_CYCLE_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  SHUTDOWN_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  POWER_OFF_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  POWER_ON_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class), 
  RESET_DROPLET_PASSWORD("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  RESIZE_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  RESTORE_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  REBUILD_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  RENAME_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  CHANGE_DROPLET_KERNEL("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  ENABLE_DROPLET_IPV6("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  DISABLE_DROPLET_BACKUPS("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  ENABLE_DROPLET_PRIVATE_NETWORKING("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  SNAPSHOT_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  
  
  // Image
  AVAILABLE_IMAGES("/images", "images", RequestMethod.GET, Images.class),
  GET_IMAGE_INFO("/images/%s", "image", RequestMethod.GET, Image.class),
  UPDATE_IMAGE_INFO("/images/%s", "image", RequestMethod.PUT, Image.class),
  DELETE_IMAGE("/images/%s", RequestMethod.DELETE),
  
  
  // Region
  AVAILABLE_REGIONS("/regions", "regions", RequestMethod.GET, Regions.class),
  
  
  // Sizes
  AVAILABLE_SIZES("/sizes", "sizes", RequestMethod.GET, Sizes.class),
  
  
  // Domains
  AVAILABLE_DOMAINS("/domains", "domains", RequestMethod.GET, Domains.class),
  GET_DOMAIN_INFO("/domains/%s", "domain", RequestMethod.GET, Domain.class),
  CREATE_DOMAIN("/domains", "domain", RequestMethod.POST, Domain.class),  
  DELETE_DOMAIN("/domains/%s", RequestMethod.DELETE),
  
  
  // Domain Record
  
  
  /*  
  REBOOT_DROPLET("/droplets/%s/reboot/"),
  POWER_CYCLE_DROPLET("/droplets/%s/power_cycle/"),
  SHUTDOWN_DROPLET("/droplets/%s/shutdown/"),
  POWER_OFF_DROPLET("/droplets/%s/power_off/"),
  POWER_ON_DROPLET("/droplets/%s/power_on/"), 
  RESET_PASSWORD_DROPLET("/droplets/%s/password_reset/"),
  RESIZE_DROPLET("/droplets/%s/resize/", ""),
  TAKE_DROPLET_SNAPSHOT("/droplets/%s/snapshot/"),
  RESTORE_DROPLET("/droplets/%s/resize/"),
  REBUILD_DROPLET("/droplets/%s/rebuild/"),
  ENABLE_AUTOMATIC_BACKUPS("/droplets/%s/enable_backups/"),
  DISABLE_AUTOMATIC_BACKUPS("/droplets/%s/disable_backups/"),
  RENAME_DROPLET("/droplets/%s/rename/"),
  DELETE_DROPLET("/droplets/%s/destroy/"),
  AVAILABLE_REGIONS("/regions/", "regions"),
  AVAILABLE_IMAGES("/images/", "images"),
  GET_IMAGE_INFO("/images/%s/", "image"),
  DELETE_IMAGE("/images/%s/destroy/"),
  TRANSFER_IMAGE("/images/%s/transfer/"),
  AVAILABLE_SSH_KEYS("/ssh_keys/", "ssh_keys"),
  CREATE_SSH_KEY("/ssh_keys/new/", "ssh_key"),
  GET_SSH_KEY("/ssh_keys/%s/", "ssh_key"),
  EDIT_SSH_KEY("/ssh_keys/%s/edit/", "ssh_key"),
  DELETE_SSH_KEY("/ssh_keys/%s/destroy/"),
  AVAILABLE_SIZES("/sizes/", "sizes"),
  AVAILABLE_DOMAINS("/domains/", "domains"),
  CREATE_DOMAIN("/domains/new", "domain"),
  GET_DOMAIN_INFO("/domains/%s/", "domain"),
  DELETE_DOMAIN("/domains/%s/destroy/"),
  GET_DOMAIN_RECORDS("/domains/%s/records/", "records"),
  CREATE_DOMAIN_RECORD("/domains/%s/records/new/", "record"),
  GET_DOMAIN_RECORD_INFO("/domains/%s/records/%s/", "record"),
  EDIT_DOMAIN_RECORD("/domains/%s/records/%s/edit/", "record"),
  DELETE_DOMAIN_RECORD("/domains/%s/records/%s/destroy/"),
  GET_EVENT_PROGRESS("/events/%s/", "event")*/;    
   
  private String path;

  private String elementName;
  
  private RequestMethod method;

  private Class<?> clazz;

  ApiAction(String path, RequestMethod method) {
    this(path, null, method);
  }

  ApiAction(String path, String elementName) {
    this(path, elementName, RequestMethod.GET);
  }

  ApiAction(String path, String elementName, RequestMethod method) {
    this(path, elementName, method, null);
  }
  
  ApiAction(String path, String elementName, RequestMethod method, Class<?> clazz) {
    this.path = path;
    this.elementName = elementName;
    this.method = method;
    this.clazz = clazz;
  }

  /**
   * @return the path
   */
  public String getPath() {
    return path;
  }

  /**
   * @return the elementName
   */
  public String getElementName() {
    return elementName;
  }

  /**
   * @return the method
   */
  public RequestMethod getMethod() {
    return method;
  }

  /**
   * @return the clazz
   */
  public Class<?> getClazz() {
    return clazz;
  }
}

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
package com.myjeeva.digitalocean.common;

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
import com.myjeeva.digitalocean.pojo.Droplets;
import com.myjeeva.digitalocean.pojo.Firewall;
import com.myjeeva.digitalocean.pojo.Firewalls;
import com.myjeeva.digitalocean.pojo.FloatingIP;
import com.myjeeva.digitalocean.pojo.FloatingIPs;
import com.myjeeva.digitalocean.pojo.Image;
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
import com.myjeeva.digitalocean.pojo.Response;
import com.myjeeva.digitalocean.pojo.Sizes;
import com.myjeeva.digitalocean.pojo.Snapshot;
import com.myjeeva.digitalocean.pojo.Snapshots;
import com.myjeeva.digitalocean.pojo.Tag;
import com.myjeeva.digitalocean.pojo.Tags;
import com.myjeeva.digitalocean.pojo.Volume;
import com.myjeeva.digitalocean.pojo.Volumes;

/**
 * Enumeration of DigitalOcean RESTful resource information.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public enum ApiAction {

  // Droplet
  AVAILABLE_DROPLETS("/droplets", "droplets", RequestMethod.GET, Droplets.class),
  GET_DROPLETS_KERNELS("/droplets/%s/kernels", "kernels", RequestMethod.GET, Kernels.class),
  GET_DROPLET_SNAPSHOTS("/droplets/%s/snapshots", "snapshots", RequestMethod.GET, Snapshots.class),
  GET_DROPLET_BACKUPS("/droplets/%s/backups", "backups", RequestMethod.GET, Backups.class),
  GET_DROPLET_NEIGHBORS("/droplets/%s/neighbors", "droplets", RequestMethod.GET, Droplets.class),
  GET_DROPLET_INFO("/droplets/%s", "droplet", RequestMethod.GET, Droplet.class),
  CREATE_DROPLET("/droplets", "droplet", RequestMethod.POST, Droplet.class),
  CREATE_DROPLETS("/droplets", "droplets", RequestMethod.POST, Droplets.class),
  DELETE_DROPLET("/droplets/%s", "response", RequestMethod.DELETE, Delete.class),
  DELETE_DROPLET_BY_TAG_NAME("/droplets", "response", RequestMethod.DELETE, Delete.class),
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
  ENABLE_DROPLET_BACKUPS("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  DISABLE_DROPLET_BACKUPS("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  ENABLE_DROPLET_PRIVATE_NETWORKING(
      "/droplets/%s/actions", "action", RequestMethod.POST, Action.class),
  SNAPSHOT_DROPLET("/droplets/%s/actions", "action", RequestMethod.POST, Action.class),

  // Account
  GET_ACCOUNT_INFO("/account", "account", RequestMethod.GET, Account.class),

  // Reports
  ALL_DROPLET_NEIGHBORS(
      "/reports/droplet_neighbors", "neighbors", RequestMethod.GET, Neighbors.class),

  // Action
  AVAILABLE_ACTIONS("/actions", "actions", RequestMethod.GET, Actions.class),
  GET_ACTION_INFO("/actions/%s", "action", RequestMethod.GET, Action.class),
  GET_DROPLET_ACTIONS("/droplets/%s/actions", "actions", RequestMethod.GET, Actions.class),
  GET_IMAGE_ACTIONS("/images/%s/actions", "actions", RequestMethod.GET, Actions.class),
  GET_FLOATING_IP_ACTIONS("/floating_ips/%s/actions", "actions", RequestMethod.GET, Actions.class),
  GET_FLOATING_IP_ACTION_INFO(
      "/floating_ips/%s/actions/%s", "action", RequestMethod.GET, Action.class),
  GET_VOLUME_ACTIONS("/volumes/%s/actions", "actions", RequestMethod.GET, Actions.class),
  GET_VOLUME_ACTION("/volumes/%s/actions/%s", "action", RequestMethod.GET, Action.class),

  // Image
  AVAILABLE_IMAGES("/images", "images", RequestMethod.GET, Images.class),
  GET_IMAGE_INFO("/images/%s", "image", RequestMethod.GET, Image.class),
  CREATE_CUSTOM_IMAGE("/images", "image", RequestMethod.POST, Image.class),
  UPDATE_IMAGE_INFO("/images/%s", "image", RequestMethod.PUT, Image.class),
  DELETE_IMAGE("/images/%s", "response", RequestMethod.DELETE, Delete.class),
  TRANSFER_IMAGE("/images/%s/actions", "action", RequestMethod.POST, Action.class),
  CONVERT_IMAGE("/images/%s/actions", "action", RequestMethod.POST, Action.class),

  // Region
  AVAILABLE_REGIONS("/regions", "regions", RequestMethod.GET, Regions.class),

  // Size
  AVAILABLE_SIZES("/sizes", "sizes", RequestMethod.GET, Sizes.class),

  // Domain
  AVAILABLE_DOMAINS("/domains", "domains", RequestMethod.GET, Domains.class),
  GET_DOMAIN_INFO("/domains/%s", "domain", RequestMethod.GET, Domain.class),
  CREATE_DOMAIN("/domains", "domain", RequestMethod.POST, Domain.class),
  DELETE_DOMAIN("/domains/%s", "response", RequestMethod.DELETE, Delete.class),

  // Domain Record
  GET_DOMAIN_RECORDS(
      "/domains/%s/records", "domain_records", RequestMethod.GET, DomainRecords.class),
  GET_DOMAIN_RECORD_INFO(
      "/domains/%s/records/%s", "domain_record", RequestMethod.GET, DomainRecord.class),
  CREATE_DOMAIN_RECORD(
      "/domains/%s/records", "domain_record", RequestMethod.POST, DomainRecord.class),
  UPDATE_DOMAIN_RECORD(
      "/domains/%s/records/%s", "domain_record", RequestMethod.PUT, DomainRecord.class),
  DELETE_DOMAIN_RECORD("/domains/%s/records/%s", "response", RequestMethod.DELETE, Delete.class),

  // Key
  AVAILABLE_KEYS("/account/keys", "ssh_keys", RequestMethod.GET, Keys.class),
  GET_KEY_INFO("/account/keys/%s", "ssh_key", RequestMethod.GET, Key.class),
  CREATE_KEY("/account/keys", "ssh_key", RequestMethod.POST, Key.class),
  UPDATE_KEY("/account/keys/%s", "ssh_key", RequestMethod.PUT, Key.class),
  DELETE_KEY("/account/keys/%s", "response", RequestMethod.DELETE, Delete.class),

  // Floating IP
  FLOATING_IPS("/floating_ips", "floating_ips", RequestMethod.GET, FloatingIPs.class),
  CREATE_FLOATING_IP("/floating_ips", "floating_ip", RequestMethod.POST, FloatingIP.class),
  GET_FLOATING_IP_INFO("/floating_ips/%s", "floating_ip", RequestMethod.GET, FloatingIP.class),
  DELETE_FLOATING_IP("/floating_ips/%s", "response", RequestMethod.DELETE, Delete.class),
  ASSIGN_FLOATING_IP("/floating_ips/%s/actions", "action", RequestMethod.POST, Action.class),
  UNASSIGN_FLOATING_IP("/floating_ips/%s/actions", "action", RequestMethod.POST, Action.class),

  // Tags
  AVAILABLE_TAGS("/tags", "tags", RequestMethod.GET, Tags.class),
  CREATE_TAG("/tags", "tag", RequestMethod.POST, Tag.class),
  GET_TAG("/tags/%s", "tag", RequestMethod.GET, Tag.class),
  DELETE_TAG("/tags/%s", "response", RequestMethod.DELETE, Delete.class),
  TAG_RESOURCE("/tags/%s/resources", "response", RequestMethod.POST, Response.class),
  UNTAG_RESOURCE("/tags/%s/resources", "response", RequestMethod.DELETE, Response.class),

  // Volumes
  AVAILABLE_VOLUMES("/volumes", "volumes", RequestMethod.GET, Volumes.class),
  CREATE_VOLUME("/volumes", "volume", RequestMethod.POST, Volume.class),
  GET_VOLUME_INFO("/volumes/%s", "volume", RequestMethod.GET, Volume.class),
  GET_VOLUME_INFO_BY_NAME("/volumes", "volumes", RequestMethod.GET, Volumes.class),
  DELETE_VOLUME("/volumes/%s", "response", RequestMethod.DELETE, Delete.class),
  DELETE_VOLUME_BY_NAME("/volumes", "response", RequestMethod.DELETE, Delete.class),
  ACTIONS_VOLUME("/volumes/%s/actions", "action", RequestMethod.POST, Action.class),
  ACTIONS_VOLUME_BY_NAME("/volumes/actions", "action", RequestMethod.POST, Action.class),
  GET_VOLUME_SNAPSHOTS("/volumes/%s/snapshots", "snapshots", RequestMethod.GET, Snapshots.class),
  SNAPSHOT_VOLUME("/volumes/%s/snapshots", "snapshot", RequestMethod.POST, Snapshot.class),

  // Snapshots
  AVAILABLE_SNAPSHOTS("/snapshots", "snapshots", RequestMethod.GET, Snapshots.class),
  ALL_DROPLET_SNAPSHOTS("/snapshots", "snapshots", RequestMethod.GET, Snapshots.class),
  ALL_VOLUME_SNAPSHOTS("/snapshots", "snapshots", RequestMethod.GET, Snapshots.class),
  GET_SNAPSHOT_INFO("/snapshots/%s", "snapshot", RequestMethod.GET, Snapshot.class),
  DELETE_SNAPSHOT("/snapshots/%s", "response", RequestMethod.DELETE, Delete.class),

  // Projects
  CREATE_PROJECT("/projects", "project", RequestMethod.POST, Project.class),
  GET_ALL_PROJECTS("/projects", "projects", RequestMethod.GET, Projects.class),
  UPDATE_PROJECT("/projects/%s", "project", RequestMethod.PUT, Project.class),
  PATCH_PROJECT("/projects/%s", "project", RequestMethod.PATCH, Project.class),
  GET_PROJECT("/projects/%s", "project", RequestMethod.GET, Project.class),
  GET_DEFAULT_PROJECT("/projects/default", "project", RequestMethod.GET, Project.class),
  UPDATE_DEFAULT_PROJECT("/projects/default", "project", RequestMethod.PUT, Project.class),
  PATCH_DEFAULT_PROJECT("/projects/default", "project", RequestMethod.PATCH, Project.class),
  DELETE_PROJECT("/projects/%s", "response", RequestMethod.DELETE, Delete.class),

  // Load Balancers
  CREATE_LOAD_BALANCER("/load_balancers", "load_balancer", RequestMethod.POST, LoadBalancer.class),
  GET_LOAD_BALANCER_INFO(
      "/load_balancers/%s", "load_balancer", RequestMethod.GET, LoadBalancer.class),
  AVAILABLE_LOAD_BALANCERS(
      "/load_balancers", "load_balancers", RequestMethod.GET, LoadBalancers.class),
  UPDATE_LOAD_BALANCER(
      "/load_balancers/%s", "load_balancer", RequestMethod.PUT, LoadBalancer.class),
  ADD_DROPLET_TO_LOAD_BALANCER(
      "/load_balancers/%s/droplets", "response", RequestMethod.POST, Response.class),
  REMOVE_DROPLET_FROM_LOAD_BALANCER(
      "/load_balancers/%s/droplets", "response", RequestMethod.DELETE, Delete.class),
  ADD_FORWARDING_RULES_TO_LOAD_BALANCER(
      "/load_balancers/%s/forwarding_rules", "response", RequestMethod.POST, Response.class),
  REMOVE_FORWARDING_RULES_FROM_LOAD_BALANCER(
      "/load_balancers/%s/forwarding_rules", "response", RequestMethod.DELETE, Delete.class),
  DELETE_LOAD_BALANCER("/load_balancers/%s", "response", RequestMethod.DELETE, Delete.class),

  // Certificates
  AVAILABLE_CERTIFICATES("/certificates", "certificates", RequestMethod.GET, Certificates.class),
  GET_CERTIFICATE_INFO("/certificates/%s", "certificate", RequestMethod.GET, Certificate.class),
  CREATE_CERTIFICATE("/certificates", "certificate", RequestMethod.POST, Certificate.class),
  DELETE_CERTIFICATE("/certificates/%s", "response", RequestMethod.DELETE, Delete.class),

  // Firewalls
  CREATE_FIREWALL("/firewalls", "firewall", RequestMethod.POST, Firewall.class),
  GET_FIREWALL_INFO("/firewalls/%s", "firewall", RequestMethod.GET, Firewall.class),
  UPDATE_FIREWALL("/firewalls/%s", "firewall", RequestMethod.PUT, Firewall.class),
  DELETE_FIREWALL("/firewalls/%s", "response", RequestMethod.DELETE, Delete.class),
  AVAILABLE_FIREWALLS("/firewalls", "firewalls", RequestMethod.GET, Firewalls.class),
  ADD_DROPLET_TO_FIREWALL("/firewalls/%s/droplets", "response", RequestMethod.POST, Response.class),
  REMOVE_DROPLET_FROM_FIREWALL(
      "/firewalls/%s/droplets", "response", RequestMethod.DELETE, Delete.class);

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

  /** @return the path */
  public String getPath() {
    return path;
  }

  /** @return the elementName */
  public String getElementName() {
    return elementName;
  }

  /** @return the method */
  public RequestMethod getMethod() {
    return method;
  }

  /** @return the clazz */
  public Class<?> getClazz() {
    return clazz;
  }
}

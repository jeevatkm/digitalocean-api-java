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
package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myjeeva.digitalocean.common.LoadBalancerStatus;
import com.myjeeva.digitalocean.common.LoadBalancingAlgorithm;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Load Balancer object
 *
 * @author Thomas Lehoux (https://github.com/tlehoux)
 * @since v2.11
 */
public class LoadBalancer extends Base {

  private static final long serialVersionUID = -442836096026412279L;

  private String id;

  @Expose private String name;

  private String ip;

  @Expose private LoadBalancingAlgorithm algorithm;

  private Region region;

  private LoadBalancerStatus status;

  @SerializedName("created_at")
  private Date createdDate;

  @SerializedName("forwarding_rules")
  private List<ForwardingRules> forwardingRules;

  @SerializedName("health_check")
  private HealthCheck healthCheck;

  @SerializedName("sticky_sessions")
  private StickySessions stickySessions;

  @SerializedName("redirect_http_to_https")
  private boolean redirectHttpToHttps;

  @SerializedName("droplet_ids")
  private List<String> dropletIds;

  @Expose private String tag;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the ip */
  public String getIp() {
    return ip;
  }

  /** @param ip the ip to set */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /** @return the id */
  public String getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(String id) {
    this.id = id;
  }

  /** @return the name */
  public String getName() {
    return name;
  }

  /** @param name the name to set */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the algorithm */
  public LoadBalancingAlgorithm getAlgorithm() {
    return algorithm;
  }

  /** @param algorithm the algorithm to set */
  public void setAlgorithm(LoadBalancingAlgorithm algorithm) {
    this.algorithm = algorithm;
  }

  /** @return the region */
  public Region getRegion() {
    return region;
  }

  /** @param region the region to set */
  public void setRegion(Region region) {
    this.region = region;
  }

  /** @return the status */
  public LoadBalancerStatus getStatus() {
    return status;
  }

  /** @param status the status to set */
  public void setStatus(LoadBalancerStatus status) {
    this.status = status;
  }

  /** @return the created date */
  public Date getCreatedDate() {
    return createdDate;
  }

  /** @param createdDate the createdDate to set */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /** @return the forwarding rules */
  public List<ForwardingRules> getForwardingRules() {
    return forwardingRules;
  }

  /** @param forwardingRules the forwardingRules to set */
  public void setForwardingRules(List<ForwardingRules> forwardingRules) {
    this.forwardingRules = forwardingRules;
  }

  /** @return the healthcheck */
  public HealthCheck getHealthCheck() {
    return healthCheck;
  }

  /** @param healthCheck the healthCheck to set */
  public void setHealthCheck(HealthCheck healthCheck) {
    this.healthCheck = healthCheck;
  }

  /** @return the sticky sessions */
  public StickySessions getStickySessions() {
    return stickySessions;
  }

  /** @param stickySessions the stickySessions to set */
  public void setStickySessions(StickySessions stickySessions) {
    this.stickySessions = stickySessions;
  }

  /**
   * @return true if the HTTP requests to the Load Balancer on port 80 will be redirected to HTTPS
   *     on port 443.
   */
  public boolean isRedirectHttpToHttps() {
    return redirectHttpToHttps;
  }

  /** @param redirectHttpToHttps the redirectHttpToHttps to set */
  public void setRedirectHttpToHttps(boolean redirectHttpToHttps) {
    this.redirectHttpToHttps = redirectHttpToHttps;
  }

  /** @return the list of dropletId */
  public List<String> getDropletIds() {
    return dropletIds;
  }

  /** @param dropletIds the dropletIds to set */
  public void setDropletIds(List<String> dropletIds) {
    this.dropletIds = dropletIds;
  }

  /** @return the tag */
  public String getTag() {
    return tag;
  }

  /** @param tag the tag to set */
  public void setTag(String tag) {
    this.tag = tag;
  }
}

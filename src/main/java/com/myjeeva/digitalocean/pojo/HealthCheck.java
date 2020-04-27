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
import com.myjeeva.digitalocean.common.Protocol;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents DigitalOcean Load Balancer health check object
 *
 * @author Thomas Lehoux (https://github.com/tlehoux)
 * @since v2.11
 */
public class HealthCheck extends Base {

  private static final long serialVersionUID = -442836096026412279L;

  @Expose private Protocol protocol;

  @Expose private Integer port;

  @Expose private String path;

  @Expose
  @SerializedName("check_interval_seconds")
  private Integer checkIntervalInSeconds = 10;

  @Expose
  @SerializedName("response_timeout_seconds")
  private Integer responseTimeoutInSeconds = 5;

  @Expose
  @SerializedName("unhealthy_threshold")
  private Integer unhealthyThreshold = 3;

  @Expose
  @SerializedName("healthy_threshold")
  private Integer healthyThreshold = 5;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the protocol */
  public Protocol getProtocol() {
    return protocol;
  }

  /** @param protocol the protocol to set */
  public void setProtocol(Protocol protocol) {
    this.protocol = protocol;
  }

  /** @return the port */
  public Integer getPort() {
    return port;
  }

  /** @param port the entry port to set */
  public void setPort(Integer port) {
    this.port = port;
  }

  /** @return the path */
  public String getPath() {
    return path;
  }

  /** @param path the path to set */
  public void setPath(String path) {
    this.path = path;
  }

  /**
   * @return the number of times a health check must pass for a backend Droplet to be marked
   *     "healthy" and be re-added to the pool
   */
  public Integer getHealthyThreshold() {
    return healthyThreshold;
  }

  /**
   * @param healthyThreshold the number of times a health check must pass for a backend Droplet to
   *     be marked "healthy" and be re-added to the pool
   */
  public void setHealthyThreshold(Integer healthyThreshold) {
    this.healthyThreshold = healthyThreshold;
  }

  /**
   * @return the number of times a health check must fail for a backend Droplet to be marked
   *     "unhealthy" and be removed from the pool.
   */
  public Integer getUnhealthyThreshold() {
    return unhealthyThreshold;
  }

  /**
   * @param unhealthyThreshold the number of times a health check must fail for a backend Droplet to
   *     be marked "unhealthy" and be removed from the pool.
   */
  public void setUnhealthyThreshold(Integer unhealthyThreshold) {
    this.unhealthyThreshold = unhealthyThreshold;
  }

  /**
   * @return the number of seconds the Load Balancer instance will wait for a response until marking
   *     a health check as failed.
   */
  public Integer getResponseTimeoutInSeconds() {
    return responseTimeoutInSeconds;
  }

  /**
   * @param responseTimeoutInSeconds the number of seconds the Load Balancer instance will wait for
   *     a response until marking a health check as failed.
   */
  public void setResponseTimeoutInSeconds(Integer responseTimeoutInSeconds) {
    this.responseTimeoutInSeconds = responseTimeoutInSeconds;
  }

  /** @return number of seconds between two consecutive health checks */
  public Integer getCheckIntervalInSeconds() {
    return checkIntervalInSeconds;
  }

  /**
   * @param checkIntervalInSeconds the number of seconds between two consecutive health checks to
   *     set
   */
  public void setCheckIntervalInSeconds(Integer checkIntervalInSeconds) {
    this.checkIntervalInSeconds = checkIntervalInSeconds;
  }
}

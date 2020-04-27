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
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Domain (TLD) attributes of DigitalOcean DNS. Revised as per v2 API data structure.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Domain extends Base {

  private static final long serialVersionUID = 5958407274594550472L;

  @Expose private String name;

  @SerializedName("ttl")
  private Integer timeToLive;

  @SerializedName("zone_file")
  private String zoneFile;

  @Expose
  @SerializedName("ip_address")
  private String ipAddress;

  public Domain() {
    // Default Constructor
  }

  public Domain(String name, String ipAddress) {
    this.name = name;
    this.ipAddress = ipAddress;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the name */
  public String getName() {
    return name;
  }

  /** @param name the name to set */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the timeToLive */
  public Integer getTimeToLive() {
    return timeToLive;
  }

  /** @param timeToLive the timeToLive to set */
  public void setTimeToLive(Integer timeToLive) {
    this.timeToLive = timeToLive;
  }

  /** @return the zoneFile */
  public String getZoneFile() {
    return zoneFile;
  }

  /** @param zoneFile the zoneFile to set */
  public void setZoneFile(String zoneFile) {
    this.zoneFile = zoneFile;
  }

  /** @return the ipAddress */
  public String getIpAddress() {
    return ipAddress;
  }

  /** @param ipAddress the ipAddress to set */
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }
}

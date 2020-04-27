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

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents DigitalOcean Network attributes
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public class Network extends Base {

  private static final long serialVersionUID = -3123221302381366269L;

  @SerializedName("ip_address")
  private String ipAddress;

  private String netmask;

  private String gateway;

  private String type;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the ipAddress */
  public String getIpAddress() {
    return ipAddress;
  }

  /** @param ipAddress the ipAddress to set */
  public void setIpAddress(String ipAddress) {
    this.ipAddress = ipAddress;
  }

  /** @return the netmask */
  public String getNetmask() {
    return netmask;
  }

  /** @param netmask the netmask to set */
  public void setNetmask(String netmask) {
    this.netmask = netmask;
  }

  /** @return the gateway */
  public String getGateway() {
    return gateway;
  }

  /** @param gateway the gateway to set */
  public void setGateway(String gateway) {
    this.gateway = gateway;
  }

  /** @return the type */
  public String getType() {
    return type;
  }

  /** @param type the type to set */
  public void setType(String type) {
    this.type = type;
  }
}

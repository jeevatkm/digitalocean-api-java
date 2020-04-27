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
 * Represents Account attributes
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public class Account extends Base {

  private static final long serialVersionUID = 5951525501167424430L;

  private String email;

  private String uuid;

  @SerializedName("droplet_limit")
  private Integer dropletLimit;

  @SerializedName("email_verified")
  private boolean isEmailVerified;

  private String status;

  @SerializedName("status_message")
  private String statusMessage;

  @SerializedName("floating_ip_limit")
  private Integer floatingIPLimit;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the email */
  public String getEmail() {
    return email;
  }

  /** @param email the email to set */
  public void setEmail(String email) {
    this.email = email;
  }

  /** @return the uuid */
  public String getUuid() {
    return uuid;
  }

  /** @param uuid the uuid to set */
  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  /** @return the dropletLimit */
  public Integer getDropletLimit() {
    return dropletLimit;
  }

  /** @param dropletLimit the dropletLimit to set */
  public void setDropletLimit(Integer dropletLimit) {
    this.dropletLimit = dropletLimit;
  }

  /** @return the isEmailVerified */
  public boolean isEmailVerified() {
    return isEmailVerified;
  }

  /** @param isEmailVerified the isEmailVerified to set */
  public void setEmailVerified(boolean isEmailVerified) {
    this.isEmailVerified = isEmailVerified;
  }

  /** @return the status */
  public String getStatus() {
    return status;
  }

  /** @param status the status to set */
  public void setStatus(String status) {
    this.status = status;
  }

  /** @return the statusMessage */
  public String getStatusMessage() {
    return statusMessage;
  }

  /** @param statusMessage the statusMessage to set */
  public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
  }

  /** @return the floatingIPLimit */
  public Integer getFloatingIPLimit() {
    return floatingIPLimit;
  }

  /** @param floatingIPLimit the floatingIPLimit to set */
  public void setFloatingIPLimit(Integer floatingIPLimit) {
    this.floatingIPLimit = floatingIPLimit;
  }
}

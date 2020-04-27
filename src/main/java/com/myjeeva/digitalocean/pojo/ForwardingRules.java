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
 * Represents DigitalOcean Load Balancer forwarding rule object
 *
 * @author Thomas Lehoux (https://github.com/tlehoux)
 * @since v2.11
 */
public class ForwardingRules extends Base {

  private static final long serialVersionUID = -442836096026412279L;

  @Expose
  @SerializedName("entry_protocol")
  private Protocol entryProtocol;

  @Expose
  @SerializedName("entry_port")
  private Integer entryPort;

  @Expose
  @SerializedName("target_protocol")
  private Protocol targetProtocol;

  @Expose
  @SerializedName("target_port")
  private Integer targetPort;

  @Expose
  @SerializedName("certificate_id")
  private String certificateId;

  @Expose
  @SerializedName("tls_passthrough")
  private boolean tlsPassthrough;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the entry protocol */
  public Protocol getEntryProtocol() {
    return entryProtocol;
  }

  public void setEntryProtocol(Protocol entryProtocol) {
    this.entryProtocol = entryProtocol;
  }

  /** @return the entry port */
  public Integer getEntryPort() {
    return entryPort;
  }

  /** @param entryPort the entry port to set */
  public void setEntryPort(Integer entryPort) {
    this.entryPort = entryPort;
  }

  /** @return the target protocol */
  public Protocol getTargetProtocol() {
    return targetProtocol;
  }

  /** @param targetProtocol the target protocol to set */
  public void setTargetProtocol(Protocol targetProtocol) {
    this.targetProtocol = targetProtocol;
  }

  /** @return the target port */
  public Integer getTargetPort() {
    return targetPort;
  }

  /** @param targetPort the target port to set */
  public void setTargetPort(Integer targetPort) {
    this.targetPort = targetPort;
  }

  /** @return the certificateId */
  public String getCertificateId() {
    return certificateId;
  }

  /** @param certificateId the certificateId to set */
  public void setCertificateId(String certificateId) {
    this.certificateId = certificateId;
  }

  /** @return true if SSL encrypted traffic will be passed through to the backend Droplets */
  public boolean isTlsPassthrough() {
    return tlsPassthrough;
  }

  /** @param tlsPassthrough the tlsPassthrough to set */
  public void setTlsPassthrough(boolean tlsPassthrough) {
    this.tlsPassthrough = tlsPassthrough;
  }
}

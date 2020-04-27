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
import com.myjeeva.digitalocean.common.StickySessionType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents DigitalOcean Load Balancer sticky sessions object
 *
 * @author Thomas Lehoux (https://github.com/tlehoux)
 * @since v2.11
 */
public class StickySessions extends Base {

  private static final long serialVersionUID = -2697068548366505704L;

  @Expose private StickySessionType type = StickySessionType.None;

  @Expose private Integer port;

  @Expose
  @SerializedName("cookie_name")
  private String cookieName;

  @Expose
  @SerializedName("cookie_ttl_seconds")
  private Integer cookieTtlInSeconds;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the type */
  public StickySessionType getType() {
    return type;
  }

  /** @param type the type to set */
  public void setType(StickySessionType type) {
    this.type = type;
  }

  /** @return the cookie name */
  public String getCookieName() {
    return cookieName;
  }

  /** @param cookieName the cookie name to set */
  public void setCookieName(String cookieName) {
    this.cookieName = cookieName;
  }

  /** @return the cookie TTL in seconds */
  public Integer getCookieTtlInSeconds() {
    return cookieTtlInSeconds;
  }

  /** @param cookieTtlInSeconds the cookie TTL in seconds to set */
  public void setCookieTtlInSeconds(Integer cookieTtlInSeconds) {
    this.cookieTtlInSeconds = cookieTtlInSeconds;
  }
}

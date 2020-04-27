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
 * Represents Floating IP Action attributes
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.3
 */
public class FloatingIPAction extends Base {

  private static final long serialVersionUID = -6669790006341807856L;

  @Expose
  @SerializedName("droplet_id")
  private Integer dropletId;

  @Expose private String region;

  @Expose private String type;

  public FloatingIPAction() {
    // default constructor
  }

  public FloatingIPAction(Integer dropletId) {
    this(dropletId, null, null);
  }

  public FloatingIPAction(String region) {
    this(null, region, null);
  }

  public FloatingIPAction(Integer dropletId, String type) {
    this(dropletId, null, type);
  }

  public FloatingIPAction(Integer dropletId, String region, String type) {
    this.dropletId = dropletId;
    this.region = region;
    this.type = type;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the dropletId */
  public Integer getDropletId() {
    return dropletId;
  }

  /** @param dropletId the dropletId to set */
  public void setDropletId(Integer dropletId) {
    this.dropletId = dropletId;
  }

  /** @return the region */
  public String getRegion() {
    return region;
  }

  /** @param region the region to set */
  public void setRegion(String region) {
    this.region = region;
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

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
import com.myjeeva.digitalocean.common.ResourceType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Resource represent a single resource for associating/disassociating with tag on DigitalOcean.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.5
 */
public class Resource {

  @Expose
  @SerializedName("resource_id")
  private String id;

  @Expose
  @SerializedName("resource_type")
  private ResourceType type;

  public Resource() {
    // Default constructor
  }

  public Resource(String id, ResourceType type) {
    this.id = id;
    this.type = type;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the id */
  public String getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(String id) {
    this.id = id;
  }

  /** @return the type */
  public ResourceType getType() {
    return type;
  }

  /** @param type the type to set */
  public void setType(ResourceType type) {
    this.type = type;
  }
}

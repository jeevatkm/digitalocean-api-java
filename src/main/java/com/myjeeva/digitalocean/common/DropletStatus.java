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

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

/**
 * Enumeration of DigitalOcean droplet states.
 *
 * @author Robert Gründler (robert@dubture.com)
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v1.5
 */
public enum DropletStatus {
  @SerializedName("new")
  NEW("new"),

  @SerializedName("active")
  ACTIVE("active"),

  @SerializedName("off")
  OFF("off"),

  @SerializedName("archive")
  ARCHIVE("archive");

  private String value;

  private DropletStatus(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public static DropletStatus fromValue(String value) {
    if (StringUtils.isBlank(value)) {
      throw new IllegalArgumentException("Value cannot be null or empty!");
    }

    for (DropletStatus ds : DropletStatus.values()) {
      if (value.equalsIgnoreCase(ds.value)) {
        return ds;
      }
    }

    throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
  }
}

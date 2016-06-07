/**
 * The MIT License
 * 
 * Copyright (c) 2013-2016 Jeevanandam M. (myjeeva.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.myjeeva.digitalocean.common;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration of DigitalOcean Image Types
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * 
 * @since v2.0
 */
public enum ImageType {

  @SerializedName("snapshot")
  SNAPSHOT("snapshot"),

  @SerializedName("backup")
  BACKUP("backup"),

  /**
   * More info:
   * https://developers.digitalocean.com/documentation/changelog/api-v2/deprecate-final-snaphots/
   */
  @Deprecated
  @SerializedName("temporary")
  TEMPORARY("temporary");

  private String value;

  ImageType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public static ImageType fromValue(String value) {
    if (null == value || "".equals(value)) {
      throw new IllegalArgumentException("Value cannot be null or empty!");
    }

    for (ImageType rt : ImageType.values()) {
      if (value.equalsIgnoreCase(rt.value)) {
        return rt;
      }
    }

    throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
  }
}

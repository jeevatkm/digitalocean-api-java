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

import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Region (aka Data Center) attributes of DigitalOcean. Revised as per v2 API data
 * structure.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Region extends Base {

  private static final long serialVersionUID = 5407997886217553770L;

  private String slug;

  private String name;

  private List<String> sizes;

  private boolean available;

  private List<String> features;

  public Region() {
    // Default Constructor
  }

  public Region(String slug) {
    this.slug = slug;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the slug */
  public String getSlug() {
    return slug;
  }

  /** @param slug the slug to set */
  public void setSlug(String slug) {
    this.slug = slug;
  }

  /** @return the name */
  public String getName() {
    return name;
  }

  /** @param name the name to set */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the sizes */
  public List<String> getSizes() {
    return sizes;
  }

  /** @param sizes the sizes to set */
  public void setSizes(List<String> sizes) {
    this.sizes = sizes;
  }

  /** @return the available */
  public boolean isAvailable() {
    return available;
  }

  /** @param available the available to set */
  public void setAvailable(boolean available) {
    this.available = available;
  }

  /** @return the features */
  public List<String> getFeatures() {
    return features;
  }

  /** @param features the features to set */
  public void setFeatures(List<String> features) {
    this.features = features;
  }
}

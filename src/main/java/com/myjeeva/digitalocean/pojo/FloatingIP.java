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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Floating IP object
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.3
 */
public class FloatingIP extends Base {

  private static final long serialVersionUID = 607751030000684913L;

  private String ip;

  private Region region;

  private Droplet droplet;

  private boolean locked;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the ip */
  public String getIp() {
    return ip;
  }

  /** @param ip the ip to set */
  public void setIp(String ip) {
    this.ip = ip;
  }

  /** @return the region */
  public Region getRegion() {
    return region;
  }

  /** @param region the region to set */
  public void setRegion(Region region) {
    this.region = region;
  }

  /** @return the droplet */
  public Droplet getDroplet() {
    return droplet;
  }

  /** @param droplet the droplet to set */
  public void setDroplet(Droplet droplet) {
    this.droplet = droplet;
  }

  /** @return the locked */
  public boolean isLocked() {
    return locked;
  }

  /** @param locked the locked to set */
  public void setLocked(boolean locked) {
    this.locked = locked;
  }
}

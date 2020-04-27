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
import com.myjeeva.digitalocean.common.ActionType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Droplet Actions like reboot, powercycle, power on , power off, etc.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public class DropletAction {

  @Expose private ActionType type;

  @Expose private String name;

  @Expose private Integer image;

  @Expose private Integer kernel;

  @Expose private String size;

  @Expose private Boolean disk;

  public DropletAction() {
    // Default Constructor
  }

  public DropletAction(ActionType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the type */
  public ActionType getType() {
    return type;
  }

  /** @param type the type to set */
  public void setType(ActionType type) {
    this.type = type;
  }

  /** @return the name */
  public String getName() {
    return name;
  }

  /** @param name the name to set */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the image */
  public Integer getImage() {
    return image;
  }

  /** @param image the image to set */
  public void setImage(Integer image) {
    this.image = image;
  }

  /** @return the kernel */
  public Integer getKernel() {
    return kernel;
  }

  /** @param kernel the kernel to set */
  public void setKernel(Integer kernel) {
    this.kernel = kernel;
  }

  /** @return the size */
  public String getSize() {
    return size;
  }

  /** @param size the size to set */
  public void setSize(String size) {
    this.size = size;
  }

  /** @return the disk */
  public Boolean getDisk() {
    return disk;
  }

  /** @param disk the size to set */
  public void setDisk(Boolean disk) {
    this.disk = disk;
  }
}

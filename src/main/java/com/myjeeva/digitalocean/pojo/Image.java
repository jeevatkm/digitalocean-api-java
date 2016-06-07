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

package com.myjeeva.digitalocean.pojo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myjeeva.digitalocean.common.ImageType;

/**
 * Represents Droplet Image (also public images aka Distribution) attributes of DigitalOcean
 * (distribution, snapshots or backups). Revised as per v2 API data structure.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Image extends RateLimitBase {

  private static final long serialVersionUID = 1321111459154107563L;

  private Integer id;

  @Expose
  private String name;

  private String distribution;

  private String slug;

  @SerializedName("public")
  private boolean availablePublic;

  private List<String> regions;

  @SerializedName("created_at")
  private Date createdDate;

  @SerializedName("min_disk_size")
  private Integer minDiskSize;
  
  @SerializedName("size_gigabytes")
  private Double size;

  private ImageType type;

  public Image() {
    // Default constructor
  }

  public Image(Integer id) {
    this.id = id;
  }

  public Image(String slug) {
    this.slug = slug;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /**
   * @return true if image is snapshot
   */
  public boolean isSnapshot() {
    return ImageType.SNAPSHOT == type;
  }

  /**
   * @return true if image is backup
   */
  public boolean isBackup() {
    return ImageType.BACKUP == type;
  }

  /**
   * @return true if image is temporary
   * Deprecated Info: https://developers.digitalocean.com/documentation/changelog/api-v2/deprecate-final-snaphots/
   */
  @Deprecated
  public boolean isTemporary() {
    return ImageType.TEMPORARY == type;
  }

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the distribution
   */
  public String getDistribution() {
    return distribution;
  }

  /**
   * @param distribution the distribution to set
   */
  public void setDistribution(String distribution) {
    this.distribution = distribution;
  }

  /**
   * @return the slug
   */
  public String getSlug() {
    return slug;
  }

  /**
   * @param slug the slug to set
   */
  public void setSlug(String slug) {
    this.slug = slug;
  }

  /**
   * @return the availablePublic
   */
  public boolean isAvailablePublic() {
    return availablePublic;
  }

  /**
   * @param availablePublic the availablePublic to set
   */
  public void setAvailablePublic(boolean availablePublic) {
    this.availablePublic = availablePublic;
  }

  /**
   * @return the regions
   */
  public List<String> getRegions() {
    return regions;
  }

  /**
   * @param regions the regions to set
   */
  public void setRegions(List<String> regions) {
    this.regions = regions;
  }

  /**
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * @return the type
   */
  public ImageType getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(ImageType type) {
    this.type = type;
  }

  /**
   * @return the min Disk Size
   */
  public Integer getMinDiskSize() {
    return minDiskSize;
  }

  /**
   * @param minDiskSize the minDiskSize to set
   */
  public void setMinDiskSize(Integer minDiskSize) {
    this.minDiskSize = minDiskSize;
  }
}

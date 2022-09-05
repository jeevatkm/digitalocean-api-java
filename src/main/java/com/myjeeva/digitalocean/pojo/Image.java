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
import com.myjeeva.digitalocean.common.ImageStatus;
import com.myjeeva.digitalocean.common.ImageType;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Droplet Image (also public images aka Distribution) attributes of DigitalOcean
 * (distribution, snapshots or backups). Revised as per v2 API data structure.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Image extends Base {

  private static final long serialVersionUID = 1321111459154107563L;

  private String id;

  @Expose private String name;

  @Expose private String distribution;

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

  // #89 - start
  // https://developers.digitalocean.com/documentation/changelog/api-v2/support-for-custom-images-and-image-tagging/
  @Expose private String url;

  @Expose private String region;

  @Expose private String description;

  @Expose private List<String> tags;

  private ImageStatus status;

  @SerializedName("error_message")
  private String errorMessage;
  // #89 - end

  public Image() {
    // Default constructor
  }

  public Image(String id) {
    this.id = id;
  }

  public Image(String name, String url, String region) {
    this.name = name;
    this.url = url;
    this.region = region;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return true if image is snapshot */
  public boolean isSnapshot() {
    return ImageType.SNAPSHOT == type;
  }

  /** @return true if image is backup */
  public boolean isBackup() {
    return ImageType.BACKUP == type;
  }

  /** @return the id */
  public String getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(String id) {
    this.id = id;
  }

  /** @return the name */
  public String getName() {
    return name;
  }

  /** @param name the name to set */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * The name of a custom image's distribution.
   *
   * <p>Currently, the valid values are "Arch Linux", "CentOS", "CoreOS", "Debian", "Fedora",
   * "Fedora Atomic", "FreeBSD", "Gentoo", "openSUSE", "RancherOS", "Ubuntu", and "Unknown". Any
   * other value will be accepted but ignored, and "Unknown" will be used in its place.
   *
   * @return the distribution
   */
  public String getDistribution() {
    return distribution;
  }

  /**
   * The name of a custom image's distribution.
   *
   * <p>Currently, the valid values are "Arch Linux", "CentOS", "CoreOS", "Debian", "Fedora",
   * "Fedora Atomic", "FreeBSD", "Gentoo", "openSUSE", "RancherOS", "Ubuntu", and "Unknown". Any
   * other value will be accepted but ignored, and "Unknown" will be used in its place.
   *
   * @param distribution the distribution to set
   */
  public void setDistribution(String distribution) {
    this.distribution = distribution;
  }

  /** @return the slug */
  public String getSlug() {
    return slug;
  }

  /** @param slug the slug to set */
  public void setSlug(String slug) {
    this.slug = slug;
  }

  /** @return the availablePublic */
  public boolean isAvailablePublic() {
    return availablePublic;
  }

  /** @param availablePublic the availablePublic to set */
  public void setAvailablePublic(boolean availablePublic) {
    this.availablePublic = availablePublic;
  }

  /** @return the regions */
  public List<String> getRegions() {
    return regions;
  }

  /** @param regions the regions to set */
  public void setRegions(List<String> regions) {
    this.regions = regions;
  }

  /** @return the createdDate */
  public Date getCreatedDate() {
    return createdDate;
  }

  /** @param createdDate the createdDate to set */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /** @return the type */
  public ImageType getType() {
    return type;
  }

  /** @param type the type to set */
  public void setType(ImageType type) {
    this.type = type;
  }

  /** @return the min Disk Size */
  public Integer getMinDiskSize() {
    return minDiskSize;
  }

  /** @param minDiskSize the minDiskSize to set */
  public void setMinDiskSize(Integer minDiskSize) {
    this.minDiskSize = minDiskSize;
  }

  /** @return the size */
  public Double getSize() {
    return size;
  }

  /** @param size the size to set */
  public void setSize(Double size) {
    this.size = size;
  }

  /** @return the url */
  public String getUrl() {
    return url;
  }

  /**
   * A URL from which the custom Linux virtual machine image may be retrieved. The image it points
   * to must be in the raw, qcow2, vhdx, vdi, or vmdk format. It may be compressed using gzip or
   * bzip2 and must be smaller than 100 GB after being decompressed.
   *
   * @param url the url to set
   */
  public void setUrl(String url) {
    this.url = url;
  }

  /** @return the region */
  public String getRegion() {
    return region;
  }

  /** @param region the region to set */
  public void setRegion(String region) {
    this.region = region;
  }

  /** @return the description */
  public String getDescription() {
    return description;
  }

  /** @param description the description to set */
  public void setDescription(String description) {
    this.description = description;
  }

  /** @return the status */
  public ImageStatus getStatus() {
    return status;
  }

  /** @param status the status to set */
  public void setStatus(ImageStatus status) {
    this.status = status;
  }

  /** @return the tags */
  public List<String> getTags() {
    return tags;
  }

  /** @param tags the tags to set */
  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  /** @return the errorMessage */
  public String getErrorMessage() {
    return errorMessage;
  }

  /** @param errorMessage the errorMessage to set */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}

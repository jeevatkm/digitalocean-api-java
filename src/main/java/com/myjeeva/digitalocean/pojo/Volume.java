/**
 * Copyright (c) Jeevanandam M. (https://github.com/jeevatkm)
 *
 * <p>digitalocean-api-client source code and usage is governed by a MIT style license that can be
 * found in the LICENSE file
 */
package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.SerializedName;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Block Storage attributes of DigitalOcean.
 *
 * <p>Block Storage volumes provide expanded storage capacity for your Droplets.
 *
 * @author Eugene Strokin (https://github.com/strokine)
 * @since v2.7
 */
public class Volume extends Base {

  private static final long serialVersionUID = 3274091633535612517L;

  private String id;

  private Region region;

  @SerializedName("droplet_ids")
  private Set<Integer> dropletIds;

  private String name;

  private String description;

  @SerializedName("size_gigabytes")
  private Integer size;

  @SerializedName("created_at")
  private Date createdDate;

  @SerializedName("snapshot_id")
  private String snapshotId;

  @SerializedName("filesystem_type")
  private String fileSystemType;

  @SerializedName("filesystem_label")
  private String fileSystemLabel;

  private List<String> tags;

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

  /** @return the region */
  public Region getRegion() {
    return region;
  }

  /** @param region the region to set */
  public void setRegion(Region region) {
    this.region = region;
  }

  /** @return the dropletIds */
  public Set<Integer> getDropletIds() {
    return dropletIds;
  }

  /** @param dropletIds the dropletIds to set */
  public void setDropletIds(Set<Integer> dropletIds) {
    this.dropletIds = dropletIds;
  }

  /** @return the name */
  public String getName() {
    return name;
  }

  /** @param name the name to set */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the description */
  public String getDescription() {
    return description;
  }

  /** @param description the description to set */
  public void setDescription(String description) {
    this.description = description;
  }

  /** @return the size in Gigabytes */
  public Integer getSize() {
    return size;
  }

  /** @param size the size to set */
  public void setSize(Integer size) {
    this.size = size;
  }

  /** @return the createdDate */
  public Date getCreatedDate() {
    return createdDate;
  }

  /** @param createdDate the createdDate to set */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /** @return the snapshotId */
  public String getSnapshotId() {
    return snapshotId;
  }

  /** @param snapshotId the snapshotId to set */
  public void setSnapshotId(String snapshotId) {
    this.snapshotId = snapshotId;
  }

  /** @return the fileSystemType */
  public String getFileSystemType() {
    return fileSystemType;
  }

  /** @param fileSystemType the fileSystemType to set */
  public void setFileSystemType(String fileSystemType) {
    this.fileSystemType = fileSystemType;
  }

  /** @return the fileSystemLabel */
  public String getFileSystemLabel() {
    return fileSystemLabel;
  }

  /** @param fileSystemLabel the fileSystemLabel to set */
  public void setFileSystemLabel(String fileSystemLabel) {
    this.fileSystemLabel = fileSystemLabel;
  }

  /** @return the tags */
  public List<String> getTags() {
    return tags;
  }

  /** @param tags the tags to set */
  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}

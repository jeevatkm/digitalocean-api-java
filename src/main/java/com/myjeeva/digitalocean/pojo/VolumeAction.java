/**
 * Copyright (c) Jeevanandam M. (https://github.com/jeevatkm)
 *
 * <p>digitalocean-api-client source code and usage is governed by a MIT style license that can be
 * found in the LICENSE file
 */
package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myjeeva.digitalocean.common.ActionType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Volume action is to create new volume.
 *
 * @author Eugene Strokin (https://github.com/strokine)
 * @since v2.7
 */
public class VolumeAction {

  @Expose private ActionType type;

  @Expose
  @SerializedName("droplet_id")
  private Integer dropletId;

  @Expose
  @SerializedName("region")
  private String regionSlug;

  @Expose
  @SerializedName("volume_name")
  private String volumeName;

  @Expose
  @SerializedName("size_gigabytes")
  private Double size;

  /** Constructor */
  public VolumeAction() {
    // Default Constructor
  }

  /**
   * Constructor
   *
   * @param type action type of the volume
   * @param dropletId is to attach/detach volume from droplet
   * @param regionSlug short name of region
   */
  public VolumeAction(ActionType type, Integer dropletId, String regionSlug) {
    this(type, dropletId, regionSlug, null, null);
  }

  /**
   * Constructor
   *
   * @param type action type of the volume
   * @param regionSlug short name of region
   * @param size volume size in GB
   */
  public VolumeAction(ActionType type, String regionSlug, Double size) {
    this(type, null, regionSlug, null, size);
  }

  /**
   * Constructor
   *
   * @param type action type of the volume
   * @param dropletId is to attach/detach volume from droplet
   * @param regionSlug short name of region
   * @param volumeName name of the volume
   * @param size volume size in GB
   */
  public VolumeAction(
      ActionType type, Integer dropletId, String regionSlug, String volumeName, Double size) {
    this.type = type;
    this.dropletId = dropletId;
    this.regionSlug = regionSlug;
    this.volumeName = volumeName;
    this.size = size;
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

  /** @return the dropletId */
  public Integer getDropletId() {
    return dropletId;
  }

  /** @param dropletId the dropletId to set */
  public void setDropletId(Integer dropletId) {
    this.dropletId = dropletId;
  }

  /** @return the regionSlug */
  public String getRegionSlug() {
    return regionSlug;
  }

  /** @param regionSlug the regionSlug to set */
  public void setRegionSlug(String regionSlug) {
    this.regionSlug = regionSlug;
  }

  /** @return the volumeName */
  public String getVolumeName() {
    return volumeName;
  }

  /** @param volumeName the volumeName to set */
  public void setVolumeName(String volumeName) {
    this.volumeName = volumeName;
  }

  /** @return the size */
  public Double getSize() {
    return size;
  }

  /** @param size the size to set */
  public void setSize(Double size) {
    this.size = size;
  }
}

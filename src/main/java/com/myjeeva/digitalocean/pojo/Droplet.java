/*
 * The MIT License
 * 
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
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

import com.google.gson.annotations.SerializedName;
import com.myjeeva.digitalocean.common.DropletStatus;

/**
 * Represents Droplet attributes of DigitalOcean. Revised as per v2 API data structure.
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Droplet {

  private Integer id;

  private String name;

  @SerializedName("memory")
  private Integer memorySizeInMb;

  @SerializedName("vcpus")
  private Integer virutalCpuCount;

  @SerializedName("disk")
  private Integer diskSize;

  private Region region;

  private Image image;

  private Size size;

  private boolean locked;

  private DropletStatus status;

  private Networks networks;

  private Kernel kernel;

  @SerializedName("created_at")
  private Date createdDate;

  private List<String> features;

  @SerializedName("backup_ids")
  private List<Integer> backupIds;

  @SerializedName("snapshot_ids")
  private List<Integer> snapshotIds;

  @SerializedName("action_ids")
  private List<Integer> actionIds;

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
   * @return the memorySizeInMb
   */
  public Integer getMemorySizeInMb() {
    return memorySizeInMb;
  }

  /**
   * @param memorySizeInMb the memorySizeInMb to set
   */
  public void setMemorySizeInMb(Integer memorySizeInMb) {
    this.memorySizeInMb = memorySizeInMb;
  }

  /**
   * @return the virutalCpuCount
   */
  public Integer getVirutalCpuCount() {
    return virutalCpuCount;
  }

  /**
   * @param virutalCpuCount the virutalCpuCount to set
   */
  public void setNoOfVirutalCpu(Integer virutalCpuCount) {
    this.virutalCpuCount = virutalCpuCount;
  }

  /**
   * @return the diskSize
   */
  public Integer getDiskSize() {
    return diskSize;
  }

  /**
   * @param diskSize the diskSize to set
   */
  public void setDiskSize(Integer diskSize) {
    this.diskSize = diskSize;
  }

  /**
   * @return the region
   */
  public Region getRegion() {
    return region;
  }

  /**
   * @param region the region to set
   */
  public void setRegion(Region region) {
    this.region = region;
  }

  /**
   * @return the image
   */
  public Image getImage() {
    return image;
  }

  /**
   * @param image the image to set
   */
  public void setImage(Image image) {
    this.image = image;
  }

  /**
   * @return the size
   */
  public Size getSize() {
    return size;
  }

  /**
   * @param size the size to set
   */
  public void setSize(Size size) {
    this.size = size;
  }

  /**
   * @return the locked
   */
  public boolean isLocked() {
    return locked;
  }

  /**
   * @param locked the locked to set
   */
  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  /**
   * @return the status
   */
  public DropletStatus getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(DropletStatus status) {
    this.status = status;
  }

  /**
   * @return the networks
   */
  public Networks getNetworks() {
    return networks;
  }

  /**
   * @param networks the networks to set
   */
  public void setNetworks(Networks networks) {
    this.networks = networks;
  }

  /**
   * @return the kernel
   */
  public Kernel getKernel() {
    return kernel;
  }

  /**
   * @param kernel the kernel to set
   */
  public void setKernel(Kernel kernel) {
    this.kernel = kernel;
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
   * @return the features
   */
  public List<String> getFeatures() {
    return features;
  }

  /**
   * @param features the features to set
   */
  public void setFeatures(List<String> features) {
    this.features = features;
  }

  /**
   * @return the backupIds
   */
  public List<Integer> getBackupIds() {
    return backupIds;
  }

  /**
   * @param backupIds the backupIds to set
   */
  public void setBackupIds(List<Integer> backupIds) {
    this.backupIds = backupIds;
  }

  /**
   * @return the snapshotIds
   */
  public List<Integer> getSnapshotIds() {
    return snapshotIds;
  }

  /**
   * @param snapshotIds the snapshotIds to set
   */
  public void setSnapshotIds(List<Integer> snapshotIds) {
    this.snapshotIds = snapshotIds;
  }

  /**
   * @return the actionIds
   */
  public List<Integer> getActionIds() {
    return actionIds;
  }

  /**
   * @param actionIds the actionIds to set
   */
  public void setActionIds(List<Integer> actionIds) {
    this.actionIds = actionIds;
  }

  /**
   * @return true if droplet is active
   */
  public boolean isActive() {
    return status == DropletStatus.ACTIVE;
  }

  /**
   * @return true if droplet is new, meaning it's booting up
   */
  public boolean isNew() {
    return status == DropletStatus.NEW;
  }

  /**
   * @return true if droplet is turned off
   */
  public boolean isOff() {
    return status == DropletStatus.OFF;
  }

  /**
   * @return true if droplet is archived
   */
  public boolean isArchived() {
    return status == DropletStatus.ARCHIVE;
  }
}

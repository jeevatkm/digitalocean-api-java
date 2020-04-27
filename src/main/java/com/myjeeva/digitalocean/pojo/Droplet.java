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

import com.google.gson.annotations.SerializedName;
import com.myjeeva.digitalocean.common.DropletStatus;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Droplet attributes of DigitalOcean. Revised as per v2 API data structure.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Droplet extends Base {

  private static final long serialVersionUID = 1110964622197874436L;

  private Integer id;

  private String name;

  private List<String> names;

  @SerializedName("memory")
  private Integer memorySizeInMb;

  @SerializedName("vcpus")
  private Integer virutalCpuCount;

  @SerializedName("disk")
  private Integer diskSize;

  private Region region;

  private Image image;

  @SerializedName("size_slug")
  private String size;

  private boolean locked;

  private DropletStatus status;

  private Networks networks;

  private Kernel kernel;

  @SerializedName("created_at")
  private Date createdDate;

  private List<String> features;

  private Boolean enableBackup;

  private Boolean enableIpv6;

  private Boolean enablePrivateNetworking;

  @SerializedName("backup_ids")
  private List<Integer> backupIds;

  @SerializedName("snapshot_ids")
  private List<Integer> snapshotIds;

  @SerializedName("ssh_keys")
  private List<Key> keys;

  @SerializedName("user_data")
  private String userData;

  @SerializedName("volume_ids")
  private List<String> volumeIds;

  private List<String> tags;

  @SerializedName("monitoring")
  private Boolean installMonitoring;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return true if droplet is active */
  public boolean isActive() {
    return DropletStatus.ACTIVE == status;
  }

  /** @return true if droplet is new, meaning it's booting up */
  public boolean isNew() {
    return DropletStatus.NEW == status;
  }

  /** @return true if droplet is turned off */
  public boolean isOff() {
    return DropletStatus.OFF == status;
  }

  /** @return true if droplet is archived */
  public boolean isArchived() {
    return DropletStatus.ARCHIVE == status;
  }

  /** @return the id */
  public Integer getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(Integer id) {
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

  /** @return the names */
  public List<String> getNames() {
    return names;
  }

  /** @param names the names to set */
  public void setNames(List<String> names) {
    this.names = names;
  }

  /** @return the memorySizeInMb */
  public Integer getMemorySizeInMb() {
    return memorySizeInMb;
  }

  /** @param memorySizeInMb the memorySizeInMb to set */
  public void setMemorySizeInMb(Integer memorySizeInMb) {
    this.memorySizeInMb = memorySizeInMb;
  }

  /** @return the virutalCpuCount */
  public Integer getVirutalCpuCount() {
    return virutalCpuCount;
  }

  /** @param virutalCpuCount the virutalCpuCount to set */
  public void setVirutalCpuCount(Integer virutalCpuCount) {
    this.virutalCpuCount = virutalCpuCount;
  }

  /** @return the diskSize */
  public Integer getDiskSize() {
    return diskSize;
  }

  /** @param diskSize the diskSize to set */
  public void setDiskSize(Integer diskSize) {
    this.diskSize = diskSize;
  }

  /** @return the region */
  public Region getRegion() {
    return region;
  }

  /** @param region the region to set */
  public void setRegion(Region region) {
    this.region = region;
  }

  /** @return the image */
  public Image getImage() {
    return image;
  }

  /** @param image the image to set */
  public void setImage(Image image) {
    this.image = image;
  }

  /** @return the size */
  public String getSize() {
    return size;
  }

  /** @param size the size to set */
  public void setSize(String size) {
    this.size = size;
  }

  /** @return the locked */
  public boolean isLocked() {
    return locked;
  }

  /** @param locked the locked to set */
  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  /** @return the status */
  public DropletStatus getStatus() {
    return status;
  }

  /** @param status the status to set */
  public void setStatus(DropletStatus status) {
    this.status = status;
  }

  /** @return the networks */
  public Networks getNetworks() {
    return networks;
  }

  /** @param networks the networks to set */
  public void setNetworks(Networks networks) {
    this.networks = networks;
  }

  /** @return the kernel */
  public Kernel getKernel() {
    return kernel;
  }

  /** @param kernel the kernel to set */
  public void setKernel(Kernel kernel) {
    this.kernel = kernel;
  }

  /** @return the createdDate */
  public Date getCreatedDate() {
    return createdDate;
  }

  /** @param createdDate the createdDate to set */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /** @return the features */
  public List<String> getFeatures() {
    return features;
  }

  /** @param features the features to set */
  public void setFeatures(List<String> features) {
    this.features = features;
  }

  /** @return the enableBackup */
  public Boolean getEnableBackup() {
    return enableBackup;
  }

  /** @param enableBackup the enableBackup to set */
  public void setEnableBackup(Boolean enableBackup) {
    this.enableBackup = enableBackup;
  }

  /** @return the enableIpv6 */
  public Boolean getEnableIpv6() {
    return enableIpv6;
  }

  /** @param enableIpv6 the enableIpv6 to set */
  public void setEnableIpv6(Boolean enableIpv6) {
    this.enableIpv6 = enableIpv6;
  }

  /** @return the enablePrivateNetworking */
  public Boolean getEnablePrivateNetworking() {
    return enablePrivateNetworking;
  }

  /** @param enablePrivateNetworking the enablePrivateNetworking to set */
  public void setEnablePrivateNetworking(Boolean enablePrivateNetworking) {
    this.enablePrivateNetworking = enablePrivateNetworking;
  }

  /** @return the backupIds */
  public List<Integer> getBackupIds() {
    return backupIds;
  }

  /** @param backupIds the backupIds to set */
  public void setBackupIds(List<Integer> backupIds) {
    this.backupIds = backupIds;
  }

  /** @return the snapshotIds */
  public List<Integer> getSnapshotIds() {
    return snapshotIds;
  }

  /** @param snapshotIds the snapshotIds to set */
  public void setSnapshotIds(List<Integer> snapshotIds) {
    this.snapshotIds = snapshotIds;
  }

  /** @return the keys */
  public List<Key> getKeys() {
    return keys;
  }

  /** @param keys the keys to set */
  public void setKeys(List<Key> keys) {
    this.keys = keys;
  }

  /** @return the userData */
  public String getUserData() {
    return userData;
  }

  /** @param userData the userData to set */
  public void setUserData(String userData) {
    this.userData = userData;
  }

  /** @return the volumeIds */
  public List<String> getVolumeIds() {
    return volumeIds;
  }

  /** @param volumeIds the volumeIds to set */
  public void setVolumeIds(List<String> volumeIds) {
    this.volumeIds = volumeIds;
  }

  /** @return the tags */
  public List<String> getTags() {
    return tags;
  }

  /** @param tags the tags to set */
  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  /** @return the installMonitoring */
  public Boolean getInstallMonitoring() {
    return installMonitoring;
  }

  /** @param installMonitoring the installMonitoring to set */
  public void setInstallMonitoring(Boolean installMonitoring) {
    this.installMonitoring = installMonitoring;
  }
}

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
import com.myjeeva.digitalocean.common.ActionStatus;
import com.myjeeva.digitalocean.common.ActionType;
import com.myjeeva.digitalocean.common.ResourceType;
import java.util.Date;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Action attributes
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public class Action extends Base {

  private static final long serialVersionUID = 5951525501167424430L;

  private Integer id;

  @SerializedName("resource_id")
  private Long resourceId;

  private ActionStatus status;

  private ActionType type;

  @SerializedName("resource_type")
  private ResourceType resourceType;

  @SerializedName("started_at")
  private Date startedAt;

  @SerializedName("completed_at")
  private Date completedAt;

  private Region region;

  @SerializedName("region_slug")
  private String regionSlug;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the id */
  public Integer getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(Integer id) {
    this.id = id;
  }

  /** @return the resourceId */
  public Long getResourceId() {
    return resourceId;
  }

  /** @param resourceId the resourceId to set */
  public void setResourceId(Long resourceId) {
    this.resourceId = resourceId;
  }

  /** @return the status */
  public ActionStatus getStatus() {
    return status;
  }

  /** @param status the status to set */
  public void setStatus(ActionStatus status) {
    this.status = status;
  }

  /** @return the type */
  public ActionType getType() {
    return type;
  }

  /** @param type the type to set */
  public void setType(ActionType type) {
    this.type = type;
  }

  /** @return the resourceType */
  public ResourceType getResourceType() {
    return resourceType;
  }

  /** @param resourceType the resourceType to set */
  public void setResourceType(ResourceType resourceType) {
    this.resourceType = resourceType;
  }

  /** @return the startedAt */
  public Date getStartedAt() {
    return startedAt;
  }

  /** @param startedAt the startedAt to set */
  public void setStartedAt(Date startedAt) {
    this.startedAt = startedAt;
  }

  /** @return the completedAt */
  public Date getCompletedAt() {
    return completedAt;
  }

  /** @param completedAt the completedAt to set */
  public void setCompletedAt(Date completedAt) {
    this.completedAt = completedAt;
  }

  /** @return the region */
  public Region getRegion() {
    return region;
  }

  /** @param region the region to set */
  public void setRegion(Region region) {
    this.region = region;
  }

  /** @return the regionSlug */
  public String getRegionSlug() {
    return regionSlug;
  }

  /** @param regionSlug the regionSlug to set */
  public void setRegionSlug(String regionSlug) {
    this.regionSlug = regionSlug;
  }
}

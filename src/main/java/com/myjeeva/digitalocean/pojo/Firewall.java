/**
 * The MIT License
 *
 * <p>Copyright (c) 2013-2020 Jeevanandam M. (jeeva@myjeeva.com) 2018 Lucas Andrey B.
 * (andreybleme1@gmail.com)
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
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Firewall attributes of DigitalOcean. Revised as per v2 API data structure.
 *
 * @author Lucas Andrey B. (andreybleme1@gmail.com)
 */
public class Firewall extends Base {

  private static final long serialVersionUID = -7669528307658280855L;

  private String id;

  private String status;

  @SerializedName("created_at")
  private Date createdDate;

  @SerializedName("pending_changes")
  private List<PendingChanges> pendingChanges;

  @SerializedName("name")
  private String name;

  @Expose
  @SerializedName("inbound_rules")
  private List<InboundRules> inboundRules;

  @Expose
  @SerializedName("outbound_rules")
  private List<OutboundRules> outboundRules;

  @SerializedName("droplet_ids")
  private List<Integer> dropletIds;

  private List<String> tags;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public List<PendingChanges> getPendingChanges() {
    return pendingChanges;
  }

  public void setPendingChanges(List<PendingChanges> pendingChanges) {
    this.pendingChanges = pendingChanges;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<InboundRules> getInboundRules() {
    return inboundRules;
  }

  public void setInboundRules(List<InboundRules> inboundRules) {
    this.inboundRules = inboundRules;
  }

  public List<OutboundRules> getOutboundRules() {
    return outboundRules;
  }

  public void setOutboundRules(List<OutboundRules> outboundRules) {
    this.outboundRules = outboundRules;
  }

  public List<Integer> getDropletIds() {
    return dropletIds;
  }

  public void setDropletIds(List<Integer> dropletIds) {
    this.dropletIds = dropletIds;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}

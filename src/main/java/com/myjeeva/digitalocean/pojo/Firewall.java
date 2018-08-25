package com.myjeeva.digitalocean.pojo;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Represents Firewall attributes of DigitalOcean. Revised as per v2 API data structure.
 * 
 * @author Lucas Andrey B. (andreybleme1@gmail.com)
 */
public class Firewall extends RateLimit {

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

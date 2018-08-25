package com.myjeeva.digitalocean.pojo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

/**
 * Represents PendingChanges for Firewalls
 *
 * @author Lucas Andrey B. (andreybleme1@gmail.com)
 */
public class PendingChanges {

  @SerializedName("droplet_id")
  private Integer dropletId;

  private Boolean removing;

  private String status;
  
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  public Integer getDropletId() {
    return dropletId;
  }

  public void setDropletId(Integer dropletId) {
    this.dropletId = dropletId;
  }

  public Boolean getRemoving() {
    return removing;
  }

  public void setRemoving(Boolean removing) {
    this.removing = removing;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }



}

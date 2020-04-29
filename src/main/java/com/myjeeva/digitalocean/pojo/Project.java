package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myjeeva.digitalocean.common.Environment;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Project attributes of DigitalOcean.
 *
 * @author Thomas Crombez (thomasc@trikthom.com)
 */
public class Project extends Base {

  private static final long serialVersionUID = -594801578631188284L;

  private String id;

  @SerializedName("owner_uuid")
  private String ownerUuid;

  @SerializedName("owner_id")
  private String ownerId;

  @Expose private String name;

  @Expose private String description;

  @Expose private String purpose;

  @Expose private Environment environment;

  @Expose
  @SerializedName("is_default")
  private boolean isDefault;

  @SerializedName("created_at")
  private String createdAt;

  @SerializedName("updated_at")
  private String updatedAt;

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

  public String getOwnerUuid() {
    return ownerUuid;
  }

  public void setOwnerUuid(String ownerUuid) {
    this.ownerUuid = ownerUuid;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPurpose() {
    return purpose;
  }

  public void setPurpose(String purpose) {
    this.purpose = purpose;
  }

  public Environment getEnvironment() {
    return environment;
  }

  public void setEnvironment(Environment environment) {
    this.environment = environment;
  }

  public boolean isDefault() {
    return isDefault;
  }

  public void setDefault(boolean aDefault) {
    isDefault = aDefault;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}

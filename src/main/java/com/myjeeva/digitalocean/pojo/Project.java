package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.Expose;
import com.myjeeva.digitalocean.common.Environment;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Project attributes of DigitalOcean.
 *
 * @author Thomas Crombez (thomasc@trikthom.com)
 */
public class Project extends Base {

//  private static final long serialVersionUID = ??;

  private String id;

  private String owner_uuid;

  private String owner_id;

  @Expose
  private String name;

  @Expose
  private String description;

  @Expose
  private String purpose;

  @Expose
  private Environment environment;

  @Expose
  private boolean is_default;

  private String created_at;

  private String updated_at;

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

  public String getOwner_uuid() {
    return owner_uuid;
  }

  public void setOwner_uuid(String owner_uuid) {
    this.owner_uuid = owner_uuid;
  }

  public String getOwner_id() {
    return owner_id;
  }

  public void setOwner_id(String owner_id) {
    this.owner_id = owner_id;
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

  public boolean getIs_default() {
    return is_default;
  }

  public void setIs_default(boolean is_default) {
    this.is_default = is_default;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(String updated_at) {
    this.updated_at = updated_at;
  }
}

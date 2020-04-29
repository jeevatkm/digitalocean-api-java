package com.myjeeva.digitalocean.common;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

/**
 * Enumeration of DigitalOcean Environments
 *
 * @author Thomas Crombez (thomasc@trikthom.com)
 */
public enum Environment {
  @SerializedName("Development")
  DEVELOPMENT("development"),

  @SerializedName("Staging")
  STAGING("staging"),

  @SerializedName("Production")
  PRODUCTION("production");

  private String value;

  private Environment(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public static Environment fromValue(String value) {
    if (StringUtils.isBlank(value)) {
      throw new IllegalArgumentException("Value cannot be null or empty!");
    }

    for (Environment environment : Environment.values()) {
      if (value.equalsIgnoreCase(environment.value)) {
        return environment;
      }
    }

    throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
  }
}

package com.myjeeva.digitalocean.pojo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Represents InboundRules for Firewalls
 *
 * @author Lucas Andrey B. (andreybleme1@gmail.com)
 */
public class InboundRules {

  @Expose
  @SerializedName("protocol")
  private String protocol;

  @Expose
  @SerializedName("ports")
  private String ports;

  @Expose
  @SerializedName("sources")
  private Sources sources;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public String getPorts() {
    return ports;
  }

  public void setPorts(String ports) {
    this.ports = ports;
  }

  public Sources getSources() {
    return sources;
  }

  public void setSources(Sources sources) {
    this.sources = sources;
  }

}

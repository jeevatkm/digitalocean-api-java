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

import com.google.gson.annotations.Expose;
import com.myjeeva.digitalocean.common.CaaTagType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents DomainRecord (TLD) Record attributes of DigitalOcean DNS. Revised as per v2 API data
 * structure.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class DomainRecord extends Base {

  private static final long serialVersionUID = 5656335027984698525L;

  private Integer id;

  @Expose private String type;

  @Expose private String name;

  @Expose private String data;

  @Expose private Integer priority;

  @Expose private Integer port;

  @Expose private Integer weight;

  @Expose private Integer ttl;

  @Expose private Integer flags;

  @Expose private CaaTagType tag;

  public DomainRecord() {
    // Default Constructor
  }

  public DomainRecord(String name) {
    this.name = name;
  }

  public DomainRecord(String name, String type) {
    this(name, null, type, null, null, null);
  }

  public DomainRecord(String name, String data, String type) {
    this(name, data, type, null, null, null);
  }

  public DomainRecord(
      String name, String data, String type, Integer priority, Integer port, Integer weight) {
    this(name, data, type, priority, null, null, null);
  }

  public DomainRecord(
      String name,
      String data,
      String type,
      Integer priority,
      Integer port,
      Integer weight,
      Integer ttl) {
    this.name = name;
    this.data = data;
    this.type = type;
    this.priority = priority;
    this.port = port;
    this.weight = weight;
    this.ttl = ttl;
  }

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

  /** @return the type */
  public String getType() {
    return type;
  }

  /** @param type the type to set */
  public void setType(String type) {
    this.type = type;
  }

  /** @return the name */
  public String getName() {
    return name;
  }

  /** @param name the name to set */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the data */
  public String getData() {
    return data;
  }

  /** @param data the data to set */
  public void setData(String data) {
    this.data = data;
  }

  /** @return the priority */
  public Integer getPriority() {
    return priority;
  }

  /** @param priority the priority to set */
  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  /** @return the port */
  public Integer getPort() {
    return port;
  }

  /** @param port the port to set */
  public void setPort(Integer port) {
    this.port = port;
  }

  /** @return the weight */
  public Integer getWeight() {
    return weight;
  }

  /** @param weight the weight to set */
  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  /** @return the TTL, in seconds */
  public Integer getTtl() {
    return ttl;
  }

  /** @param ttl the TTL, in seconds */
  public void setTtl(Integer ttl) {
    this.ttl = ttl;
  }

  /** @return the flags */
  public Integer getFlags() {
    return flags;
  }

  /** @param flags the flags to set */
  public void setFlags(Integer flags) {
    this.flags = flags;
  }

  /** @return the tag */
  public CaaTagType getTag() {
    return tag;
  }

  /** @param tag the tag to set */
  public void setTag(CaaTagType tag) {
    this.tag = tag;
  }
}

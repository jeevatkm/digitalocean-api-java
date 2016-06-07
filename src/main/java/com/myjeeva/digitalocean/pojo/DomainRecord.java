/**
 * The MIT License
 * 
 * Copyright (c) 2013-2016 Jeevanandam M. (myjeeva.com)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.myjeeva.digitalocean.pojo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;

/**
 * Represents DomainRecord (TLD) Record attributes of DigitalOcean DNS. Revised as per v2 API data
 * structure.
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class DomainRecord extends RateLimitBase {

  private static final long serialVersionUID = 5656335027984698525L;

  private Integer id;

  @Expose
  private String type;

  @Expose
  private String name;

  @Expose
  private String data;

  @Expose
  private String priority;

  @Expose
  private Integer port;

  @Expose
  private Integer weight;

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

  public DomainRecord(String name, String data, String type, String priority, Integer port,
      Integer weight) {
    this.name = name;
    this.data = data;
    this.type = type;
    this.priority = priority;
    this.port = port;
    this.weight = weight;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /**
   * @return the id
   */
  public Integer getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * @param type the type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the data
   */
  public String getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(String data) {
    this.data = data;
  }

  /**
   * @return the priority
   */
  public String getPriority() {
    return priority;
  }

  /**
   * @param priority the priority to set
   */
  public void setPriority(String priority) {
    this.priority = priority;
  }

  /**
   * @return the port
   */
  public Integer getPort() {
    return port;
  }

  /**
   * @param port the port to set
   */
  public void setPort(Integer port) {
    this.port = port;
  }

  /**
   * @return the weight
   */
  public Integer getWeight() {
    return weight;
  }

  /**
   * @param weight the weight to set
   */
  public void setWeight(Integer weight) {
    this.weight = weight;
  }
}

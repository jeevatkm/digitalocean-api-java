/*
 * The MIT License
 * 
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
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

import com.google.gson.annotations.SerializedName;

/**
 * Represents HTTP Method - DELETE response handling
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * 
 * @since v2.0
 */
public class Delete extends RateLimitBase {

  private static final long serialVersionUID = -3552374545843268569L;

  @SerializedName("request_status")
  private Boolean isRequestSuccess;

  @SerializedName("status_code")
  private int statusCode;

  /**
   * Default Constructor
   */
  public Delete() {
    // Default Constructor
  }

  /**
   * Parameterized Constructor
   * 
   * @param limit the number of requests that can be made per hour
   * @param remaining the number of requests that remain before you hit your request limit
   * @param reset this represents the time when the oldest request will expire
   */
  public Delete(Boolean isRequestSuccess) {
    this.isRequestSuccess = isRequestSuccess;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /**
   * @return the isRequestSuccess
   */
  public Boolean getIsRequestSuccess() {
    return isRequestSuccess;
  }

  /**
   * @param isRequestSuccess the isRequestSuccess to set
   */
  public void setIsRequestSuccess(Boolean isRequestSuccess) {
    this.isRequestSuccess = isRequestSuccess;
  }

  /**
   * @return the statusCode
   */
  public int getStatusCode() {
    return statusCode;
  }

  /**
   * @param statusCode the statusCode to set
   */
  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }
}

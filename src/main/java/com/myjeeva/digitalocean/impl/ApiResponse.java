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
package com.myjeeva.digitalocean.impl;

import com.myjeeva.digitalocean.common.ApiAction;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents DigitalOcean API Response details
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public class ApiResponse {

  private ApiAction apiAction;

  private Object data;

  private boolean requestSuccess;

  /** Default Constructor */
  public ApiResponse() {
    // Default constructor
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param requestSuccess result of the executed api request
   */
  public ApiResponse(ApiAction apiAction, boolean requestSuccess) {
    this(apiAction, null, requestSuccess);
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param data a api response object
   * @param requestSuccess result of the executed api request
   */
  public ApiResponse(ApiAction apiAction, Object data, boolean requestSuccess) {
    this.apiAction = apiAction;
    this.data = data;
    this.requestSuccess = requestSuccess;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  public boolean isDataExists() {
    return (null == data);
  }

  /** @return the apiAction */
  public ApiAction getApiAction() {
    return apiAction;
  }

  /** @param apiAction the apiAction to set */
  public void setApiAction(ApiAction apiAction) {
    this.apiAction = apiAction;
  }

  /** @return the data */
  public Object getData() {
    return data;
  }

  /** @param data the data to set */
  public void setData(Object data) {
    this.data = data;
  }

  /** @return the requestSuccess */
  public boolean isRequestSuccess() {
    return requestSuccess;
  }

  /** @param requestSuccess the requestSuccess to set */
  public void setRequestSuccess(boolean requestSuccess) {
    this.requestSuccess = requestSuccess;
  }
}

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
package com.myjeeva.digitalocean.impl;

import com.myjeeva.digitalocean.common.ApiAction;
import com.myjeeva.digitalocean.common.RequestMethod;

/**
 * Represents DigitalOcean API Request details
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * 
 * @since v2.0
 */
public class ApiRequest {

  private ApiAction apiAction;

  private Object data;

  private Object[] params;

  private Integer pageNo;

  public ApiRequest() {
    // Default Constructor
  }

  public ApiRequest(ApiAction apiAction, Object[] params) {
    this(apiAction, null, params, null);
  }

  public ApiRequest(ApiAction apiAction, Integer pageNo) {
    this(apiAction, null, null, pageNo);
  }

  public ApiRequest(ApiAction apiAction, Object data) {
    this(apiAction, data, null, null);
  }

  public ApiRequest(ApiAction apiAction, Object[] params, Integer pageNo) {
    this(apiAction, null, params, pageNo);
  }

  public ApiRequest(ApiAction apiAction, Object data, Object[] params) {
    this(apiAction, data, params, null);
  }

  public ApiRequest(ApiAction apiAction, Object data, Object[] params, Integer pageNo) {
    this.apiAction = apiAction;
    this.data = data;
    this.params = params;
    this.pageNo = pageNo;
  }

  /**
   * @return the path
   */
  public String getPath() {
    return apiAction.getPath();
  }

  /**
   * @return the elementName
   */
  public String getElementName() {
    return apiAction.getElementName();
  }

  /**
   * @return the method
   */
  public RequestMethod getMethod() {
    return apiAction.getMethod();
  }

  /**
   * @return the clazz
   */
  public Class<?> getClazz() {
    return apiAction.getClazz();
  }

  /**
   * @return the apiAction
   */
  public ApiAction getApiAction() {
    return apiAction;
  }

  /**
   * @param apiAction the apiAction to set
   */
  public void setApiAction(ApiAction apiAction) {
    this.apiAction = apiAction;
  }

  /**
   * @return the data
   */
  public Object getData() {
    return data;
  }

  /**
   * @param data the data to set
   */
  public void setData(Object data) {
    this.data = data;
  }

  /**
   * @return the params
   */
  public Object[] getParams() {
    return params;
  }

  /**
   * @param params the params to set
   */
  public void setParams(Object[] params) {
    this.params = params;
  }

  /**
   * @return the pageNo
   */
  public Integer getPageNo() {
    return pageNo;
  }

  /**
   * @param pageNo the pageNo to set
   */
  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }
}

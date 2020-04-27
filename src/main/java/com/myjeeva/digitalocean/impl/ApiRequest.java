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
import com.myjeeva.digitalocean.common.RequestMethod;
import java.util.Map;

/**
 * Represents DigitalOcean API Request details
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public class ApiRequest {

  private ApiAction apiAction;

  private Object data;

  private Object[] pathParams;

  private Map<String, String> queryParams;

  private Integer pageNo;

  private Integer perPage;

  /** Default Constructor */
  public ApiRequest() {
    // Default Constructor
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   */
  public ApiRequest(ApiAction apiAction) {
    this(apiAction, null, null, null, null, null);
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param pathParams a api request path variable value(s)
   */
  public ApiRequest(ApiAction apiAction, Object[] pathParams) {
    this(apiAction, null, pathParams, null, null, null);
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param pageNo of the request pagination
   * @param perPage no. of items per page
   */
  public ApiRequest(ApiAction apiAction, Integer pageNo, Integer perPage) {
    this(apiAction, null, null, pageNo, null, perPage);
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param pageNo of the request pagination
   * @param queryParams of the api request
   * @param perPage no. of items per page
   */
  public ApiRequest(
      ApiAction apiAction, Integer pageNo, Map<String, String> queryParams, Integer perPage) {
    this(apiAction, null, null, pageNo, queryParams, perPage);
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param data a api request body data object
   */
  public ApiRequest(ApiAction apiAction, Object data) {
    this(apiAction, data, null, null, null, null);
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param pathParams a api request path variable value(s)
   * @param pageNo of the request pagination
   * @param perPage no. of items per page
   */
  public ApiRequest(ApiAction apiAction, Object[] pathParams, Integer pageNo, Integer perPage) {
    this(apiAction, null, pathParams, pageNo, null, perPage);
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param data a api request body data object
   * @param pathParams a api request path variable value(s)
   */
  public ApiRequest(ApiAction apiAction, Object data, Object[] pathParams) {
    this(apiAction, data, pathParams, null, null, null);
  }

  /**
   * Constructor
   *
   * @param apiAction a info about api request
   * @param data a api request body data object
   * @param pathParams a api request path variable value(s)
   * @param pageNo of the request pagination
   * @param queryParams of the api request
   * @param perPage no. of items per page
   */
  public ApiRequest(
      ApiAction apiAction,
      Object data,
      Object[] pathParams,
      Integer pageNo,
      Map<String, String> queryParams,
      Integer perPage) {
    this.apiAction = apiAction;
    this.data = data;
    this.pathParams = pathParams;
    this.pageNo = pageNo;
    this.queryParams = queryParams;
    this.perPage = perPage;
  }

  public Boolean isCollectionElement() {
    return getElementName().endsWith("s");
  }

  /** @return the path */
  public String getPath() {
    return apiAction.getPath();
  }

  /** @return the elementName */
  public String getElementName() {
    return apiAction.getElementName();
  }

  /** @return the method */
  public RequestMethod getMethod() {
    return apiAction.getMethod();
  }

  /** @return the clazz */
  public Class<?> getClazz() {
    return apiAction.getClazz();
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

  /** @return the pathParams */
  public Object[] getPathParams() {
    return pathParams;
  }

  /** @param pathParams the pathParams to set */
  public void setPathParams(Object[] pathParams) {
    this.pathParams = pathParams;
  }

  /** @return the queryParams */
  public Map<String, String> getQueryParams() {
    return queryParams;
  }

  /** @param queryParams the queryParams to set */
  public void setQueryParams(Map<String, String> queryParams) {
    this.queryParams = queryParams;
  }

  /** @return the pageNo */
  public Integer getPageNo() {
    return pageNo;
  }

  /** @param pageNo the pageNo to set */
  public void setPageNo(Integer pageNo) {
    this.pageNo = pageNo;
  }

  /** @return the perPage */
  public Integer getPerPage() {
    return perPage;
  }

  /** @param perPage the perPage to set */
  public void setPerPage(Integer perPage) {
    this.perPage = perPage;
  }
}

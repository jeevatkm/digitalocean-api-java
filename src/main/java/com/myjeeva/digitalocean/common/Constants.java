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
package com.myjeeva.digitalocean.common;

import org.apache.http.HttpHeaders;

/**
 * DigitalOcean API client Constants
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v1.0
 */
public interface Constants {

  // General
  String UTF_8 = "utf-8";
  Integer START_PAGE_NO = 1;
  String URL_PATH_SEPARATOR = "/";
  String USER_AGENT = "DigitalOcean API Client by myjeeva.com";
  String JSON_CONTENT_TYPE = "application/json";
  String FORM_URLENCODED_CONTENT_TYPE = "application/x-www-form-urlencoded";
  String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssX";
  String RATE_LIMIT_JSON_STRUCT =
      "\"rate_limit\": { \"limit\": %s, \"remaining\": %s, \"reset\": \"%s\"}";
  String RATE_LIMIT_ELEMENT_NAME = "rate_limit";
  String LINKS_ELEMENT_NAME = "links";
  String META_ELEMENT_NAME = "meta";

  // HTTPS Scheme
  String HTTPS_SCHEME = "https";

  // HTTP Param name
  String PARAM_PAGE_NO = "page";
  String PARAM_PER_PAGE = "per_page";
  int DEFAULT_PAGE_SIZE = 25; // per DO doc

  String NO_CONTENT_JSON_STRUCT = "{\"response\": {\"request_status\": true, \"status_code\": %s}}";

  // HTTP Headers
  String HDR_USER_AGENT = "X-User-Agent";
  String HDR_CONTENT_TYPE = HttpHeaders.CONTENT_TYPE;
  String HDR_AUTHORIZATION = HttpHeaders.AUTHORIZATION;
  String HDR_RATE_LIMIT = "RateLimit-Limit";
  String HDR_RATE_REMAINING = "RateLimit-Remaining";
  String HDR_RATE_RESET = "RateLimit-Reset";
}

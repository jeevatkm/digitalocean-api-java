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
package com.myjeeva.digitalocean.exception;

/**
 * <code>DigitalOceanException</code> will be thrown, when request had interruption [ <code>
 * HTTP status code &gt;= 400 &amp;&amp; &lt; 510</code>] DigitalOcean API
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class DigitalOceanException extends Exception {

  static final long serialVersionUID = -925220451573356906L;

  private String id;

  private int httpStatusCode;

  public DigitalOceanException(String msg) {
    super(msg);
  }

  public DigitalOceanException(String msg, Throwable t) {
    super(msg, t);
  }

  public DigitalOceanException(String msg, String id, int statusCode) {
    super(msg);
    this.id = id;
    this.httpStatusCode = statusCode;
  }

  /** @return the id */
  public String getId() {
    return id;
  }

  /** @return the httpStatusCode */
  public int getHttpStatusCode() {
    return httpStatusCode;
  }
}

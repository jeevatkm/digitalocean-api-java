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

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Rate Limit header values
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public class RateLimit implements Serializable {

  private static final long serialVersionUID = 4523060082914942360L;

  private Integer limit;

  private Integer remaining;

  private Date reset;

  /** Default Constructor */
  public RateLimit() {
    // Default Constructor
  }

  /**
   * Parameterized Constructor
   *
   * @param limit the number of requests that can be made per hour
   * @param remaining the number of requests that remain before you hit your request limit
   * @param reset this represents the time when the oldest request will expire
   */
  public RateLimit(Integer limit, Integer remaining, Date reset) {
    this.limit = limit;
    this.remaining = remaining;
    this.reset = reset;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the limit */
  public Integer getLimit() {
    return limit;
  }

  /** @return the remaining */
  public Integer getRemaining() {
    return remaining;
  }

  /** @return the reset */
  public Date getReset() {
    return reset;
  }
}

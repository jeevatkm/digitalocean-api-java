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

import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents LoadBalancers attributes
 *
 * @author Thomas Lehoux (https://github.com/tlehoux)
 * @since v2.11
 */
public class LoadBalancers extends Base {

  private static final long serialVersionUID = 5440139918030950627L;

  @SerializedName("load_balancers")
  private List<LoadBalancer> loadBalancers;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the droplets */
  public List<LoadBalancer> getLoadBalancers() {
    return loadBalancers;
  }

  /** @param loadBalancers the droplets to set */
  public void setLoadBalancers(List<LoadBalancer> loadBalancers) {
    this.loadBalancers = loadBalancers;
  }
}

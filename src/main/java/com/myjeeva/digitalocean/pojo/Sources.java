/**
 * The MIT License
 *
 * <p>Copyright (c) 2013-2020 Jeevanandam M. (jeeva@myjeeva.com) 2018 Lucas Andrey B.
 * (andreybleme1@gmail.com)
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
import com.google.gson.annotations.SerializedName;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Sources for InboundRules used by Firewalls
 *
 * @author Lucas Andrey B. (andreybleme1@gmail.com)
 */
public class Sources {

  @Expose private List<String> addresses;

  @Expose
  @SerializedName("droplet_ids")
  private List<Integer> dropletIds;

  @Expose
  @SerializedName("load_balancer_uids")
  private List<String> loadBalancerUids;

  @Expose
  @SerializedName("tags")
  private List<String> tags;

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  public List<String> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<String> addresses) {
    this.addresses = addresses;
  }

  public List<Integer> getDropletIds() {
    return dropletIds;
  }

  public void setDropletIds(List<Integer> dropletIds) {
    this.dropletIds = dropletIds;
  }

  public List<String> getLoadBalancerUids() {
    return loadBalancerUids;
  }

  public void setLoadBalancerUids(List<String> loadBalancerUids) {
    this.loadBalancerUids = loadBalancerUids;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }
}

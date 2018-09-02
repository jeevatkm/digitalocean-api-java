/**
 * The MIT License
 * 
 * Copyright (c) 2013-2018 Jeevanandam M. (myjeeva.com)
 *               2018 Lucas Andrey B. (andreybleme1@gmail.com)
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

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.SerializedName;

/**
 * Represents Firewalls attributes.
 * 
 * @author Lucas Andrey B. (andreybleme1@gmail.com)
 */
public class Firewalls extends Base {

  private static final long serialVersionUID = -6358515137857888620L;
  
  @SerializedName("firewalls")
  private List<Firewall> firewalls;
  
  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  public List<Firewall> getFirewalls() {
    return firewalls;
  }

  public void setFirewalls(List<Firewall> firewalls) {
    this.firewalls = firewalls;
  }
  
}

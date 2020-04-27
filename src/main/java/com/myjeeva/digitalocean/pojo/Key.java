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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents SSH Key attributes of DigitalOcean. Revised as per v2 API data structure.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Key extends Base {

  private static final long serialVersionUID = 3454646433241484585L;

  @Expose private Integer id;

  @Expose private String name;

  @Expose private String fingerprint;

  @Expose
  @SerializedName("public_key")
  private String publicKey;

  public Key() {
    // Default constructor
  }

  public Key(Integer id) {
    this.id = id;
  }

  public Key(String name) {
    this.name = name;
  }

  public Key(String name, String publicKey) {
    this.name = name;
    this.publicKey = publicKey;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the id */
  public Integer getId() {
    return id;
  }

  /** @param id the id to set */
  public void setId(Integer id) {
    this.id = id;
  }

  /** @return the name */
  public String getName() {
    return name;
  }

  /** @param name the name to set */
  public void setName(String name) {
    this.name = name;
  }

  /** @return the fingerprint */
  public String getFingerprint() {
    return fingerprint;
  }

  /** @param fingerprint the fingerprint to set */
  public void setFingerprint(String fingerprint) {
    this.fingerprint = fingerprint;
  }

  /** @return the publicKey */
  public String getPublicKey() {
    return publicKey;
  }

  /** @param publicKey the publicKey to set */
  public void setPublicKey(String publicKey) {
    this.publicKey = publicKey;
  }
}

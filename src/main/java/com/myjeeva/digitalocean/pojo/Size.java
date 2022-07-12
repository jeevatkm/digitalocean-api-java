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
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents Droplet Size (aka Droplet Plan) attributes of DigitalOcean. Revised as per v2 API data
 * structure.
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Size extends Base {

  private static final long serialVersionUID = -4657640557301808868L;

  private String slug;

  @SerializedName("memory")
  private Integer memorySizeInMb;

  @SerializedName("vcpus")
  private Integer virutalCpuCount;

  @SerializedName("disk")
  private Integer diskSize;

  private Double transfer;

  @SerializedName("price_monthly")
  private BigDecimal priceMonthly;

  @SerializedName("price_hourly")
  private BigDecimal priceHourly;

  private List<String> regions;

  private boolean available;

  public Size() {
    // Default constructor
  }

  public Size(String slug) {
    this.slug = slug;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

  /** @return the slug */
  public String getSlug() {
    return slug;
  }

  /** @param slug the slug to set */
  public void setSlug(String slug) {
    this.slug = slug;
  }

  /** @return the memorySizeInMb */
  public Integer getMemorySizeInMb() {
    return memorySizeInMb;
  }

  /** @param memorySizeInMb the memorySizeInMb to set */
  public void setMemorySizeInMb(Integer memorySizeInMb) {
    this.memorySizeInMb = memorySizeInMb;
  }

  /** @return the virutalCpuCount */
  public Integer getVirutalCpuCount() {
    return virutalCpuCount;
  }

  /** @param virutalCpuCount the virutalCpuCount to set */
  public void setVirutalCpuCount(Integer virutalCpuCount) {
    this.virutalCpuCount = virutalCpuCount;
  }

  /** @return the diskSize */
  public Integer getDiskSize() {
    return diskSize;
  }

  /** @param diskSize the diskSize to set */
  public void setDiskSize(Integer diskSize) {
    this.diskSize = diskSize;
  }

  /** @return the transfer */
  public Double getTransfer() {
    return transfer;
  }

  /** @param transfer the transfer to set */
  public void setTransfer(Double transfer) {
    this.transfer = transfer;
  }

  /** @return the priceMonthly */
  public BigDecimal getPriceMonthly() {
    return priceMonthly;
  }

  /** @param priceMonthly the priceMonthly to set */
  public void setPriceMonthly(BigDecimal priceMonthly) {
    this.priceMonthly = priceMonthly;
  }

  /** @return the priceHourly */
  public BigDecimal getPriceHourly() {
    return priceHourly;
  }

  /** @param priceHourly the priceHourly to set */
  public void setPriceHourly(BigDecimal priceHourly) {
    this.priceHourly = priceHourly;
  }

  /** @return the regions */
  public List<String> getRegions() {
    return regions;
  }

  /** @param regions the regions to set */
  public void setRegions(List<String> regions) {
    this.regions = regions;
  }

  /** @return the available */
  public boolean isAvailable() {
    return available;
  }

  /** @param available the available to set */
  public void setAvailable(boolean available) {
    this.available = available;
  }
}

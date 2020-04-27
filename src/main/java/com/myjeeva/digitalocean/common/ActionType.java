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

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.StringUtils;

/**
 * Enumeration of DigitalOcean Droplet &amp; Image Action Type
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public enum ActionType {
  @SerializedName("create")
  CREATE("create"),

  @SerializedName("reboot")
  REBOOT("reboot"),

  @SerializedName("power_cycle")
  POWER_CYCLE("power_cycle"),

  @SerializedName("shutdown")
  SHUTDOWN("shutdown"),

  @SerializedName("power_off")
  POWER_OFF("power_off"),

  @SerializedName("power_on")
  POWER_ON("power_on"),

  @SerializedName("password_reset")
  PASSWORD_RESET("password_reset"),

  @SerializedName("resize")
  RESIZE("resize"),

  @SerializedName("restore")
  RESTORE("restore"),

  @SerializedName("rebuild")
  REBUILD("rebuild"),

  @SerializedName("rename")
  RENAME("rename"),

  @SerializedName("change_kernel")
  CHANGE_KERNEL("change_kernel"),

  @SerializedName("enable_ipv6")
  ENABLE_IPV6("enable_ipv6"),

  @SerializedName("enable_backups")
  ENABLE_BACKUPS("enable_backups"),

  @SerializedName("disable_backups")
  DISABLE_BACKUPS("disable_backups"),

  @SerializedName("enable_private_networking")
  ENABLE_PRIVATE_NETWORKING("enable_private_networking"),

  @SerializedName("snapshot")
  SNAPSHOT("snapshot"),

  @SerializedName("backup")
  BACKUP("backup"),

  @SerializedName("transfer")
  TRANSFER("transfer"),

  @SerializedName("convert")
  CONVERT("convert"),

  @SerializedName("convert_to_snapshot")
  CONVERT_TO_SNAPSHOT("convert_to_snapshot"),

  @SerializedName("distribution")
  DISTRIBUTION("distribution"),

  @SerializedName("application")
  APPLICATION("application"),

  @SerializedName("assign_ip")
  ASSIGN_FLOATING_IP("assign_ip"),

  @SerializedName("unassign_ip")
  UNASSIGN_FLOATING_IP("unassign_ip"),

  @SerializedName("reserve_ip")
  RESERVE_FLOATING_IP("reserve_ip"),

  @SerializedName(
      value = "attach",
      alternate = {"attach_volume"})
  ATTACH("attach"),

  @SerializedName(
      value = "detach",
      alternate = {"detach_volume"})
  DETACH("detach");

  private String value;

  private ActionType(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public static ActionType fromValue(String value) {
    if (StringUtils.isBlank(value)) {
      throw new IllegalArgumentException("Value cannot be null or empty!");
    }

    for (ActionType dat : ActionType.values()) {
      if (value.equalsIgnoreCase(dat.value)) {
        return dat;
      }
    }

    throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
  }
}

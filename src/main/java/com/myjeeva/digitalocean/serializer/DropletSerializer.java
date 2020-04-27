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
package com.myjeeva.digitalocean.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.Key;
import java.lang.reflect.Type;
import org.apache.commons.lang3.StringUtils;

/**
 * Serializer for droplet class
 *
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * @since v2.0
 */
public class DropletSerializer implements JsonSerializer<Droplet> {

  @Override
  public JsonElement serialize(Droplet droplet, Type paramType, JsonSerializationContext context) {
    final JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("name", droplet.getName());

    if (null != droplet.getNames() && !droplet.getNames().isEmpty()) {
      JsonArray names = new JsonArray();
      for (String name : droplet.getNames()) {
        names.add(context.serialize(name));
      }
      jsonObject.add("names", names);
    }

    jsonObject.addProperty("region", droplet.getRegion().getSlug());
    jsonObject.addProperty("size", droplet.getSize());

    if (null == droplet.getImage().getId()) {
      jsonObject.addProperty("image", droplet.getImage().getSlug());
    } else {
      jsonObject.addProperty("image", droplet.getImage().getId());
    }

    if (null != droplet.getEnableBackup()) {
      jsonObject.addProperty("backups", droplet.getEnableBackup());
    }

    if (null != droplet.getEnableIpv6()) {
      jsonObject.addProperty("ipv6", droplet.getEnableIpv6());
    }

    if (null != droplet.getEnablePrivateNetworking()) {
      jsonObject.addProperty("private_networking", droplet.getEnablePrivateNetworking());
    }

    if (null != droplet.getKeys() && !droplet.getKeys().isEmpty()) {
      JsonArray sshKeys = new JsonArray();
      for (Key k : droplet.getKeys()) {
        if (null != k.getId()) {
          sshKeys.add(context.serialize(k.getId()));
        }
        if (!StringUtils.isBlank(k.getFingerprint())) {
          sshKeys.add(context.serialize(k.getFingerprint()));
        }
      }
      jsonObject.add("ssh_keys", sshKeys);
    }

    // #19
    if (null != droplet.getUserData()) {
      jsonObject.addProperty("user_data", droplet.getUserData());
    }

    // #56
    if (null != droplet.getVolumeIds() && !droplet.getVolumeIds().isEmpty()) {
      JsonArray volumes = new JsonArray();
      for (String volume : droplet.getVolumeIds()) {
        volumes.add(context.serialize(volume));
      }
      jsonObject.add("volumes", volumes);
    }

    // #56
    if (null != droplet.getTags() && !droplet.getTags().isEmpty()) {
      JsonArray tags = new JsonArray();
      for (String tag : droplet.getTags()) {
        tags.add(context.serialize(tag));
      }
      jsonObject.add("tags", tags);
    }

    // #70
    if (null != droplet.getInstallMonitoring()) {
      jsonObject.addProperty("monitoring", droplet.getInstallMonitoring());
    }

    return jsonObject;
  }
}

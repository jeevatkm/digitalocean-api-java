/*
 * The MIT License
 * 
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
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
package com.myjeeva.digitalocean.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.myjeeva.digitalocean.pojo.Droplet;

/**
 * Serializer for droplet class
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * 
 * @since v2.0
 */
public class DropletSerializer implements JsonSerializer<Droplet> {

  @Override
  public JsonElement serialize(Droplet droplet, Type paramType, JsonSerializationContext context) {

    final JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("name", droplet.getName());
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

    if (null != droplet.getKeys() && droplet.getKeys().size() > 0) {
      final JsonElement jsonSshKeys = context.serialize(droplet.getKeys());
      jsonObject.add("ssh_keys", jsonSshKeys);
    }

    // #19 - https://github.com/jeevatkm/digitalocean-api-java/issues/19
    if (null != droplet.getUserData()) {
      jsonObject.addProperty("user_data", droplet.getUserData());
    }

    return jsonObject;
  }

}

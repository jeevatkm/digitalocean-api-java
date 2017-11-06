/**
 * Copyright (c) Jeevanandam M. (https://github.com/jeevatkm)
 * 
 * digitalocean-api-client source code and usage is governed by a MIT style license that can be
 * found in the LICENSE file
 */

package com.myjeeva.digitalocean.serializer;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.myjeeva.digitalocean.pojo.Volume;

/**
 * Serialize the volume info for POST request.
 * 
 * @author Eugene Strokin (https://github.com/strokine)
 * 
 * @since v2.7
 */
public class VolumeSerializer implements JsonSerializer<Volume> {

  @Override
  public JsonElement serialize(Volume volume, Type paramType, JsonSerializationContext context) {
    final JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("id", volume.getId());
    jsonObject.addProperty("name", volume.getName());

    if (StringUtils.isNotBlank(volume.getDescription())) {
      jsonObject.addProperty("description", volume.getDescription());
    }

    if (StringUtils.isNotBlank(volume.getRegion().getSlug())) {
      jsonObject.addProperty("region", volume.getRegion().getSlug());
    }

    if (StringUtils.isNotBlank(volume.getSnapshotId())) {
      jsonObject.addProperty("snapshot_id", volume.getSnapshotId());
    }

    if (null != volume.getSize()) {
      jsonObject.addProperty("size_gigabytes", volume.getSize());
    }

    return jsonObject;
  }

}

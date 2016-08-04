package com.myjeeva.digitalocean.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.myjeeva.digitalocean.pojo.Volume;

public class VolumeSerializer implements JsonSerializer<Volume> {

  @Override
  public JsonElement serialize(Volume volume, Type paramType, JsonSerializationContext context) {
    final JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("id", volume.getId());
    jsonObject.addProperty("name", volume.getName());
    jsonObject.addProperty("description", volume.getDescription());
    jsonObject.addProperty("region", volume.getRegion().getSlug());
    jsonObject.addProperty("size_gigabytes", volume.getSizeGigabytes());

    return jsonObject;
  }

}

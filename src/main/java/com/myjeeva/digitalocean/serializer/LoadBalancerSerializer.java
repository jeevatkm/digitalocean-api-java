/**
 * Copyright (c) Jeevanandam M. (https://github.com/jeevatkm)
 *
 * <p>digitalocean-api-client source code and usage is governed by a MIT style license that can be
 * found in the LICENSE file
 */
package com.myjeeva.digitalocean.serializer;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.myjeeva.digitalocean.pojo.ForwardingRules;
import com.myjeeva.digitalocean.pojo.LoadBalancer;
import java.lang.reflect.Type;

/**
 * Serialize the load balancer info for POST request.
 *
 * @author Thomas Lehoux (https://github.com/tlehoux)
 * @since v2.11
 */
public class LoadBalancerSerializer implements JsonSerializer<LoadBalancer> {

  @Override
  public JsonElement serialize(
      LoadBalancer loadBalancer, Type paramType, JsonSerializationContext context) {
    final JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("name", loadBalancer.getName());

    jsonObject.addProperty("region", loadBalancer.getRegion().getSlug());

    if (null != loadBalancer.getAlgorithm()) {
      jsonObject.addProperty("algorithm", loadBalancer.getAlgorithm().toString());
    }

    if (null != loadBalancer.getForwardingRules() && !loadBalancer.getForwardingRules().isEmpty()) {
      JsonArray rules = new JsonArray();
      for (ForwardingRules rule : loadBalancer.getForwardingRules()) {
        rules.add(context.serialize(rule));
      }
      jsonObject.add("forwarding_rules", rules);
    }

    if (null != loadBalancer.getHealthCheck()) {
      jsonObject.add("health_check", context.serialize(loadBalancer.getHealthCheck()));
    }

    if (null != loadBalancer.getStickySessions()) {
      jsonObject.add("sticky_sessions", context.serialize(loadBalancer.getStickySessions()));
    }

    if (null != loadBalancer.getDropletIds() && !loadBalancer.getDropletIds().isEmpty()) {
      JsonArray dropletIds = new JsonArray();
      for (String dropletId : loadBalancer.getDropletIds()) {
        dropletIds.add(context.serialize(dropletId));
      }
      jsonObject.add("droplet_ids", dropletIds);
    }
    return jsonObject;
  }
}

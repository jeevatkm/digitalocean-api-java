package com.myjeeva.digitalocean.serializer;

import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.myjeeva.digitalocean.pojo.Firewall;
import com.myjeeva.digitalocean.pojo.InboundRules;
import com.myjeeva.digitalocean.pojo.OutboundRules;

/**
 * Serializer for firewall class
 * 
 * @author Lucas Andrey B. (andreybleme1@gmail.com)
 * 
 * @since v2.15
 */
public class FirewallSerializer implements JsonSerializer<Firewall> {

  @Override
  public JsonElement serialize(Firewall firewall, Type paramType,
      JsonSerializationContext context) {
    final JsonObject jsonObject = new JsonObject();

    jsonObject.addProperty("name", firewall.getName());

    if (null != firewall.getInboundRules() && !firewall.getInboundRules().isEmpty()) {
      JsonArray inboundRules = new JsonArray();
      for (InboundRules inboundRule : firewall.getInboundRules()) {
        inboundRules.add(context.serialize(inboundRule));
      }
      jsonObject.add("inbound_rules", inboundRules);
    }

    if (null != firewall.getOutboundRules() && !firewall.getOutboundRules().isEmpty()) {
      JsonArray outboundRules = new JsonArray();
      for (OutboundRules outboundRule : firewall.getOutboundRules()) {
        outboundRules.add(context.serialize(outboundRule));
      }
      jsonObject.add("outbound_rules", outboundRules);
    }

    if (null != firewall.getDropletIds() && !firewall.getDropletIds().isEmpty()) {
      JsonArray dropletIds = new JsonArray();
      for (Integer dropletId : firewall.getDropletIds()) {
        dropletIds.add(context.serialize(dropletId));
      }
      jsonObject.add("droplet_ids", dropletIds);
    }

    if (null != firewall.getTags() && !firewall.getTags().isEmpty()) {
      JsonArray tags = new JsonArray();
      for (String tag : firewall.getTags()) {
        tags.add(context.serialize(tag));
      }
      jsonObject.add("tags", tags);
    }

    return jsonObject;
  }

}

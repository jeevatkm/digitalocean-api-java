/**
 * The MIT License
 *
 * <p>Copyright (c) 2013-2020 Jeevanandam M. (jeeva@myjeeva.com) 2018 Lucas Andrey B.
 * (andreybleme1@gmail.com)
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
import com.myjeeva.digitalocean.pojo.Firewall;
import com.myjeeva.digitalocean.pojo.InboundRules;
import com.myjeeva.digitalocean.pojo.OutboundRules;
import java.lang.reflect.Type;

/**
 * Serializer for firewall class
 *
 * @author Lucas Andrey B. (andreybleme1@gmail.com)
 * @since v2.16
 */
public class FirewallSerializer implements JsonSerializer<Firewall> {

  @Override
  public JsonElement serialize(
      Firewall firewall, Type paramType, JsonSerializationContext context) {
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

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
package com.myjeeva.digitalocean;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import com.myjeeva.digitalocean.pojo.Domain;
import com.myjeeva.digitalocean.pojo.DomainRecord;
import com.myjeeva.digitalocean.pojo.Droplet;
import com.myjeeva.digitalocean.pojo.DropletImage;
import com.myjeeva.digitalocean.pojo.DropletSize;
import com.myjeeva.digitalocean.pojo.Region;
import com.myjeeva.digitalocean.pojo.SshKey;

/**
 * DigitalOcean API client Constants
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public interface Constants {

  // General
  String UTF_8 = "utf-8";

  // HTTPS Scheme
  String HTTPS_SCHEME = "https";

  // HTTP Param name
  String PARAM_CLIENT_ID = "client_id";
  String PARAM_API_KEY = "api_key";
  String PARAM_NAME = "name";
  String PARAM_SIDE_ID = "size_id";
  String PARAM_REGION_ID = "region_id";
  String PARAM_IMAGE_ID = "image_id";
  String PARAM_SSH_KEY_IDS = "ssh_key_ids";
  String PARAM_IP_ADDRESS = "ip_address";
  String PARAM_RECORD_TYPE = "record_type";
  String PARAM_DATA = "data";
  String PARAM_PRIORITY = "priority";
  String PARAM_PORT = "port";
  String PARAM_WEIGHT = "weight";
  String PARAM_SSH_PUB_KEY = "ssh_pub_key";

  // JSON Element Name
  String STATUS = "status";

  // Gson Type Tokens
  Type TYPE_DROPLET_LIST = new TypeToken<List<Droplet>>() {}.getType();

  Type TYPE_IMAGE_LIST = new TypeToken<List<DropletImage>>() {}.getType();

  Type TYPE_REGION_LIST = new TypeToken<List<Region>>() {}.getType();

  Type TYPE_SIZE_LIST = new TypeToken<List<DropletSize>>() {}.getType();

  Type TYPE_DOMAIN_LIST = new TypeToken<List<Domain>>() {}.getType();

  Type TYPE_DOMAIN_RECORD_LIST = new TypeToken<List<DomainRecord>>() {}.getType();

  Type TYPE_SSH_KEY_LIST = new TypeToken<List<SshKey>>() {}.getType();
}

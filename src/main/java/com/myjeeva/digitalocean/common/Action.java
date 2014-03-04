/* The MIT License
 *
 * Copyright (c) 2010-2014 Jeevanandam M. (myjeeva.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE. 
 * 
 */
package com.myjeeva.digitalocean.common;

/**
 * Enumeration of DigitalOcean API and its map path and element name
 * 
 * @author Jeevanandam (jeeva@myjeeva.com)
 */
public enum Action {

    AVAILABLE_DROPLETS("/droplets/", "droplets"),
    CREATE_DROPLET("/droplets/new/", "droplet"),
    GET_DROPLET_INFO("/droplets/%s/", "droplet"),
    REBOOT_DROPLET("/droplets/%s/reboot/"),
    POWER_CYCLE_DROPLET("/droplets/%s/power_cycle/"),
    SHUTDOWN_DROPLET("/droplets/%s/shutdown/"),
    POWER_OFF_DROPLET("/droplets/%s/power_off/"),
    POWER_ON_DROPLET("/droplets/%s/power_on/"), 
    RESET_PASSWORD_DROPLET("/droplets/%s/password_reset/"),
    RESIZE_DROPLET("/droplets/%s/resize/", ""),
    TAKE_DROPLET_SNAPSHOT("/droplets/%s/snapshot/"),
    RESTORE_DROPLET("/droplets/%s/resize/"),
    REBUILD_DROPLET("/droplets/%s/rebuild/"),
    ENABLE_AUTOMATIC_BACKUPS("/droplets/%s/enable_backups/"),
    DISABLE_AUTOMATIC_BACKUPS("/droplets/%s/disable_backups/"),
    RENAME_DROPLET("/droplets/%s/rename/"),
    DELETE_DROPLET("/droplets/%s/destroy/"),
    AVAILABLE_REGIONS("/regions/", "regions"),
    AVAILABLE_IMAGES("/images/", "images"),
    GET_IMAGE_INFO("/images/%s/", "image"),
    DELETE_IMAGE("/images/%s/destroy/"),
    TRANSFER_IMAGE("/images/%s/transfer/"),
    AVAILABLE_SSH_KEYS("/ssh_keys/", "ssh_keys"),
    CREATE_SSH_KEY("/ssh_keys/new/", "ssh_key"),
    GET_SSH_KEY("/ssh_keys/%s/", "ssh_key"),
    EDIT_SSH_KEY("/ssh_keys/%s/edit/", "ssh_key"),
    DELETE_SSH_KEY("/ssh_keys/%s/destroy/"),
    AVAILABLE_SIZES("/sizes/", "sizes"),
    AVAILABLE_DOMAINS("/domains/", "domains"),
    CREATE_DOMAIN("/domains/new", "domain"),
    GET_DOMAIN_INFO("/domains/%s/", "domain"),
    DELETE_DOMAIN("/domains/%s/destroy/"),
    GET_DOMAIN_RECORDS("/domains/%s/records/", "records"),
    CREATE_DOMAIN_RECORD("/domains/%s/records/new/", "record"),
    GET_DOMAIN_RECORD_INFO("/domains/%s/records/%s/", "record"),
    EDIT_DOMAIN_RECORD("/domains/%s/records/%s/edit/", "record"),
    DELETE_DOMAIN_RECORD("/domains/%s/records/%s/destroy/"),
    GET_EVENT_PROGRESS("/events/%s/", "event");    
   
	private String mapPath;
	
	private String elementName;
	
	Action(String mapPath) {
		this(mapPath, "");
	}

	Action(String mapPath, String elementName) {
		this.mapPath = mapPath;
		this.elementName = elementName;
	}

	/**
	 * @return the mapPath
	 */
	public String getMapPath() {
		return mapPath;
	}

	/**
	 * @return the elementName
	 */
	public String getElementName() {
		return elementName;
	}
	
}

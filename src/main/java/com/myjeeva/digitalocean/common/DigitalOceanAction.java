/**
 * 
 */
package com.myjeeva.digitalocean.common;


/**
 * Enumeration of DigitalOcean API supports
 * 
 * @author Jeevanandam (jeeva@myjeeva.com) 
 *
 */
public enum DigitalOceanAction {
    
    AVAILABLE_DROPLETS(""),
    CREATE_DROPLET("/new"),
    GET_DROPLET_INFO(""),
    REBOOT_DROPLET("/reboot"),
    POWER_CYCLE_DROPLET("/power_cycle"),
    SHUTDOWN_DROPLET("/shutdown"),
    POWER_OFF_DROPLET("/power_off"),
    POWER_ON_DROPLET("/power_on"), 
    RESET_PASSWORD_DROPLET("/password_reset"),
    RESIZE_DROPLET("/resize"),
    TAKE_DROPLET_SNAPSHOT("/snapshot"),
    RESTORE_DROPLET("/resize"),
    REBUILD_DROPLET("/rebuild"),
    ENABLE_AUTOMATIC_BACKUPS("/enable_backups"),
    DISABLE_AUTOMATIC_BACKUPS("/disable_backups"),
    RENAME_DROPLET("/rename"),
    DELETE_DROPLET("/destroy"),
    AVAILABLE_REGIONS("/regions"),
    ALL_IMAGES(""),
    GET_IMAGE_INFO(""),
    DELETE_IMAGE("/destroy"),
    TRANSFER_IMAGE("/transfer"),
    ALL_SSH_KEYS(""),
    CREATE_SSH_KEY("/new"),
    GET_SSH_KEY(""),
    EDIT_SSH_KEY("/edit"),
    DELETE_SSH_KEY("/destroy"),
    AVAILABLE_SIZES(""),
    AVAILABLE_DOMAINS(""),
    CREATE_DOMAIN("/new"),
    GET_DOMAIN(""),
    DELETE_DOMAIN("/destroy"),
    GET_DOMAIN_RECORDS("/records"),
    CREATE_DOMAIN_RECORD("/records/new"),
    GET_DOMAIN_RECORD_INFO("/records/{0}"),
    EDIT_DOMAIN_RECORD("/records/{0}/edit"),
    DELETE_DOMAIN_RECORD("/records/{0}/destroy");    
   
    private String mapPath;

    DigitalOceanAction(String mapPath) {
	this.mapPath = mapPath;
    }
    
    /**
     * @return the code
     */
    public String getCode() {
	return mapPath;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
	this.mapPath = code;
    }
}

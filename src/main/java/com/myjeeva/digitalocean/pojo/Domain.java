package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * 
 * @author Jeevanandam (jeeva@myjeeva.com)
 * 
 */

public class Domain {

    private Integer id;

    private String name;

    @SerializedName("ttl")
    private Integer timeToLive;

    @SerializedName("live_zone_file")
    private String liveZoneFile;

    private String error;

    @SerializedName("zone_file_with_error")
    private String zoneFileWithError;

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the timeToLive
     */
    public Integer getTimeToLive() {
	return timeToLive;
    }

    /**
     * @param timeToLive
     *            the timeToLive to set
     */
    public void setTimeToLive(Integer timeToLive) {
	this.timeToLive = timeToLive;
    }

    /**
     * @return the liveZoneFile
     */
    public String getLiveZoneFile() {
	return liveZoneFile;
    }

    /**
     * @param liveZoneFile
     *            the liveZoneFile to set
     */
    public void setLiveZoneFile(String liveZoneFile) {
	this.liveZoneFile = liveZoneFile;
    }

    /**
     * @return the error
     */
    public String getError() {
	return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError(String error) {
	this.error = error;
    }

    /**
     * @return the zoneFileWithError
     */
    public String getZoneFileWithError() {
	return zoneFileWithError;
    }

    /**
     * @param zoneFileWithError
     *            the zoneFileWithError to set
     */
    public void setZoneFileWithError(String zoneFileWithError) {
	this.zoneFileWithError = zoneFileWithError;
    }

}

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
package com.myjeeva.digitalocean.pojo;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * Represents Domain (TLD) attributes of DigitalOcean DNS
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Domain implements Serializable {

	private static final long serialVersionUID = -1034777642814045693L;

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

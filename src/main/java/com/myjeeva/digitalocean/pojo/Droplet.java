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

import java.util.List;

import com.google.gson.annotations.SerializedName;

/**
 * Represents Droplet attributes of DigitalOcean
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Droplet {

	private Integer id;

	private String name;

	@SerializedName("image_id")
	private Integer imageId;

	@SerializedName("region_id")
	private Integer regionId;

	@SerializedName("size_id")
	private Integer sizeId;

	@SerializedName("backups_active")
	private boolean backupsActive;

	private List<Backup> backups;

	private List<Snapshot> snapshots;

	@SerializedName("ip_address")
	private String ipAddress;

	@SerializedName("private_ip_address")
	private String privateIpAddress;

	private boolean locked;

	private String status;

	@SerializedName("created_at")
	private String createdDate;

	@SerializedName("event_id")
	private Long eventId;

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
	 * @return the imageId
	 */
	public Integer getImageId() {
		return imageId;
	}

	/**
	 * @param imageId
	 *            the imageId to set
	 */
	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the regionId
	 */
	public Integer getRegionId() {
		return regionId;
	}

	/**
	 * @param regionId
	 *            the regionId to set
	 */
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	/**
	 * @return the sizeId
	 */
	public Integer getSizeId() {
		return sizeId;
	}

	/**
	 * @param sizeId
	 *            the sizeId to set
	 */
	public void setSizeId(Integer sizeId) {
		this.sizeId = sizeId;
	}

	/**
	 * @return the backupsActive
	 */
	public boolean isBackupsActive() {
		return backupsActive;
	}

	/**
	 * @param backupsActive
	 *            the backupsActive to set
	 */
	public void setBackupsActive(boolean backupsActive) {
		this.backupsActive = backupsActive;
	}

	/**
	 * @return the backups
	 */
	public List<Backup> getBackups() {
		return backups;
	}

	/**
	 * @param backups
	 *            the backups to set
	 */
	public void setBackups(List<Backup> backups) {
		this.backups = backups;
	}

	/**
	 * @return the snapshots
	 */
	public List<Snapshot> getSnapshots() {
		return snapshots;
	}

	/**
	 * @param snapshots
	 *            the snapshots to set
	 */
	public void setSnapshots(List<Snapshot> snapshots) {
		this.snapshots = snapshots;
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress
	 *            the ipAddress to set
	 */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the privateIpAddress
	 */
	public String getPrivateIpAddress() {
		return privateIpAddress;
	}

	/**
	 * @param privateIpAddress
	 *            the privateIpAddress to set
	 */
	public void setPrivateIpAddress(String privateIpAddress) {
		this.privateIpAddress = privateIpAddress;
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 *            the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the eventId
	 */
	public Long getEventId() {
		return eventId;
	}

	/**
	 * @param eventId
	 *            the eventId to set
	 */
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

}

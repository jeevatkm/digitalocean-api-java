package com.myjeeva.digitalocean.pojo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myjeeva.digitalocean.common.ActionType;

public class VolumeAction {

	@Expose
	private ActionType type;
	
	@Expose
	@SerializedName("droplet_id")
	private Integer dropletId;
	
	@Expose
	@SerializedName("region")
	private String regionSlug;
	
	@Expose
	@SerializedName("volume_name")
	private String volumeName;
	
	@Expose
	@SerializedName("size_gigabytes")
	private Double sizeGigabytes;

	public VolumeAction(ActionType type, Integer dropletId, String regionSlug, String volumeName) {
		this.type = type;
		this.dropletId = dropletId;
		this.regionSlug = regionSlug;
		this.volumeName = volumeName;
	}
	
	public VolumeAction(ActionType type, Integer dropletId, String regionSlug) {
		this.type = type;
		this.dropletId = dropletId;
		this.regionSlug = regionSlug;
	}
	
	public VolumeAction(ActionType type, String regionSlug, Double sizeGigabytes) {
		this.type = type;
		this.regionSlug = regionSlug;
		this.sizeGigabytes = sizeGigabytes;
	}

	public ActionType getType() {
		return type;
	}

	public void setType(ActionType type) {
		this.type = type;
	}

	public Integer getDropletId() {
		return dropletId;
	}

	public void setDropletId(Integer dropletId) {
		this.dropletId = dropletId;
	}

	public String getRegionSlug() {
		return regionSlug;
	}

	public void setRegionSlug(String regionSlug) {
		this.regionSlug = regionSlug;
	}
	
	public String getVolumeName() {
		return volumeName;
	}

	public void setVolumeName(String volumeName) {
		this.volumeName = volumeName;
	}

	public Double getSizeGigabytes() {
		return sizeGigabytes;
	}

	public void setSizeGigabytes(Double sizeGigabytes) {
		this.sizeGigabytes = sizeGigabytes;
	}

	@Override
	public String toString() {
	    return ReflectionToStringBuilder.toString(this);
	}
}

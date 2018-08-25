package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.SerializedName;

public class PendingChanges {
	
	@SerializedName("droplet_id")
	private Integer dropletId;
	
	private Boolean removing;
	
	private String status;

	public Integer getDropletId() {
		return dropletId;
	}

	public void setDropletId(Integer dropletId) {
		this.dropletId = dropletId;
	}

	public Boolean getRemoving() {
		return removing;
	}

	public void setRemoving(Boolean removing) {
		this.removing = removing;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}

package com.myjeeva.digitalocean.pojo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Destinations {
	
	@Expose
	private List<String> addresses;
	
	@Expose
	@SerializedName("droplet_ids")
	private List<Integer> dropletIds;
	
	@Expose
	@SerializedName("load_balancer_uids")
	private List<String> loadBalancerUids;
	
	@Expose
	private List<String> tags;

	public List<String> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public List<Integer> getDropletIds() {
		return dropletIds;
	}

	public void setDropletIds(List<Integer> dropletIds) {
		this.dropletIds = dropletIds;
	}

	public List<String> getLoadBalancerUids() {
		return loadBalancerUids;
	}

	public void setLoadBalancerUids(List<String> loadBalancerUids) {
		this.loadBalancerUids = loadBalancerUids;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}

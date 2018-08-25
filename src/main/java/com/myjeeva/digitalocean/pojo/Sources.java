package com.myjeeva.digitalocean.pojo;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sources {
	
private List<String> addresses;

	@Expose
	@SerializedName("droplet_ids")
	private List<Integer> dropletIds;
	
	@Expose
	@SerializedName("load_balancer_uids")
	private List<String> loadBalancerUids;
	
	@Expose
	@SerializedName("tags")
	private List<String> tags;
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

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

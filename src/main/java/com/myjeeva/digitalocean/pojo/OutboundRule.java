package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OutboundRule {
	
	@Expose
	@SerializedName("protocol")
	private String protocol;
	
	@Expose
	@SerializedName("ports")
	private String ports;
	
	@Expose
	@SerializedName("destinations")
	private Destinations destinations;

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getPorts() {
		return ports;
	}

	public void setPorts(String ports) {
		this.ports = ports;
	}

	public Destinations getDestinations() {
		return destinations;
	}

	public void setDestinations(Destinations destinations) {
		this.destinations = destinations;
	}

}

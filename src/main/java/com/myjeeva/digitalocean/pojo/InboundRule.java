package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InboundRule {
	
	@Expose
	@SerializedName("protocol")
	private String protocol;
	
	@Expose
	@SerializedName("ports")
	private String ports;
	
	@Expose
	@SerializedName("sources")
	private Sources sources;

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

	public Sources getSources() {
		return sources;
	}

	public void setSources(Sources sources) {
		this.sources = sources;
	}
}

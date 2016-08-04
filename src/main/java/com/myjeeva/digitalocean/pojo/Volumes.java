package com.myjeeva.digitalocean.pojo;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class Volumes extends Base{

	private static final long serialVersionUID = 1739002259344347687L;
	
	private List<Volume> volumes;
	
	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}

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

import com.google.gson.annotations.SerializedName;

/**
 * Represents Event return object of DigitalOcean API request
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 * 
 * @since v1.3
 */
public class Event {

	private Long id;

	@SerializedName("action_status")
	private String actionStatus;

	@SerializedName("droplet_id")
	private Integer dropletId;

	@SerializedName("event_type_id")
	private Integer eventTypeId;

	private String percentage;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the actionStatus
	 */
	public String getActionStatus() {
		return actionStatus;
	}

	/**
	 * @param actionStatus
	 *            the actionStatus to set
	 */
	public void setActionStatus(String actionStatus) {
		this.actionStatus = actionStatus;
	}

	/**
	 * @return the dropletId
	 */
	public Integer getDropletId() {
		return dropletId;
	}

	/**
	 * @param dropletId
	 *            the dropletId to set
	 */
	public void setDropletId(Integer dropletId) {
		this.dropletId = dropletId;
	}

	/**
	 * @return the eventTypeId
	 */
	public Integer getEventTypeId() {
		return eventTypeId;
	}

	/**
	 * @param eventTypeId
	 *            the eventTypeId to set
	 */
	public void setEventTypeId(Integer eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	/**
	 * @return the percentage
	 */
	public String getPercentage() {
		return percentage;
	}

	/**
	 * @param percentage
	 *            the percentage to set
	 */
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

}

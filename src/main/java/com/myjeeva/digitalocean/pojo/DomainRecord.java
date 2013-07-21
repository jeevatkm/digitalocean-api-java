/* The MIT License
 *
 * Copyright (c) 2010-2013 Jeevanandam M. (myjeeva.com)
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
 * Represents DomainRecord (TLD) Record attributes of DigitalOcean DNS
 * 
 * @author Jeevanandam (jeeva@myjeeva.com)
 */
public class DomainRecord {

	private Integer id;

	@SerializedName("domain_id")
	private Integer domainId;

	@SerializedName("record_type")
	private String recordType;

	private String data;

	private String name;

	private String priority;

	private Integer port;

	private Integer weight;

	/**
	 * Default Constructor
	 */
	public DomainRecord() {
		// Default Constructor
	}

	/**
	 * Parameterized {@link DomainRecord} Constructor
	 * 
	 * @param id
	 *            Domain record Id
	 * @param domainId
	 *            specifies the domain Id for which to create a record
	 * @param recordType
	 *            the type of record you would like to create. 'A', 'CNAME',
	 *            'NS', 'TXT', 'MX' or 'SRV'
	 * @param data
	 *            this is the value of the record, for example: IP address, '@'
	 * @param name
	 *            Optional, required for 'A', 'CNAME', 'TXT' and 'SRV' records
	 * @param priority
	 *            Optional, required for 'SRV' and 'MX' records
	 * @param port
	 *            Optional, required for 'SRV' records
	 * @param weight
	 *            Optional, required for 'SRV' records
	 */
	public DomainRecord(Integer id, Integer domainId, String recordType,
			String data, String name, String priority, Integer port,
			Integer weight) {
		this.id = id;
		this.domainId = domainId;
		this.recordType = recordType;
		this.name = name;
		this.data = data;
		this.priority = priority;
		this.port = port;
		this.weight = weight;
	}

	/**
	 * @return the idDomain record Id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to setDomain Record Id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the domainIdspecifies the domain Id for which to create a record
	 */
	public Integer getDomainId() {
		return domainId;
	}

	/**
	 * @param domainId
	 *            the domainId to set the domain Id for which to create a record
	 */
	public void setDomainId(Integer domainId) {
		this.domainId = domainId;
	}

	/**
	 * @return the recordTypethe type of record you would like to create. 'A',
	 *         'CNAME', 'NS', 'TXT', 'MX' or 'SRV'
	 */
	public String getRecordType() {
		return recordType;
	}

	/**
	 * @param recordType
	 *            the recordType to set 'A', 'CNAME', 'NS', 'TXT', 'MX' or 'SRV'
	 */
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	/**
	 * @return the data value ofIP address, '@'
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to setthis is the value of the record, for example:
	 *            IP address, '@'
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @return the namevalue of 'A', 'CNAME', 'TXT' and 'SRV' records
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to setOptional, required for 'A', 'CNAME', 'TXT' and
	 *            'SRV' records
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the priority SRV' and 'MX' values
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set Optional, required for 'SRV' and 'MX'
	 *            records
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/**
	 * @return the port 'SRV' records
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to setOptional, required for 'SRV' records
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the weight value of 'SRV' records
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to setOptional, required for 'SRV' records
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}

}

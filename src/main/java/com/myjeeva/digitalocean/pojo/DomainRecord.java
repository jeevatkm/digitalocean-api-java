package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * 
 * @author Jeevanandam (jeeva@myjeeva.com)
 * 
 */

public class DomainRecord {

    private Integer id;

    @SerializedName("domain_id")
    private String domainId;

    @SerializedName("record_type")
    private String recordType;

    private String name;

    private String data;

    private String priority;

    private Integer port;

    private Integer weight;

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
     * @return the domainId
     */
    public String getDomainId() {
	return domainId;
    }

    /**
     * @param domainId
     *            the domainId to set
     */
    public void setDomainId(String domainId) {
	this.domainId = domainId;
    }

    /**
     * @return the recordType
     */
    public String getRecordType() {
	return recordType;
    }

    /**
     * @param recordType
     *            the recordType to set
     */
    public void setRecordType(String recordType) {
	this.recordType = recordType;
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
     * @return the data
     */
    public String getData() {
	return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(String data) {
	this.data = data;
    }

    /**
     * @return the priority
     */
    public String getPriority() {
	return priority;
    }

    /**
     * @param priority
     *            the priority to set
     */
    public void setPriority(String priority) {
	this.priority = priority;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
	return port;
    }

    /**
     * @param port
     *            the port to set
     */
    public void setPort(Integer port) {
	this.port = port;
    }

    /**
     * @return the weight
     */
    public Integer getWeight() {
	return weight;
    }

    /**
     * @param weight
     *            the weight to set
     */
    public void setWeight(Integer weight) {
	this.weight = weight;
    }

}

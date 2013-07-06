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
 * Represents Droplet attributes of DigitalOcean
 * 
 * @author Jeevanandam M. (jeeva@myjeeva.com)
 */
public class Droplet {

    private Integer id;

    private String name;

    @SerializedName("image_id")
    private Integer imageId;

    @SerializedName("region_id")
    private Integer regionId;

    @SerializedName("size_id")
    private Integer sizeId;

    @SerializedName("backups_active")
    private String backupsActive;

    private String status;

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
     * @return the imageId
     */
    public Integer getImageId() {
	return imageId;
    }

    /**
     * @param imageId
     *            the imageId to set
     */
    public void setImageId(Integer imageId) {
	this.imageId = imageId;
    }

    /**
     * @return the regionId
     */
    public Integer getRegionId() {
	return regionId;
    }

    /**
     * @param regionId
     *            the regionId to set
     */
    public void setRegionId(Integer regionId) {
	this.regionId = regionId;
    }

    /**
     * @return the sizeId
     */
    public Integer getSizeId() {
	return sizeId;
    }

    /**
     * @param sizeId
     *            the sizeId to set
     */
    public void setSizeId(Integer sizeId) {
	this.sizeId = sizeId;
    }

    /**
     * @return the backupsActive
     */
    public String getBackupsActive() {
	return backupsActive;
    }

    /**
     * @param backupsActive
     *            the backupsActive to set
     */
    public void setBackupsActive(String backupsActive) {
	this.backupsActive = backupsActive;
    }

    /**
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
	this.status = status;
    }
}

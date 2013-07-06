package com.myjeeva.digitalocean.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * 
 * 
 * @author Jeevanandam (jeeva@myjeeva.com) 
 *
 */

public class SshKey {

    private Integer id;

    private String name;

    @SerializedName("ssh_pub_key")
    private String sshPublicKey;

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
     * @return the sshPublicKey
     */
    public String getSshPublicKey() {
	return sshPublicKey;
    }

    /**
     * @param sshPublicKey
     *            the sshPublicKey to set
     */
    public void setSshPublicKey(String sshPublicKey) {
	this.sshPublicKey = sshPublicKey;
    }

}

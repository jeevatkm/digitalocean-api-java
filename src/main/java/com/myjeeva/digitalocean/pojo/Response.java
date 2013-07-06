package com.myjeeva.digitalocean.pojo;

/**
 * 
 * 
 * @author Jeevanandam (jeeva@myjeeva.com) 
 *
 */

public class Response {

    private String status;

    private String jsonData;
    
    private Integer eventId;

    public Response(String status, Integer eventId, String jsonData) {
	this.status = status;
	this.jsonData = jsonData;
	this.eventId = eventId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the jsonData
     */
    public String getJsonData() {
        return jsonData;
    }

    /**
     * @param jsonData the jsonData to set
     */
    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }

    /**
     * @return the eventId
     */
    public Integer getEventId() {
        return eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }
    
}

package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class CapabilityRequest {
    private String capabilityName;
    private String leadName;
    private String capabilityLeadPicture;
    private String message;
    @JsonCreator
    public CapabilityRequest(
            @JsonProperty("capabilityName") String capabilityName,
            @JsonProperty("leadName") String leadName,
            @JsonProperty("capabilityLeadPicture") String capabilityLeadPicture,
            @JsonProperty("message") String message
    ) {
        this.capabilityName = capabilityName;
        this.leadName = leadName;
        this.capabilityLeadPicture = capabilityLeadPicture;
        this.message = message;
    }
    public String getCapabilityName() {
        return capabilityName;
    }
    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }
    public String getLeadName() {
        return leadName;
    }
    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }
    public String getCapabilityLeadPicture() {
        return capabilityLeadPicture;
    }
    public void setCapabilityLeadPicture(String capabilityLeadPicture) {
        this.capabilityLeadPicture = capabilityLeadPicture;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
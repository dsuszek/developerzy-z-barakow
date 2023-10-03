package org.kainos.ea.model;

public class Capability {
    private short id;
    private String capabilityName;
    private String leadName;
    private String capabilityLeadPicture;
    private String message;

    public Capability(short id, String capabilityName, String leadName, String capabilityLeadPicture, String message) {
        this.id = id;
        this.capabilityName = capabilityName;
        this.leadName = leadName;
        this.capabilityLeadPicture = capabilityLeadPicture;
        this.message = message;
    }

    public Capability(String capabilityName, String leadName, String capabilityLeadPicture, String message) {
        this.capabilityName = capabilityName;
        this.leadName = leadName;
        this.capabilityLeadPicture = capabilityLeadPicture;
        this.message = message;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
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

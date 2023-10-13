package org.kainos.ea.model;

public class JobRoleDetails {
    private short id;
    private String name;
    private String description;
    private String link;
    private String bandName;
    private short bandLevel;
    private String capabilityName;

    public JobRoleDetails(short id, String name, String description, String link, String bandName, short bandLevel, String capabilityName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.bandName = bandName;
        this.bandLevel = bandLevel;
        this.capabilityName = capabilityName;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public short getBandLevel() {
        return bandLevel;
    }

    public void setBandLevel(short bandLevel) {
        this.bandLevel = bandLevel;
    }

    public String getCapabilityName() {
        return capabilityName;
    }

    public void setCapabilityName(String capabilityName) {
        this.capabilityName = capabilityName;
    }
}

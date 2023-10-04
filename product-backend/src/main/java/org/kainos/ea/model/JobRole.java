package org.kainos.ea.model;

public class JobRole {
    private short id;
    private String name;
    private String description;
    private String link;
    private short bandId;

    public JobRole(short id, String name, String description, String link, short bandId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
        this.bandId = bandId;
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

    public short getBandId() {
        return bandId;
    }

    public void setBandId(short bandId) {
        this.bandId = bandId;
    }
}

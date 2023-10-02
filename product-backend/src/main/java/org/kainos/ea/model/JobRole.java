package org.kainos.ea.model;

public class JobRole {
    private short id;
    private String name;
    private String description;
    private String link;

    public JobRole(short id, String name, String description, String link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
    }
    public JobRole(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
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
}
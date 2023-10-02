package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleResponse {

    private short id;
    private String name;
    private String description;
    private String link;

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

    @JsonCreator
    public JobRoleResponse(
            @JsonProperty("id") short id,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("link") String link) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.link = link;
    }
}
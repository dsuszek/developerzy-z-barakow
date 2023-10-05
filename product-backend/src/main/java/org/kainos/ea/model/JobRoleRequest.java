package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRoleRequest {

    private String name;
    private String description;
    private String link;
    private short bandId;

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

    @JsonCreator
    public JobRoleRequest(
            @JsonProperty("name") String name,
            @JsonProperty("description") String description,
            @JsonProperty("link") String link,
            @JsonProperty("bandId") short bandId) {
        this.name = name;
        this.description = description;
        this.link = link;
        this.bandId = bandId;
    }
}

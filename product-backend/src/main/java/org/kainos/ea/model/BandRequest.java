package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BandRequest {
    private String name;
    private short level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }

    @JsonCreator
    public BandRequest(
            @JsonProperty("name") String name,
            @JsonProperty("level") String description) {
        this.name = name;
        this.level = level;
    }
}

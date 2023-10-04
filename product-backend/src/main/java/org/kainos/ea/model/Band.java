package org.kainos.ea.model;

public class Band {
    private short id;
    private String name;
    private short level;
    public Band(short id, String name, short level) {
        this.id = id;
        this.name = name;
        this.level = level;
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

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }
}

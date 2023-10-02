package org.kainos.ea.model;

public class User {
    private short id;
    private String email;
    private String password;
    private short roleId;

    public User(short id, String email, String password, short roleId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public short getRoleId() {
        return roleId;
    }

    public void setRoleId(short roleId) {
        this.roleId = roleId;
    }
}
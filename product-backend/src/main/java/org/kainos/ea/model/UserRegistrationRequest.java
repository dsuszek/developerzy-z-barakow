package org.kainos.ea.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistrationRequest {

    private String email;
    private String password;
    private short roleId;

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

    @JsonCreator
    public UserRegistrationRequest(
            @JsonProperty("email") String email,
            @JsonProperty("password") String password,
            @JsonProperty("roleId") short roleId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }
}

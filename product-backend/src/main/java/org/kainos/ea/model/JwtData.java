package org.kainos.ea.model;

public class JwtData {
    private String email;
    private int roleId;

    public JwtData(String email, int roleId) {
        this.email = email;
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}

package com.example.provider.users;

import java.util.List;

public class User {
    private String name;
    private List<String> permissions;

    @SuppressWarnings("unused")
    public User() {
    }

    public User(String name, List<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    @SuppressWarnings("unused")
    public List<String> getPermissions() {
        return permissions;
    }

    @SuppressWarnings("unused")
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}

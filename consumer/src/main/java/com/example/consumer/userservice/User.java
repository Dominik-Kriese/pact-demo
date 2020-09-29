package com.example.consumer.userservice;

import java.util.List;

public class User {
    private String name;
    private List<String> permissions;

    public User() {
    }

    public User(String name, List<String> permissions) {
        this.name = name;
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    @SuppressWarnings("unused")
    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
}

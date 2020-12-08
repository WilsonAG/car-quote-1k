package com.will.carquote1k.models;

import androidx.annotation.NonNull;

public class User {

    private String name;
    private String id;
    private String code;
    private String username;
    private String password;


    public User(String name, String id, String code, String username, String password) {
        this.name = name;
        this.id = id;
        this.code = code;
        this.username = username;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "name='" + getName() + '\'' +
                ", id='" + getId() + '\'' +
                ", code='" + getCode() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}

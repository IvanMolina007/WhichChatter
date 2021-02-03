package com.example.whichchatter;

import java.util.List;

public class User {

    public String idUID, name, mobile, description;
    List<User> userList;


    public User() {
    }

    public User(String idUID, String name, String mobile, String description){
        this.idUID = idUID;
        this.name = name;
        this.mobile = mobile;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getidUID() {
        return idUID;
    }

}


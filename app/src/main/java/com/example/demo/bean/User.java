package com.example.demo.bean;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {

    private String name;
    private String email;
    private String sex;
    private String userID;





    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User()
    {
        name="";
        email="";
        sex="";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)


    public User(String name, String email) {
        this.name = name;
        this.email = email;

    }
}
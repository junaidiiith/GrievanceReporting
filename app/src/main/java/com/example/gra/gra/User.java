package com.example.gra.gra;

/**
 * Created by junaid on 30/3/18.
 */

import com.google.firebase.database.IgnoreExtraProperties;
@IgnoreExtraProperties
public class User {
    private String name;
    private String email;
    private String phone;
    private String type;
    private String adhar;

    public User(){
        //this constructor is required
    }

    public User(String name, String email, String phone, String type, String adhar) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.adhar = adhar;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getType() {
        return type;
    }

    public String getAdhar() {
        return adhar;
    }
}

package com.example.gra.gra;

import android.app.Application;

/**
 * Created by sachin on 26/3/17.
 */

public class MyGlobalVariable {

    private static String myAadharNumber = "";
    private static String userType = "";
    private static String email = "";
    private static String mobileNumber = "";
    private static String password = "";

    public static String getMyAadharNumber() {
        return myAadharNumber;
    }

    public static void setMyAadharNumber(String myAadharNumber) {
        MyGlobalVariable.myAadharNumber = myAadharNumber;
    }

    public static String getUserType() {
        return userType;
    }

    public static void setUserType(String userType) {
        MyGlobalVariable.userType = userType;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        MyGlobalVariable.email = email;
    }

    public static String getMobileNumber() {
        return mobileNumber;
    }

    public static void setMobileNumber(String mobileNumber) {
        MyGlobalVariable.mobileNumber = mobileNumber;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        MyGlobalVariable.password = password;
    }
}

package com.example.gra.gra;

/**
 * Created by RAVI on 4/11/2017.
 */

public class Grievance {

    private static String gid;
    private static String aadharNumber;
    private static String category;
    private static String locality;
    private static String city;
    private static String state;
    private static String pincode;
    private static String description;
    private static String status;

    public static String getGid() {
        return gid;
    }

    public static void setGid(String gid) {
        Grievance.gid = gid;
    }

    public static String getAadharNumber() {
        return aadharNumber;
    }

    public static void setAadharNumber(String aadharNumber) {
        Grievance.aadharNumber = aadharNumber;
    }

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        Grievance.category = category;
    }

    public static String getLocality() {
        return locality;
    }

    public static void setLocality(String locality) {
        Grievance.locality = locality;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Grievance.city = city;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
        Grievance.state = state;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Grievance.description = description;
    }

    public static String getPincode() {
        return pincode;
    }

    public static void setPincode(String pincode) {
        Grievance.pincode = pincode;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        Grievance.status = status;
    }
}

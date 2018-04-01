package com.example.gra.gra;

/**
 * Created by RAVI on 4/11/2017.
 */

public class Contacts {
    private static String fire = "";
    private static String ambulance = "";
    private static String police = "";
    private static String water = "";
    private static String electricity = "";
    private static String pincode = "";
    private static int foundFlag = 0;

    public static int getFoundFlag() {
        return foundFlag;
    }

    public static void setFoundFlag(int foundFlag) {
        Contacts.foundFlag = foundFlag;
    }

    public static String getPincode() {
        return pincode;
    }

    public static void setPincode(String pincode) {
        Contacts.pincode = pincode;
    }

    public static String getPolice() {
        return police;
    }

    public static void setPolice(String police) {
        Contacts.police = police;
    }

    public static String getFire() {
        return fire;
    }

    public static void setFire(String fire) {
        Contacts.fire = fire;
    }

    public static String getAmbulance() {
        return ambulance;
    }

    public static void setAmbulance(String ambulance) {
        Contacts.ambulance = ambulance;
    }

    public static String getWater() {
        return water;
    }

    public static void setWater(String water) {
        Contacts.water = water;
    }

    public static String getElectricity() {
        return electricity;
    }

    public static void setElectricity(String electricity) {
        Contacts.electricity = electricity;
    }

    public static void flush(){
        Contacts.fire = "";
        Contacts.ambulance = "";
        Contacts.water = "";
        Contacts.police = "";
        Contacts.electricity = "";
        foundFlag = 0;
    }
}

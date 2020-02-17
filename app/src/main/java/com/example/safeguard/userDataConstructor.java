package com.example.safeguard;

import com.google.android.gms.maps.model.LatLng;

public class userDataConstructor {
    private String userName;
    private String phoneNumber;
    private String email;
    private String location;
    private LatLng latLang;

    public userDataConstructor() {

    }

    public userDataConstructor(String userName, String phoneNumber, String email, String location, LatLng latLang) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.location = location;
        this.latLang = latLang;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LatLng getLatLang() {
        return latLang;
    }

    public void setLatLang(LatLng latLang) {
        this.latLang = latLang;
    }
}

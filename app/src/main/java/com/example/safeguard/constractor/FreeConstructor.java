package com.example.safeguard.constractor;

public class FreeConstructor {

    private String UserName;
    private String UserPhoneName;
    private String location;
    private String message;
    private double latitude;
    private double longitude;

    public FreeConstructor() {
    }

    public FreeConstructor(String userName, String userPhoneName, String location, String message, double latitude, double longitude) {
        UserName = userName;
        UserPhoneName = userPhoneName;
        this.location = location;
        this.message = message;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPhoneName() {
        return UserPhoneName;
    }

    public void setUserPhoneName(String userPhoneName) {
        UserPhoneName = userPhoneName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

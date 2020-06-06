package com.example.safeguard.constractor;

public class FreeConstructor {

    private String UserName;
    private String UserPhoneName;
    private String Location;
    private String Message;
    private String RequestType;
    private double latitude;
    private double longitude;

    public FreeConstructor() {
    }

    public FreeConstructor(String userName, String userPhoneName, String location, String message, String requestType, double latitude, double longitude) {
        UserName = userName;
        UserPhoneName = userPhoneName;
        Location = location;
        Message = message;
        RequestType = requestType;
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
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestType() {
        return RequestType;
    }

    public void setRequestType(String requestType) {
        RequestType = requestType;
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

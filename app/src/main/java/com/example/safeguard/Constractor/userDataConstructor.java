package com.example.safeguard.Constractor;

public class userDataConstructor {
    private String UserName;
    private String PhoneNumber;
    private String Email;
    private String UserLocation;
    private double Latitude;
    private double Longitude;

    public userDataConstructor() {

    }

    public userDataConstructor(String userName, String phoneNumber, String email, String userLocation, double latitude, double longitude) {
        UserName = userName;
        PhoneNumber = phoneNumber;
        Email = email;
        UserLocation = userLocation;
        Latitude = latitude;
        Longitude = longitude;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserLocation() {
        return UserLocation;
    }

    public void setUserLocation(String userLocation) {
        UserLocation = userLocation;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}

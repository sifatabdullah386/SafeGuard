package com.example.safeguard.Constractor;

public class FreeConstructor {

    private String location;
    private String message;
    private double latitude;
    private double longitude;

    public FreeConstructor() {
    }

    public FreeConstructor(String location, String message, double latitude, double longitude) {
        this.location = location;
        this.message = message;
        this.latitude = latitude;
        this.longitude = longitude;
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

package com.example.safeguard.Constractor;

public class addFireStationsConstructor {

    private String FireStationName;
    private String FireStationPhoneNumber;
    private String FireStationEmail;
    private String FireStationLocation;
    private String FireStationDescription;

    public addFireStationsConstructor() {
    }

    public addFireStationsConstructor(String fireStationName, String fireStationPhoneNumber, String fireStationEmail, String fireStationLocation, String fireStationDescription) {
        FireStationName = fireStationName;
        FireStationPhoneNumber = fireStationPhoneNumber;
        FireStationEmail = fireStationEmail;
        FireStationLocation = fireStationLocation;
        FireStationDescription = fireStationDescription;
    }

    public String getFireStationName() {
        return FireStationName;
    }

    public void setFireStationName(String fireStationName) {
        FireStationName = fireStationName;
    }

    public String getFireStationPhoneNumber() {
        return FireStationPhoneNumber;
    }

    public void setFireStationPhoneNumber(String fireStationPhoneNumber) {
        FireStationPhoneNumber = fireStationPhoneNumber;
    }

    public String getFireStationEmail() {
        return FireStationEmail;
    }

    public void setFireStationEmail(String fireStationEmail) {
        FireStationEmail = fireStationEmail;
    }

    public String getFireStationLocation() {
        return FireStationLocation;
    }

    public void setFireStationLocation(String fireStationLocation) {
        FireStationLocation = fireStationLocation;
    }

    public String getFireStationDescription() {
        return FireStationDescription;
    }

    public void setFireStationDescription(String fireStationDescription) {
        FireStationDescription = fireStationDescription;
    }
}

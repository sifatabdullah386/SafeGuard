package com.example.safeguard;

public class addFireStationConstructor {

    private String ID;
    private String FireStationName;
    private String FireStationPhoneNumber;
    private String FireStationEmail;
    private String FireStationDescription;

    addFireStationConstructor(String id, String fireStationNameData , String fireStationPhoneNumberData, String fireStationEmailData, String fireStationDescriptionData) {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
    public String getFireStationDescription() {
        return FireStationDescription;
    }

    public void setFireStationDescription(String fireStationDescription) {
        FireStationDescription = fireStationDescription;
    }
}

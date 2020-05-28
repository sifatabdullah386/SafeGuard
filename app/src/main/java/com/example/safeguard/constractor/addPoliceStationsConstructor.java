package com.example.safeguard.constractor;

public class addPoliceStationsConstructor {

    private String PoliceStationsName;
    private String PoliceStationsPhoneNumber;
    private String PoliceStationsEmail;
    private String PoliceStationsLocation;
    private String PoliceStationsDescription;

    public addPoliceStationsConstructor() {
    }

    public addPoliceStationsConstructor(String policeStationsName, String policeStationsPhoneNumber, String policeStationsEmail, String policeStationsLocation,  String policeStationsDescription) {
        PoliceStationsName = policeStationsName;
        PoliceStationsPhoneNumber = policeStationsPhoneNumber;
        PoliceStationsEmail = policeStationsEmail;
        PoliceStationsLocation = policeStationsLocation;
        PoliceStationsDescription = policeStationsDescription;
    }

    public String getPoliceStationsName() {
        return PoliceStationsName;
    }

    public void setPoliceStationsName(String policeStationsName) {
        PoliceStationsName = policeStationsName;
    }

    public String getPoliceStationsPhoneNumber() {
        return PoliceStationsPhoneNumber;
    }

    public void setPoliceStationsPhoneNumber(String policeStationsPhoneNumber) {
        PoliceStationsPhoneNumber = policeStationsPhoneNumber;
    }

    public String getPoliceStationsEmail() {
        return PoliceStationsEmail;
    }

    public void setPoliceStationsEmail(String policeStationsEmail) {
        PoliceStationsEmail = policeStationsEmail;
    }

    public String getPoliceStationsLocation() {
        return PoliceStationsLocation;
    }

    public void setPoliceStationsLocation(String policeStationsLocation) {
        PoliceStationsLocation = policeStationsLocation;
    }

    public String getPoliceStationsDescription() {
        return PoliceStationsDescription;
    }

    public void setPoliceStationsDescription(String policeStationsDescription) {
        PoliceStationsDescription = policeStationsDescription;
    }
}

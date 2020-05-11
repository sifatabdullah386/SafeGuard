package com.example.safeguard.Constractor;

public class addAmbulancesConstructor {

    private String AmbulanceName;
    private String AmbulancePhoneNumber;
    private String AmbulanceEmail;
    private String AmbulanceLocation;
    private String AmbulanceType;
    private String AmbulanceDescription;

    public addAmbulancesConstructor() {

    }

    public addAmbulancesConstructor(String ambulanceName, String ambulancePhoneNumber, String ambulanceEmail, String ambulanceLocation, String ambulanceType, String ambulanceDescription) {
        AmbulanceName = ambulanceName;
        AmbulancePhoneNumber = ambulancePhoneNumber;
        AmbulanceEmail = ambulanceEmail;
        AmbulanceLocation = ambulanceLocation;
        AmbulanceType = ambulanceType;
        AmbulanceDescription = ambulanceDescription;
    }
    public String getAmbulanceName() {
        return AmbulanceName;
    }

    public void setAmbulanceName(String ambulanceName) {
        AmbulanceName = ambulanceName;
    }

    public String getAmbulancePhoneNumber() {
        return AmbulancePhoneNumber;
    }

    public void setAmbulancePhoneNumber(String ambulancePhoneNumber) {
        AmbulancePhoneNumber = ambulancePhoneNumber;
    }

    public String getAmbulanceEmail() {
        return AmbulanceEmail;
    }

    public void setAmbulanceEmail(String ambulanceEmail) {
        AmbulanceEmail = ambulanceEmail;
    }

    public String getAmbulanceLocation() {
        return AmbulanceLocation;
    }

    public void setAmbulanceLocation(String ambulanceLocation) {
        AmbulanceLocation = ambulanceLocation;
    }

    public String getAmbulanceType() {
        return AmbulanceType;
    }

    public void setAmbulanceType(String ambulanceType) {
        AmbulanceType = ambulanceType;
    }

    public String getAmbulanceDescription() {
        return AmbulanceDescription;
    }

    public void setAmbulanceDescription(String ambulanceDescription) {
        AmbulanceDescription = ambulanceDescription;
    }
}

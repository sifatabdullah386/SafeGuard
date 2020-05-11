package com.example.safeguard.Constractor;

public class addHospitalsConstructor {

    private String HospitalName;
    private String HospitalPhoneNumber;
    private String HospitalEmail;
    private String HospitalLocation;
    private String HospitalType;
    private String HospitalDescription;

    public addHospitalsConstructor() {
    }

    public addHospitalsConstructor(String hospitalName, String hospitalPhoneNumber, String hospitalEmail, String hospitalLocation, String hospitalType, String hospitalDescription) {
        HospitalName = hospitalName;
        HospitalPhoneNumber = hospitalPhoneNumber;
        HospitalEmail = hospitalEmail;
        HospitalLocation = hospitalLocation;
        HospitalType = hospitalType;
        HospitalDescription = hospitalDescription;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getHospitalPhoneNumber() {
        return HospitalPhoneNumber;
    }

    public void setHospitalPhoneNumber(String hospitalPhoneNumber) {
        HospitalPhoneNumber = hospitalPhoneNumber;
    }

    public String getHospitalEmail() {
        return HospitalEmail;
    }

    public void setHospitalEmail(String hospitalEmail) {
        HospitalEmail = hospitalEmail;
    }

    public String getHospitalLocation() {
        return HospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        HospitalLocation = hospitalLocation;
    }

    public String getHospitalType() {
        return HospitalType;
    }

    public void setHospitalType(String hospitalType) {
        HospitalType = hospitalType;
    }

    public String getHospitalDescription() {
        return HospitalDescription;
    }

    public void setHospitalDescription(String hospitalDescription) {
        HospitalDescription = hospitalDescription;
    }
}

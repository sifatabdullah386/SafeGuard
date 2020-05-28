package com.example.safeguard.constractor;

public class addVolunteersConstructor {

    private String ID;
    private String VolunteerName;
    private String VolunteerLocation;
    private String VolunteerPhoneNumber;
    private String VolunteerEmail;
    private String VolunteerDescription;

    public addVolunteersConstructor() {
    }

    public addVolunteersConstructor(String ID, String volunteerName, String volunteerLocation, String volunteerPhoneNumber, String volunteerEmail, String volunteerDescription) {
        this.ID = ID;
        VolunteerName = volunteerName;
        VolunteerLocation = volunteerLocation;
        VolunteerPhoneNumber = volunteerPhoneNumber;
        VolunteerEmail = volunteerEmail;
        VolunteerDescription = volunteerDescription;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVolunteerName() {
        return VolunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        VolunteerName = volunteerName;
    }

    public String getVolunteerLocation() {
        return VolunteerLocation;
    }

    public void setVolunteerLocation(String volunteerLocation) {
        VolunteerLocation = volunteerLocation;
    }

    public String getVolunteerPhoneNumber() {
        return VolunteerPhoneNumber;
    }

    public void setVolunteerPhoneNumber(String volunteerPhoneNumber) {
        VolunteerPhoneNumber = volunteerPhoneNumber;
    }

    public String getVolunteerEmail() {
        return VolunteerEmail;
    }

    public void setVolunteerEmail(String volunteerEmail) {
        VolunteerEmail = volunteerEmail;
    }
    public String getVolunteerDescription() {
        return VolunteerDescription;
    }

    public void setVolunteerDescription(String volunteerDescription) {
        VolunteerDescription = volunteerDescription;
    }
}

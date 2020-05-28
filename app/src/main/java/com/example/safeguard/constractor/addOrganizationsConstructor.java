package com.example.safeguard.constractor;

public class addOrganizationsConstructor {
    private String ID;
    private String OrganizationName;
    private String OrganizationLocation;
    private String OrganizationPhoneNumber;
    private String OrganizationEmail;
    private String OrganizationType;
    private String OrganizationDescription;

    public addOrganizationsConstructor() {
    }

    public addOrganizationsConstructor(String ID, String organizationName, String organizationLocation, String organizationPhoneNumber, String organizationEmail, String organizationType, String organizationDescription) {
        this.ID = ID;
        OrganizationName = organizationName;
        OrganizationLocation = organizationLocation;
        OrganizationPhoneNumber = organizationPhoneNumber;
        OrganizationEmail = organizationEmail;
        OrganizationType = organizationType;
        OrganizationDescription = organizationDescription;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOrganizationName() {
        return OrganizationName;
    }

    public void setOrganizationName(String organizationName) {
        OrganizationName = organizationName;
    }

    public String getOrganizationLocation() {
        return OrganizationLocation;
    }

    public void setOrganizationLocation(String organizationLocation) {
        OrganizationLocation = organizationLocation;
    }

    public String getOrganizationPhoneNumber() {
        return OrganizationPhoneNumber;
    }

    public void setOrganizationPhoneNumber(String organizationPhoneNumber) {
        OrganizationPhoneNumber = organizationPhoneNumber;
    }

    public String getOrganizationEmail() {
        return OrganizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        OrganizationEmail = organizationEmail;
    }

    public String getOrganizationType() {
        return OrganizationType;
    }

    public void setOrganizationType(String organizationType) {
        OrganizationType = organizationType;
    }

    public String getOrganizationDescription() {
        return OrganizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        OrganizationDescription = organizationDescription;
    }
}


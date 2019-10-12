package com.example.safeguard;

public class userDataConstructor {
    private String ID;
    private String userName;
    private String phoneNumber;
    private String location;
    private String email;

    public userDataConstructor() {

    }

    userDataConstructor(String ID, String userName, String phoneNumber, String location, String email) {
        this.ID = ID;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.email = email;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

package com.example.safeguard.constractor;

public class RequestConstructor {
    private String UserRequesterName;
    private String UserRequesterPhoneNumber;
    private String UserRequesterMessages;
    private String UserRequesterLocation;

    public RequestConstructor() {
    }

    public RequestConstructor(String userRequesterName, String userRequesterPhoneNumber, String userRequesterMessages, String userRequesterLocation) {
        UserRequesterName = userRequesterName;
        UserRequesterPhoneNumber = userRequesterPhoneNumber;
        UserRequesterMessages = userRequesterMessages;
        UserRequesterLocation = userRequesterLocation;
    }

    public String getUserRequesterName() {
        return UserRequesterName;
    }

    public void setUserRequesterName(String userRequesterName) {
        UserRequesterName = userRequesterName;
    }

    public String getUserRequesterPhoneNumber() {
        return UserRequesterPhoneNumber;
    }

    public void setUserRequesterPhoneNumber(String userRequesterPhoneNumber) {
        UserRequesterPhoneNumber = userRequesterPhoneNumber;
    }

    public String getUserRequesterMessages() {
        return UserRequesterMessages;
    }

    public void setUserRequesterMessages(String userRequesterMessages) {
        UserRequesterMessages = userRequesterMessages;
    }

    public String getUserRequesterLocation() {
        return UserRequesterLocation;
    }

    public void setUserRequesterLocation(String userRequesterLocation) {
        UserRequesterLocation = userRequesterLocation;
    }
}

package com.example.safeguard.Constractor;

public class addEmergencyContactsConstructor {

    private String Name1;
    private String PhoneNumber1;
    private String Name2;
    private String PhoneNumber2;
    private String Name3;
    private String PhoneNumber3;

    public addEmergencyContactsConstructor() {
    }

    public addEmergencyContactsConstructor(String name1, String phoneNumber1, String name2, String phoneNumber2, String name3, String phoneNumber3) {
        Name1 = name1;
        PhoneNumber1 = phoneNumber1;
        Name2 = name2;
        PhoneNumber2 = phoneNumber2;
        Name3 = name3;
        PhoneNumber3 = phoneNumber3;
    }

    public String getName1() {
        return Name1;
    }

    public void setName1(String name1) {
        Name1 = name1;
    }

    public String getPhoneNumber1() {
        return PhoneNumber1;
    }

    public void setPhoneNumber1(String phoneNumber1) {
        PhoneNumber1 = phoneNumber1;
    }

    public String getName2() {
        return Name2;
    }

    public void setName2(String name2) {
        Name2 = name2;
    }

    public String getPhoneNumber2() {
        return PhoneNumber2;
    }

    public void setPhoneNumber2(String phoneNumber2) {
        PhoneNumber2 = phoneNumber2;
    }

    public String getName3() {
        return Name3;
    }

    public void setName3(String name3) {
        Name3 = name3;
    }

    public String getPhoneNumber3() {
        return PhoneNumber3;
    }

    public void setPhoneNumber3(String phoneNumber3) {
        PhoneNumber3 = phoneNumber3;
    }
}

package com.example.foodwastagemanagmentsystem;

public class UserInformation {
    private String name;
    private String phone;
    private String userType;
    private  String country;

    public UserInformation() {
    }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}

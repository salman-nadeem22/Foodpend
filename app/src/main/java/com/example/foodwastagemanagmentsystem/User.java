package com.example.foodwastagemanagmentsystem;

public class User {
    private String phone;
    private String userType;
    private String name;
    private  String country;

    public User(String phone, String userType, String name, String country) {
        this.phone = phone;
        this.userType = userType;
        this.name = name;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }
}

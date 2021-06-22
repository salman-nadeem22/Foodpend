package com.example.foodwastagemanagmentsystem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class RecycleViewItemModel {
    private String eventName, eventType, uId, userEmail, eventAddress, eventDate, eventLat, eventLon, eventPeoples, eventTime, eventFoodMade, eventDressCode;
    private ArrayList<String> foods;

    public RecycleViewItemModel() {
    }

    public String getEventName() {
        return eventName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLat() {
        return eventLat;
    }

    public void setEventLat(String eventLat) {
        this.eventLat = eventLat;
    }

    public String getEventLon() {
        return eventLon;
    }

    public void setEventLon(String eventLon) {
        this.eventLon = eventLon;
    }

    public String getEventPeoples() {
        return eventPeoples;
    }

    public void setEventPeoples(String eventPeoples) {
        this.eventPeoples = eventPeoples;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventFoodMade() {
        return eventFoodMade;
    }

    public void setEventFoodMade(String eventFoodMade) {
        this.eventFoodMade = eventFoodMade;
    }

    public String getEventDressCode() {
        return eventDressCode;
    }

    public void setEventDressCode(String eventDressCode) {
        this.eventDressCode = eventDressCode;
    }

    public ArrayList<String> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<String> foods) {
        this.foods = foods;
    }

    public RecycleViewItemModel(String eventName, String eventType, String uId, String userEmail, String eventAddress,
                                String eventDate, String eventLat, String eventLon, String eventPeoples, String eventTime,
                                String eventFoodMade, String eventDressCode, ArrayList<String> foods) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.uId = uId;
        this.userEmail = userEmail;
        this.eventAddress = eventAddress;
        this.eventDate = eventDate;
        this.eventLat = eventLat;
        this.eventLon = eventLon;
        this.eventPeoples = eventPeoples;
        this.eventTime = eventTime;
        this.eventFoodMade = eventFoodMade;
        this.eventDressCode = eventDressCode;
        this.foods = foods;
    }
}

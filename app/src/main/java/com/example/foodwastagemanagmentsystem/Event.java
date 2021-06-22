package com.example.foodwastagemanagmentsystem;

import java.util.ArrayList;

public class Event {
    String uId, eventName, eventType, eventAddress, eventPeoples, eventLat, eventLon, eventDate,
            eventTime, eventFoodMade, eventDressCode;
    ArrayList<String> foods;

    public Event(String uId, String eventName, String eventType, String eventAddress,
                 String eventPeoples, String eventLat, String eventLon, String eventDate,
                 String eventTime, String eventFoodMade, String eventDressCode, ArrayList<String> foods) {

        this.uId = uId;
        this.eventName = eventName;
        this.eventType = eventType;
        this.eventAddress = eventAddress;
        this.eventPeoples = eventPeoples;
        this.eventLat = eventLat;
        this.eventLon = eventLon;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventFoodMade = eventFoodMade;
        this.eventDressCode = eventDressCode;
        this.foods = foods;
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

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAddress) {
        this.eventAddress = eventAddress;
    }

    public String getEventPeoples() {
        return eventPeoples;
    }

    public void setEventPeoples(String eventPeoples) {
        this.eventPeoples = eventPeoples;
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

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getEventName() {
        return eventName;
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
}

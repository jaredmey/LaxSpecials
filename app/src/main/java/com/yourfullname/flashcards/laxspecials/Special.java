package com.yourfullname.flashcards.laxspecials;

/**
 * Created by Jimmy on 4/6/2016.
 */
public class Special {

    private long id;
    private long barId;


    //Variables for the Specials table
    private String barName;
    private String day = "";
    private String time = "";
    private String description = "";
    private String address = "";

    //Setters and Getters for the private instance variables
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public String getBarName() {
        return barName;
    }
    public void setBarName(String barName) {
        this.barName = barName;
    }

    public long getBarId() {
        return barId;
    }
    public void setBarId(long barId) {
        this.barId = barId;
    }

    public String getDay() {
        return day;
    }
    public void setDay(String day) {
        this.day = day;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return barName + "\n" + description + "\n" + address;
    }



}

package com.yourfullname.flashcards.laxspecials;

public class User {
    //Instance variables for the Users table
    private long id;
    private String name = "";

    //Setters and Getters for the private instance variables

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

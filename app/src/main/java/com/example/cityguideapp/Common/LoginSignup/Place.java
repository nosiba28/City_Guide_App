package com.example.cityguideapp.Common.LoginSignup;



public class Place {
    public String name;
    public String category;
    public String phoneNumber;
    public String email;
    public String description;
    public String mapsLink;

    // Required default constructor for Firebase
    public Place() {}

    public Place(String name, String category, String phoneNumber, String email, String description, String mapsLink) {
        this.name = name;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.description = description;
        this.mapsLink = mapsLink;
    }
}


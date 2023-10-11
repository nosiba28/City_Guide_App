package com.example.cityguideapp.Common.LoginSignup;
/*
public class Place11 {
    private String name;
    private String category;
    private String phoneNumber;
    private String email;
    private String mapsLink;

    public Place11(String name, String category, String phoneNumber, String email, String mapsLink) {
        this.name = name;
        this.category = category;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.mapsLink = mapsLink;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getMapsLink() {
        return mapsLink;
    }
}*/

public class Place11 {
    private String Name;
    private String Category;
    private String PhoneNumber;
    private String Email;
    private String MapsLink;

    public Place11() {
        // Empty constructor required for Firestore
    }

    public Place11(String Name, String Category, String PhoneNumber, String Email, String MapsLink) {
        this.Name = Name;
        this.Category = Category;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.MapsLink = MapsLink;
    }

    public String getName() {
        return Name;
    }

    public String getCategory() {
        return Category;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getMapsLink() {
        return MapsLink;
    }
}


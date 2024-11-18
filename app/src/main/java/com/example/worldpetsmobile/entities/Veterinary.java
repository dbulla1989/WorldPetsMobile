package com.example.worldpetsmobile.entities;

import com.google.android.gms.maps.model.LatLng;

public class Veterinary {

    private String name;
    private String address;
    private String phone;
    private float rating;
    private LatLng location;

    public Veterinary(String name, String address, String phone, float rating, LatLng location) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
        this.location = location;
    }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getPhone() { return phone; }

    public float getRating() { return rating; }

    public LatLng getLocation() { return location; }
}


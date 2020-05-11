package com.example.safeguard.Constractor;

import com.google.android.gms.maps.model.LatLng;

public class LocationConstructor {
    private  String location;
    private LatLng locationLatLang;

    public LocationConstructor() {
    }

    public LocationConstructor(String location, LatLng locationLatLang) {
        this.location = location;
        this.locationLatLang = locationLatLang;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LatLng getLocationLatLang() {
        return locationLatLang;
    }

    public void setLocationLatLang(LatLng locationLatLang) {
        this.locationLatLang = locationLatLang;
    }
}

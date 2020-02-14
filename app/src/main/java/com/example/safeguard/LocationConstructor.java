package com.example.safeguard;

import com.google.android.gms.maps.model.LatLng;

public class LocationConstructor {
    private  String location;
    private LatLng latLang;

    public LocationConstructor() {
    }

    LocationConstructor(String location, LatLng latLang) {
        this.location = location;
        this.latLang = latLang;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LatLng getLatLang() {
        return latLang;
    }

    public void setLatLang(LatLng latLang) {
        this.latLang = latLang;
    }
}

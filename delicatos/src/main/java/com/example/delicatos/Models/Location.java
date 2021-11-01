package com.example.delicatos.Models;

public class Location {
    private Long longitude;
    private Long latitude;

    public Location(Long longitude, Long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public Long getLatitude() {
        return latitude;
    }
}

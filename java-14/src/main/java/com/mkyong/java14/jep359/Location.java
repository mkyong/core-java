package com.mkyong.java14.jep359;

public record Location(double latitude, double longitude) {

    // override record default constructor
    public Location {
        this.latitude = latitude * 3;
        this.longitude = longitude * 3;
    }

    // override record toString
    @Override
    public String toString() {
        return "GPS Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

}

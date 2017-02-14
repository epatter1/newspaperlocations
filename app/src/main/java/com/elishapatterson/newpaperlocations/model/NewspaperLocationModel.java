package com.elishapatterson.newpaperlocations.model;

/**
 * Single location from our list of locations
 */
public class NewspaperLocationModel {

    private double latitude = 0.0;
    private double longitude = 0.0;

    private String style = ""; // paperbox or starbucks

    private String details = ""; // This will hold the address/details string


    public NewspaperLocationModel(String style, double latitude, double longitude, String details) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.style = style;
        this.details = details;

    }

    public String getStyle() { return this.style; }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public String getDetails() {
        return this.details;
    }

}

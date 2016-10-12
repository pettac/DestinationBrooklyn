package com.csthack.beinnovative.destination_brooklyn;

import android.graphics.drawable.Drawable;

/**
 * Created by nolan on 4/9/16.
 */
public class pointInterestClass {

    private String name, imageURL, description, website,timePeriod, buildingType;
    private double latitude, longitude;
    Drawable image;

    pointInterestClass(String name, String imageURL, String description, String website, String timePeriod, String buildingType, double latitude, double longitude) {
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.website = website;
        this.timePeriod = timePeriod;
        this.buildingType = buildingType;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private void setName(String name){
        this.name = name;
    }

    protected String getName(){
        return this.name;
    }

    private void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    protected String getImageURL(){
        return this.imageURL;
    }

    private void setDescription(String description){
        this.description = description;
    }

    protected String getDescription(){
        return this.description;
    }

    private void setWebsite(String website){
        this.website = website;
    }

    protected String getWebsite(){
        return this.website;
    }

    private void setLatitude(double latitude){
        this.latitude = latitude;
    }

    protected double getLatitude(){
        return this.latitude;
    }

    private void getLongitude(double longitude){
        this.longitude = longitude;
    }

    protected double getLongitude(){
        return this.longitude;
    }

    private void setTimePeriod(String timePeriod){
        this.timePeriod = timePeriod;
    }

    protected String getTimePeriod(){
        return this.timePeriod;
    }

    private void setBuildingType(String buildingType){
        this.buildingType = buildingType;
    }

    protected String getBuildingType(){
        return this.buildingType;
    }

    protected int whatBuildingType(String buildingTypeValue){
        int retValue;
        switch(buildingTypeValue.toLowerCase()){
            case "streetart":
                retValue = 1;
            case "architecture":
                retValue = 2;
            case "museum":
                retValue = 3;
            default:
                retValue = 0;
        }
        return retValue;
    }
}

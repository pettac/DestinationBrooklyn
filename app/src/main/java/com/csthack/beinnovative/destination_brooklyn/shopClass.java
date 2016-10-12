package com.csthack.beinnovative.destination_brooklyn;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;

/**
 * Created by nolan on 4/9/16.
 */

public  class shopClass{
    private String name, imageURL, description, website;
    private double latitude, longitude;
    public Drawable image;

    shopClass(String name, String imageURL, String description, String website, double latitude, double longitude) {
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.website = website;
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

    protected void setImage (Drawable image){
        this.image = image;
    }
}

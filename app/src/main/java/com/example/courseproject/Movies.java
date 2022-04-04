package com.example.courseproject;

import android.media.Image;

public class Movies {
    public Movies(String title, String related_Date, String rate, String imageURL, String overview) {
        Title = title;
        Related_Date = related_Date;
        Rate = rate;
        this.imageURL = imageURL;
        Overview = overview;
    }

    private String Title;
    private String Related_Date;
    private String Rate;
    private String imageURL;
    private String Overview;



    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getRelated_Date() {
        return Related_Date;
    }

    public void setRelated_Date(String related_Date) {
        Related_Date = related_Date;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getImage() {
        return imageURL;
    }

    public void setImage(String image) {
        this.imageURL = image;
    }

    public String getOverview() {
        return Overview;
    }

    public void setOverview(String overview) {
        Overview = overview;
    }
}





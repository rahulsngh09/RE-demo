package com.mysite.core.bean;

public class RidingPositionImagesEntity {

    private String imageURL;

    public RidingPositionImagesEntity() {

    }

    public RidingPositionImagesEntity(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}

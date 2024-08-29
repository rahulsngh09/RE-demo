package com.mysite.core.bean;

public class RidingPositionImagesEntity {

    private String image_url;

    public RidingPositionImagesEntity() {

    }

    public RidingPositionImagesEntity(String imageURL) {
        this.image_url = imageURL;
    }

    public String getImageURL() {
        return image_url;
    }

    public void setImageURL(String imageURL) {
        this.image_url = imageURL;
    }
}

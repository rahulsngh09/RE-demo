package com.mysite.core.bean;

public class ImageEntity {
    private String text;
    private String backgroundImage;


    public ImageEntity(String text, String backgroundImage){
        this.text = text;
        this.backgroundImage = backgroundImage;


    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}

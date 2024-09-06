package com.mysite.core.bean;

import java.util.List;

public class BikeDetails {
    private String bikeName;
    private String bikePrice;
    private String bikeHeroImage;
    private List<String> bikeFeatures;
    private List<String> bikeSmallImages;
    private String forwardIcon;


    public String getForwardIcon() {
        return forwardIcon;
    }

    public void setForwardIcon(String forwardIcon) {
        this.forwardIcon = forwardIcon;
    }

    public BikeDetails() {

    }

    public BikeDetails(String bikeName, String bikePrice, String bikeHeroImage, List<String> bikeFeatures, List<String> bikeSmallImages, String forwardIcon) {
        this.bikeName = bikeName;
        this.bikePrice = bikePrice;
        this.bikeHeroImage = bikeHeroImage;
        this.bikeFeatures = bikeFeatures;
        this.bikeSmallImages = bikeSmallImages;
        this.forwardIcon = forwardIcon;
    }

    public String getBikeName() {
        return bikeName;
    }

    public void setBikeName(String bikeName) {
        this.bikeName = bikeName;
    }

    public String getBikePrice() {
        return bikePrice;
    }

    public void setBikePrice(String bikePrice) {
        this.bikePrice = bikePrice;
    }

    public String getBikeHeroImage() {
        return bikeHeroImage;
    }

    public void setBikeHeroImage(String bikeHeroImage) {
        this.bikeHeroImage = bikeHeroImage;
    }

    public List<String> getBikeFeatures() {
        return bikeFeatures;
    }

    public void setBikeFeatures(List<String> bikeFeatures) {
        this.bikeFeatures = bikeFeatures;
    }

    public List<String> getBikeSmallImages() {
        return bikeSmallImages;
    }

    public void setBikeSmallImages(List<String> bikeSmallImages) {
        this.bikeSmallImages = bikeSmallImages;
    }
}

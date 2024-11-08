package com.invento.invento.dto;

public class ItemCardData {

    private String title;
    private String description;
    private String imageUrl;

    public ItemCardData(String imageUrl, String description, String title) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

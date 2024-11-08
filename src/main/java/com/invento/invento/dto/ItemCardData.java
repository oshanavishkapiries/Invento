package com.invento.invento.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCardData {

    private String title;
    private String description;
    private String imageUrl;

    public ItemCardData(String imageUrl, String description, String title) {
        this.imageUrl = imageUrl;
        this.description = description;
        this.title = title;
    }
}
package com.example.mynotes;

public class CardData {

    private String title;
    private String description;
    private int picture;

    public CardData(String title, String description, int picture) {
        this.title = title;
        this.description = description;
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPicture() {
        return picture;
    }
}

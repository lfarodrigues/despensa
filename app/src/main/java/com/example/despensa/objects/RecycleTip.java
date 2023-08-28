package com.example.despensa.objects;

public class RecycleTip {
    private String title;
    private String description;

    public RecycleTip(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
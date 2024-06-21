package com.example.practica4v2.model;

public class ClassModel {

    private String instructorName;
    private String location;
    private String schedule;
    private String level;
    private String description;
    private double price;
    private float rating;
    private int thumbnail;

    public ClassModel(String instructorName, String location, String schedule, String level, String description, double price, float rating, int thumbnail) {
        this.instructorName = instructorName;
        this.location = location;
        this.schedule = schedule;
        this.level = level;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.thumbnail = thumbnail;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getLocation() {
        return location;
    }

    public String getSchedule() {
        return schedule;
    }

    public String getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public float getRating() {
        return rating;
    }

    public int getThumbnail() {
        return thumbnail;
    }
}

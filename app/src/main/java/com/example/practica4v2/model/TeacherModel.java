package com.example.practica4v2.model;

public class TeacherModel {

    private String id;
    private String name;
    private String email;
    private String location;
    private String url;

    private Long phone;

    public String getDescription() {
        return description;
    }

    private String description;

    public String getId() {
        return id;
    }

    private Long level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getLevel() {
        return level;
    }

    public String getUrl() {
        return url;
    }

    public void setLevel(Long level) {
        this.level = level;
    }


    public TeacherModel(){

    }

    public Long getPhone() {
        return phone;
    }

    public TeacherModel(String id, String name, String email, String location, String url, Long level, String description, Long phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.location = location;
        this.level = level;
        this.url = url;
        this.description = description;
        this.phone = phone;
    }
}

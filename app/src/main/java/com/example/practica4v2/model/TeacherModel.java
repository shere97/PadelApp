package com.example.practica4v2.model;

public class TeacherModel {

    private String name, email, location;
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

    public void setLevel(Long level) {
        this.level = level;
    }

    public TeacherModel(){

    }

    public TeacherModel(String name, String email, String location, Long level) {
        this.name = name;
        this.email = email;
        this.location = location;
        this.level = level;
    }
}

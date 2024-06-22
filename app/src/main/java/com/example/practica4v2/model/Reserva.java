package com.example.practica4v2.model;

public class Reserva {

    private String idReserva;
    private String teacher_name;

    private String location;

    private String user_name;

    private String horaSeleccionada;

    public String getIdReserva() {
        return idReserva;
    }

    public String getLocation() {
        return location;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getHoraSeleccionada() {
        return horaSeleccionada;
    }

    public Reserva(){

    }

    public Reserva(String idReserva, String teacher_name, String user_name, String horaSeleccionada, String location) {
        this.idReserva = idReserva;
        this.teacher_name = teacher_name;
        this.user_name = user_name;
        this.location = location;
        this.horaSeleccionada = horaSeleccionada;
    }
}

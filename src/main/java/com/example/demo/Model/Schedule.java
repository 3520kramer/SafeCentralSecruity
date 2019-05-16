package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Schedule {
    @Id
    private int id;
    private String starttid;
    private String sluttid;
    private String timetal;
    private String dato;

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStarttid() {
        return starttid;
    }

    public void setStarttid(String starttid) {
        this.starttid = starttid;
    }

    public String getSluttid() {
        return sluttid;
    }

    public void setSluttid(String sluttid) {
        this.sluttid = sluttid;
    }

    public String getTimetal() {
        return timetal;
    }

    public void setTimetal(String timetal) {
        this.timetal = timetal;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}
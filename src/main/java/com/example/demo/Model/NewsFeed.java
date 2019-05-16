package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NewsFeed {
    @Id
    private int id;
    private String opslag;
    private String dato;
    private String tid;

    public NewsFeed() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpslag() {
        return opslag;
    }

    public void setOpslag(String opslag) {
        this.opslag = opslag;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}

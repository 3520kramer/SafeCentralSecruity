package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NewsFeed {
    @Id
    private int newsfeed_id;
    private String opslag;
    private String dato;
    private String tid;

    public NewsFeed() {
    }

    public int getId() {
        return newsfeed_id;
    }

    public void setId(int newsfeed_id) {
        this.newsfeed_id = newsfeed_id;
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

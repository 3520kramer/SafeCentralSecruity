package com.example.demo.Model;

/*
Importere entity og id sådan så vi kan forbinde til vores database
Entity bruges i stedet for @Table prøver på at forbinde til mens
id er vores id i de forskellige tabeller

Klasse indeholder desuden en masse fields samt getter og setter
 */

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NewsFeed {
    // Ansvarlige: Oliver
    @Id
    private int newsfeed_id;
    private String opslag;
    private String dato;
    private String tid;

    public NewsFeed() {
    }

    public int getNewsfeed_id() {
        return newsfeed_id;
    }

    public void setNewsfeed_id(int newsfeed_id) {
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

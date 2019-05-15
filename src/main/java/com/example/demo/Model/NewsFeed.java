package com.example.demo.Model;

public class NewsFeed {
    private String id;
    private String opslag;
    private String dato;
    private String tid;

    public NewsFeed() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

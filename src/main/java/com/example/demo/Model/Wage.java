package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
Importere entity og id sådan så vi kan forbinde til vores database
Entity bruges i stedet for @Table prøver på at forbinde til mens
id er vores id i de forskellige tabeller

Klasse indeholder desuden en masse fields samt getter og setter
 */

@Entity
public class Wage {
    // Ansvarlige: Oliver
    @Id
    private int medarbejder_id;
    private String fornavn;
    private String efternavn;
    private String navn;
    private String cpr;
    private double lon;
    private double timetal;
    private double totallon;

    public Wage() {
    }

    public int getMedarbejder_id() {
        return medarbejder_id;
    }

    public void setMedarbejder_id(int medarbejder_id) {
        this.medarbejder_id = medarbejder_id;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEfternavn() {
        return efternavn;
    }

    public void setEfternavn(String efternavn) {
        this.efternavn = efternavn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getTimetal() {
        return timetal;
    }

    public void setTimetal(double timetal) {
        this.timetal = timetal;
    }

    public double getTotallon() {
        return totallon;
    }

    public void setTotallon(double totallon) {
        this.totallon = totallon;
    }
}

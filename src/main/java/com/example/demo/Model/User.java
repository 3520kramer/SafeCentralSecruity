package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class User {
    @Id
    private int medarbejder_id;
    private String fornavn;
    private String efternavn;
    private String ansettelsesdato;
    private String telefon;
    private String email;
    private String cpr;
    private double lon;
    private String adresse;
    private int postnummer;

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

    public String getAnsettelsesdato() {
        return ansettelsesdato;
    }

    public void setAnsettelsesdato(String ansettelsesdato) {
        this.ansettelsesdato = ansettelsesdato;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }
}


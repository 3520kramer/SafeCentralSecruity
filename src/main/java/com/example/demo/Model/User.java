package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class User {
    @Id
    private int bruger_id;
    private String fornavn;
    private String efternavn;
    private String ansettelsesdato;
    private String telefon;
    private String email;
    private String cpr;
    private double lon;
    private String addresse;
    private int postnummer;

    public int getId() {
        return bruger_id;
    }

    public void setId(int bruger_id) {
        this.bruger_id = bruger_id;
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

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }
}


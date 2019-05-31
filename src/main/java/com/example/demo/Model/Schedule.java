package com.example.demo.Model;

/*
Importere entity og id sådan så vi kan forbinde til vores database
Entity bruges i stedet for @Table prøver på at forbinde til mens
id er vores id i de forskellige tabeller

Klasse indeholder desuden en masse fields samt getter og setter
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Schedule {
    @Id
    private int vagtplan_id;
    private String fornavn;
    private String efternavn;
    private String navn;
    private String starttid;
    private String sluttid;
    private double timetal;
    private String dato;
    private String firma_navn;
    private String adresse;
    private String bydel;
    private int postnummer;
    private int medarbejder_id;
    private int kunde_id;

    public Schedule() {
    }

    public int getVagtplan_id() {
        return vagtplan_id;
    }

    public void setVagtplan_id(int vagtplan_id) {
        this.vagtplan_id = vagtplan_id;
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

    public double getTimetal() {
        return timetal;
    }

    public void setTimetal(double timetal) {
        this.timetal = timetal;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getFirma_navn() {
        return firma_navn;
    }

    public void setFirma_navn(String firma_navn) {
        this.firma_navn = firma_navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getBydel() {
        return bydel;
    }

    public void setBydel(String bydel) {
        this.bydel = bydel;
    }

    public int getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(int postnummer) {
        this.postnummer = postnummer;
    }

    public int getMedarbejder_id() {
        return medarbejder_id;
    }

    public void setMedarbejder_id(int medarbejder_id) {
        this.medarbejder_id = medarbejder_id;
    }

    public int getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }
}
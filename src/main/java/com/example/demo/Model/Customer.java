package com.example.demo.Model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {


    @Id
    private int kunde_id;
    private String firma_navn;
    private String kontaktperson;
    private String telefon;
    private String email;
    private int CVR;
    private String adresse;
    private int postnummer;

    public int getKunde_id() {
        return kunde_id;
    }

    public void setKunde_id(int kunde_id) {
        this.kunde_id = kunde_id;
    }


    public String getFirma_navn() {
        return firma_navn;
    }

    public void setFirma_navn(String firma_navn) {
        this.firma_navn = firma_navn;
    }

    public String getKontaktperson() {
        return kontaktperson;
    }

    public void setKontaktperson(String kontaktperson) {
        this.kontaktperson = kontaktperson;
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

    public int getCVR() {
        return CVR;
    }

    public void setCVR(int CVR) {
        this.CVR = CVR;
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

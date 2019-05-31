package com.example.demo.Model;

/*
Importere entity og id sådan så vi kan forbinde til vores database
Entity bruges i stedet for @Table prøver på at forbinde til mens
id er vores id i de forskellige tabeller

Klasse indeholder desuden en masse fields samt getter og setter
 */

public class Owner extends User {

    @Override
    public String getAnsettelsesdato() {
        String ansettelsesdato = "2018-01-01";
        return ansettelsesdato;
    }
}

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
    public class Login {
        // Ansvarlige: Nadia og Kasper
        @Id
        private int bruger_id;
        private String username;
        private String password;
        private String status;

        public int getBruger_id() {
            return bruger_id;
        }

        public void setBruger_id(int bruger_id) {
            this.bruger_id = bruger_id;
        }

        public Login(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {

            return status;

    }

    public void setStatus(String status) {
        this.status = status;
    }
}

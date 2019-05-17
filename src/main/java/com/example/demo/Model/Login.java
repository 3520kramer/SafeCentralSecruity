package com.example.demo.Model;

import javax.persistence.Entity;
import javax.persistence.Id;

    @Entity
    public class Login {

        @Id
        private int bruger_id;
        private String username;
        private String password;
        private String status;

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

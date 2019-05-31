package com.example.demo.Repository;

//Vores interface, som vi bruger mest af alt for at have implementeret et krav om polymorphism, hvor vi desværre kun har delete med
//Grundet interfaces natur, er det redundant at have den abstract, men det startede med at være en abstract superklasse

public abstract interface RepoInterface {
    public Boolean delete(int id);
}

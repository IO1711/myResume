package com.bilolbek.myResume.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hobbies {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String hobbyName;


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHobbyName() {
        return this.hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", hobbyName='" + getHobbyName() + "'" +
            "}";
    }

}

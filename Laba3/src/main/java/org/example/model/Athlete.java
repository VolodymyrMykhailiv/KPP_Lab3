package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class Athlete implements Serializable {
    private String name;
    private String surname;
    private int age;
    @JsonIgnore
    private transient int medalCount;
    private String sport;
    private Origin origin;

    public Athlete() {
    }

    public Athlete(String name, String surname, int age, int medalCount, String sport, Origin origin) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.medalCount = medalCount;
        this.sport = sport;
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getMedalCount() {
        return medalCount;
    }

    public String getSport() {
        return sport;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMedalCount(int medalCount) {
        this.medalCount = medalCount;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Athlete{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", medalCount=" + medalCount +
                ", sport='" + sport + '\'' +
                ", origin=" + origin +
                '}';
    }


    public String toStringRepresentation() {
        return name + "," + surname + "," + age + "," + sport + "," + origin.toStringRepresentation();
    }

    public static Athlete createFromString(String csv) {
        String[] parts = csv.split(",");
        Origin origin = Origin.createFromString(parts[4], parts[5]);
        return new Athlete(parts[0], parts[1], Integer.parseInt(parts[2]),0, parts[3], origin);
    }
}

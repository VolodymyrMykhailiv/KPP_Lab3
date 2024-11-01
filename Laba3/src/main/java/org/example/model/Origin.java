package org.example.model;

import java.io.Serializable;

public class Origin implements Serializable {
    private String country;
    private String city;

    public Origin() {

    }
    public Origin(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Origin{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String toStringRepresentation() {
        return country + "," + city;
    }

    public static Origin createFromString(String country, String city) {
        return new Origin(country, city);
    }

}

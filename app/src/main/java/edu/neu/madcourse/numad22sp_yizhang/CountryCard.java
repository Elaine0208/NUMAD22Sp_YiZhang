package edu.neu.madcourse.numad22sp_yizhang;

public class CountryCard {
    private final String name;
    private final String country;
    private final String prob;

    //Constructor
    public CountryCard(String name, String country, String prob) {
        this.name = name;
        this.country = country;
        this.prob = prob;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getProb() {return prob;}
}
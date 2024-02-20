package me.model;

import java.util.ArrayList;

public class Attraction {
    private String name;
    private int ageClassification;
    private int minimumHeight;
    private ArrayList<Integer> validPassports;
    private int visitCount;
    private boolean available;
    public Attraction() {
    }

    public Attraction(String name, int ageClassification, int minimumHeight, ArrayList<Integer> validPassports, int visitCount, boolean available) {
        this.name = name;
        this.ageClassification = ageClassification;
        this.minimumHeight = minimumHeight;
        this.validPassports = validPassports;
        this.visitCount = visitCount;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAgeClassification() {
        return ageClassification;
    }

    public void setAgeClassification(int ageClassification) {
        this.ageClassification = ageClassification;
    }

    public int getMinimumHeight() {
        return minimumHeight;
    }

    public void setMinimumHeight(int minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    public ArrayList<Integer> getValidPassports() {
        return validPassports;
    }

    public void setValidPassports(ArrayList<Integer> validPassports) {
        this.validPassports = validPassports;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

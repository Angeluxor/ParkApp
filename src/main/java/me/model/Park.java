package me.model;

public class Park {

    private double maxCapacity;
    private double visitors;


    public Park() {
    }

    public Park(double maxCapacity, double visitors) {
        this.maxCapacity = maxCapacity;
        this.visitors = visitors;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(double maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public double getVisitors() {
        return visitors;
    }

    public void setVisitors(double visitors) {
        this.visitors = visitors;
    }

    public double calculateOccupancy() {
        return (visitors * 100) / maxCapacity;
    }


}

package me.model;

import me.model.clients.Client;
import me.model.employees.Employee;

import java.util.ArrayList;

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

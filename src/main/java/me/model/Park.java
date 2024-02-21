package me.model;

import me.model.clients.Client;
import me.model.employees.Employee;

import java.util.ArrayList;

public class Park {

    private ArrayList<Attraction> attractionList;
    private ArrayList<Client> clientsList;
    private ArrayList<Employee> employeesList;
    private ArrayList<TicketStation> ticketStationsList;
    private double occupancy;

    public Park() {
    }

    public Park(ArrayList<Attraction> attractionList, ArrayList<Client> clientsList, ArrayList<Employee> employeesList, ArrayList<TicketStation> ticketStationsList, double occupancy) {
        this.attractionList = attractionList;
        this.clientsList = clientsList;
        this.employeesList = employeesList;
        this.ticketStationsList = ticketStationsList;
        this.occupancy = occupancy;
    }

    public ArrayList<Attraction> getAttractionList() {
        return attractionList;
    }

    public void setAttractionList(ArrayList<Attraction> attractionList) {
        this.attractionList = attractionList;
    }

    public ArrayList<Client> getClientsList() {
        return clientsList;
    }

    public void setClientsList(ArrayList<Client> clientsList) {
        this.clientsList = clientsList;
    }

    public ArrayList<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(ArrayList<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public double getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(double occupancy) {
        this.occupancy = occupancy;
    }

    public ArrayList<TicketStation> getTicketStationsList() {
        return ticketStationsList;
    }

    public void setTicketStationsList(ArrayList<TicketStation> ticketStationsList) {
        this.ticketStationsList = ticketStationsList;
    }

    @Override
    public String toString() {
        return "Park{" +
                "attractionList=" + attractionList +
                ", clientsList=" + clientsList +
                ", employeesList=" + employeesList +
                ", ticketStationsList=" + ticketStationsList +
                ", occupancy=" + occupancy +
                '}';
    }
}

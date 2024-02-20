package me.model.employees;

import me.model.Attraction;
import me.model.TicketStation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdministrativeEmployee extends Employee{

    public AdministrativeEmployee(String name, String email, int id, int phoneNumber, int workScheduleType) {
        super(name, email, id, phoneNumber, workScheduleType);
    }

    public AdministrativeEmployee() {
    }

    public void setActiveTicketStations(ArrayList<TicketStation> ticketStations){
        if(LocalDate.now().getDayOfWeek().equals(DayOfWeek.MONDAY) || LocalDate.now().getDayOfWeek().equals(DayOfWeek.TUESDAY)){
            for (int i = 0; i < 2; i++){
                TicketStation randomTicketStation = ticketStations.get((int) (Math.random() * 5));
                while (!randomTicketStation.isActive()){
                    randomTicketStation = ticketStations.get((int) (Math.random() * 5));
                }
                randomTicketStation.setActive(false);

            }
        }
    }

    public void setActiveTicketStations(ArrayList<TicketStation> ticketStations, double occupancy){
        if(occupancy>60.00){
            for (TicketStation ticketStation : ticketStations) {
                ticketStation.setActive(true);
            }
        }

    }

    public void setAttractionCondition(Attraction attraction, int newAge, int newHeight, ArrayList<Integer> newPassports){
        attraction.setAgeClassification(newAge);
        attraction.setMinimumHeight(newHeight);
        attraction.setValidPassports(newPassports);
    }

}

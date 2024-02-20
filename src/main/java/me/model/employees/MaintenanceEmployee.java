package me.model.employees;

import me.model.Attraction;

public class MaintenanceEmployee extends Employee{

    public MaintenanceEmployee() {
    }

    public MaintenanceEmployee(String name, String email, int id, int phoneNumber, int workScheduleType) {
        super(name, email, id, phoneNumber, workScheduleType);
    }

    public void setAttractionAvailability(Attraction attraction){
        attraction.setAvailable(!attraction.isAvailable());
    }
}

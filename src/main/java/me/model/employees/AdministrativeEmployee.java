package me.model.employees;

import me.model.Attraction;
import me.model.TicketStation;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdministrativeEmployee extends Employee{

    public AdministrativeEmployee(String name, String email, int id, long phoneNumber, int workScheduleType) {
        super(name, email, id, phoneNumber, workScheduleType);
    }

    public AdministrativeEmployee() {
    }


}

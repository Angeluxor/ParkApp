package me.model.employees;

import me.model.clients.Client;

public class AdvertisingEmployee extends Employee{

    public AdvertisingEmployee() {
    }
    public AdvertisingEmployee(String name, String email, int id, long phoneNumber, int workScheduleType) {
        super(name, email, id, phoneNumber, workScheduleType);
    }

}

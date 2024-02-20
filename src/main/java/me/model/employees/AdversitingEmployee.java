package me.model.employees;

import me.model.clients.Client;

public class AdversitingEmployee extends Employee{

    public AdversitingEmployee() {
    }
    public AdversitingEmployee(String name, String email, int id, int phoneNumber, int workScheduleType) {
        super(name, email, id, phoneNumber, workScheduleType);
    }
    public void setDiscount(Client client){
        if((client.getVisitCount()%3)==0){
            client.setDiscountPercentage(25);
        }
    }
}

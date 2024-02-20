package me.model.employees;

import me.model.Attraction;
import me.model.clients.Client;

public class OperationalEmployee extends Employee {

    private Attraction currentAttraction;

    public OperationalEmployee() {
    }

    public OperationalEmployee(String name, String email, int id, int phoneNumber, int workScheduleType, Attraction currentAttraction) {
        super(name, email, id, phoneNumber, workScheduleType);
        this.currentAttraction = currentAttraction;
    }

    public Attraction getCurrentAttraction() {
        return currentAttraction;
    }

    public void setCurrentAttraction(Attraction currentAttraction) {
        this.currentAttraction = currentAttraction;
    }


    public int allowAccess(Client client) {

        if (client.getAge() >= currentAttraction.getAgeClassification()
           && client.getHeightOnCm() >= currentAttraction.getMinimumHeight()
           && currentAttraction.getValidPassports().contains(client.getPassportType())
           && currentAttraction.isAvailable())
        {
            currentAttraction.setVisitCount(currentAttraction.getVisitCount()+1);
            return 1;
        } else if(!(client.getAge() >= currentAttraction.getAgeClassification())){
            return 2;
        } else if(!(client.getHeightOnCm() >= currentAttraction.getMinimumHeight())){
            return 3;
        } else if(!(currentAttraction.getValidPassports().contains(client.getPassportType()))){
            return 4;
        } else if(!(getCurrentAttraction().isAvailable())){
            return 5;
        } else{
            return 6;
        }

    }
}

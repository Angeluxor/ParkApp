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

    @Override
    public String toString() {
        return "OperationalEmployee{" +
                "currentAttraction=" + currentAttraction +
                '}';
    }
}

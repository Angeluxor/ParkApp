package me.model.employees;

import me.model.Person;

public class Employee extends Person {
    public int workScheduleType;

    public Employee() {
    }
    public Employee(String name, String email, int id, int phoneNumber, int workScheduleType) {
        super(name, email, id, phoneNumber);
        this.workScheduleType = workScheduleType;
    }

    public int getWorkScheduleType() {
        return workScheduleType;
    }

    public void setWorkScheduleType(int workScheduleType) {
        this.workScheduleType = workScheduleType;
    }
}

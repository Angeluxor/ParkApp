package me.model.employees;

import me.model.Person;

public class Employee extends Person {
    /**
     * Type 1 means 8 a.m. to 5 p.m.
     * Type 2 means 6 a.m. to 2 p.m.
     * Type 3 means 2 p.m to 10 p.m.
     */
    public int workScheduleType;

    public Employee() {
    }

    public Employee(String name, String email, int id, long phoneNumber, int workScheduleType) {
        super(name, email, id, phoneNumber);
        this.workScheduleType = workScheduleType;
    }

    public int getWorkScheduleType() {
        return workScheduleType;
    }

    public void setWorkScheduleType(int workScheduleType) {
        this.workScheduleType = workScheduleType;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "workScheduleType=" + workScheduleType +
                '}';
    }
}

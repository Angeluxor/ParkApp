package me.model.employees;

import me.model.TicketStation;

public class LogisticsEmployee extends Employee {
    private TicketStation CurrentTicketStation;

    public LogisticsEmployee() {
    }

    public LogisticsEmployee(String name, String email, int id, long phoneNumber, int workScheduleType, TicketStation currentTicketStation) {
        super(name, email, id, phoneNumber, workScheduleType);
        CurrentTicketStation = currentTicketStation;
    }

    public TicketStation getCurrentTicketStation() {
        return CurrentTicketStation;
    }

    public void setCurrentTicketStation(TicketStation currentTicketStation) {
        CurrentTicketStation = currentTicketStation;
    }

    @Override
    public String toString() {
        return "LogisticsEmployee{" +
                "CurrentTicketStation=" + CurrentTicketStation +
                '}';
    }
}

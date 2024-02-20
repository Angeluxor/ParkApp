package me.model.employees;

import me.model.TicketStation;
import me.model.clients.Client;
import me.model.clients.UnderAgeClient;

public class LogisticsEmployee extends Employee {
    private TicketStation CurrentTicketStation;

    public LogisticsEmployee() {
    }

    public LogisticsEmployee(String name, String email, int id, int phoneNumber, int workScheduleType, TicketStation currentTicketStation) {
        super(name, email, id, phoneNumber, workScheduleType);
        CurrentTicketStation = currentTicketStation;
    }

    public TicketStation getCurrentTicketStation() {
        return CurrentTicketStation;
    }

    public void setCurrentTicketStation(TicketStation currentTicketStation) {
        CurrentTicketStation = currentTicketStation;
    }

//    public Client createClient(String name, String email, int id, int phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage) {
//        return new Client(name, email, id, phoneNumber, heightOnCm, age, visitCount, discountPercentage);
//    }
//
//    public UnderAgeClient createUnderAgeClient(String name, String email, int id, int phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage, String attendantName, int attendantId, int attendantPhoneNumber) {
//        return new UnderAgeClient(name, email, id, phoneNumber, heightOnCm, age, visitCount, discountPercentage, attendantName, attendantId, attendantPhoneNumber);
//    }

    public void sellPassport(Client client, int passportType) {
        client.setPassportType(passportType);
        client.setVisitCount(client.getVisitCount() + 1);
    }
}

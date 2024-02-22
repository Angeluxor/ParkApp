package me.controller;

import me.model.TicketStation;
import me.model.clients.UnderAgeClient;
import me.model.employees.OperationalEmployee;
import me.view.MenuView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller = new Controller();
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readAllTicketStation() {
        ArrayList<TicketStation> ticketStations = controller.readAllTicketStation();
        assertFalse(ticketStations.isEmpty());
    }

    @Test
    void updateAttractionByName() {
        assertTrue(controller.updateAttractionByName("Carros chocones", "ageClassification", "9"));
        assertEquals( 9, controller.readAttractionByName("Carros chocones").getAgeClassification());
    }

    @Test
    void readAdministrativeEmployeeById() {
        assertEquals(3005117799L, controller.readAdministrativeEmployeeById(71256389).getPhoneNumber());
    }

    @Test
    void readOperationalEmployeeById() {
        OperationalEmployee operationalEmployee = controller.readOperationalEmployeeById(1001241112);
        assertNotNull(operationalEmployee.getCurrentAttraction());
    }

    @Test
    void allowAccess() {
        controller.createClient("Piper Pimienta", "diazpiper@gmail.com", 3698, 5823742L, 186, 51, 0, 0);
        controller.updateClientById(3698, "passportType", "3");
        int result = controller.allowAccess(1001241112, 3698);
        assertEquals(1, result);
    }
}
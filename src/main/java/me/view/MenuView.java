package me.view;

import me.controller.Controller;
import me.model.employees.*;

import java.util.Scanner;

public class MenuView {

    private Controller controller;

    public void mainMenu() {
        Scanner scann = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de gestión para empleados de Salitre Mágico");
        System.out.println("Por favor ingrese su número de documento");
        int employeeId = scann.nextInt();
        Employee employee = controller.ReadEmployee(employeeId);

        switch (employee) {
            case MaintenanceEmployee maintenanceEmployee:
                maintenanceMenu();
                break;
            case AdministrativeEmployee administrativeEmployee:
                administrativeMenu();
                break;
            case LogisticsEmployee logisticsEmployee:
                logisticsMenu();
                break;
            case OperationalEmployee operationalEmployee:
                operationalMenu();
                break;
            case AdversitingEmployee adversitingEmployee:
                advertisingMenu();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + employee.getClass());
        }
    }

    private void advertisingMenu() {

    }

    private void operationalMenu() {

    }

    private void logisticsMenu() {

    }

    private void administrativeMenu() {

    }

    private void maintenanceMenu() {

    }
}

package me.controller;

import me.model.Park;
import me.model.employees.Employee;
import me.view.MenuView;

public class Controller {

    private final MenuView menu;
    private final Park park;

    public Controller(MenuView menu, Park park) {
        this.menu = menu;
        this.park = park;
    }

    public MenuView getMenu() {
        return menu;
    }

    public Park getPark() {
        return park;
    }

    public Employee ReadEmployee(int employeeId) {
        for (Employee employee : park.getEmployeesList()) {
            if(employee.getId() == employeeId){
                return employee;
            }
        }
        return null;
    }
}

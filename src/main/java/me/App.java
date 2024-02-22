package me;

import me.controller.Controller;
import me.view.MenuView;

public class App {
    public static void main(String[] args) {

        MenuView menuView = new MenuView();
        menuView.mainMenu();

        /* BD initial fill using CRUD methods
        controller.createAdministrativeEmployee("Pedro Perez", "pperez@salitre.com", 71256389, 3005117799L, 1);
        controller.createAdvertisingEmployee("Juana Peña", "jpena@salitre.com", 43111222, 3144182945L, 1);
        controller.createMaintenanceEmployee("Juan Albañil", "Juanalba@salitre.com", 92458789, 3112567888L, 2);
        controller.createMaintenanceEmployee("Camilo Manrique", "Camanrique@salitre.com", 1037789882, 3217712324L, 3);
        controller.createLogisticsEmployee("Teresita Rodríguez", "tererod@salitre.com", 42555753, 3025418199L, 2, controller.readTicketStationByNumber(1));
        controller.createLogisticsEmployee("Alfredo Zitarrosa", "alfredozita@salitre.com", 71611708, 3146325273L, 2, controller.readTicketStationByNumber(2));
        controller.createLogisticsEmployee("Manuel Serrat", "jmanuelserrat@salitre.com", 3655592, 3113263951L, 2, controller.readTicketStationByNumber(3));
        controller.createLogisticsEmployee("Antonio Carmona", "tonocarmona@salitre.com", 1037648634, 3007831177L, 3, controller.readTicketStationByNumber(4));
        controller.createLogisticsEmployee("Omara Portuondo", "lasitiera@salitre.com", 43512935, 3122623076L, 3, controller.readTicketStationByNumber(5));
        controller.createOperationalEmployee("Yuniel Jimenez", "jimenezyun@salitre.com", 103655789, 3145225896L, 1, controller.readAttractionByName("Carrusel"));
        controller.createOperationalEmployee("Alba Molina", "albamolina@salitre.com", 1001241112, 3058789191L, 1, controller.readAttractionByName("Montaña rusa"));
        controller.createOperationalEmployee("Soledad Bravo", "solebravo@salitre.com", 98587443, 3043155211L, 1, controller.readAttractionByName("Martillo"));
        controller.createOperationalEmployee("Vicente Miranda", "miranda68@salitre.com", 72200365, 3109916354L, 1, controller.readAttractionByName("Carros chocones"));
         */




    }

}

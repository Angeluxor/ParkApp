package me.view;

import me.controller.Controller;
import me.model.Attraction;
import me.model.Person;
import me.model.clients.Client;
import me.model.clients.UnderAgeClient;
import me.model.employees.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuView {

    private final Controller controller = new Controller();
    private Employee employee = null;

    public void mainMenu() {

        int aux;
        boolean exitFlag = false;
        Scanner scann = new Scanner(System.in);
        while (!exitFlag) {
            aux = 0;
            System.out.println("Bienvenido al sistema de gestión para empleados de Salitre Mágico");
            System.out.println("Por favor ingrese su número de documento");
            try {
                int employeeId = scann.nextInt();
                do {
                    employee = switch (aux) {
                        case 0 -> controller.readAdministrativeEmployeeById(employeeId);
                        case 1 -> controller.readAdvertisingEmployeeById(employeeId);
                        case 2 -> controller.readLogisticsEmployeeById(employeeId);
                        case 3 -> controller.readMaintenanceEmployeeById(employeeId);
                        case 4 -> controller.readOperationalEmployeeById(employeeId);
                        default -> new Employee();
                    };
                    aux++;
                } while (employee == null);
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
                    case AdvertisingEmployee advertisingEmployee:
                        advertisingMenu();
                        break;
                    default:
                        boolean invalidOption = true;
                        int option;
                        System.out.println("Empleado no encontrado");
                        while (invalidOption) {
                            System.out.println("""                            
                                    ¿Desea ingresar otro número de documento?
                                    1.Sí
                                    2.No
                                    """);
                            try {
                                option = scann.nextInt();
                                switch (option) {
                                    case 1:
                                        invalidOption = false;
                                        break;
                                    case 2:
                                        exitFlag = true;
                                        invalidOption = false;
                                        break;
                                    default:
                                        System.out.println("Por favor ingrese una opción válida\n");
                                        break;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor ingrese un número entero\n");
                                scann.next();
                            }
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número entero\n");
                scann.next();
            }
        }
    }

    private void advertisingMenu() {
        Scanner scann = new Scanner(System.in);
        boolean invalidOption = true;
        int option;
        while (invalidOption) {
            System.out.println("""                    
                    Menú publicitario
                    Por favor elija una opción:
                    1. Asignar 25% de descuento a clientes que acumulan tres visitas sin descuento
                    2. Volver a la pantalla de inicio
                    3. Cerrar el aplicativo
                    """);
            try {
                option = scann.nextInt();
                switch (option) {
                    case 1:
                        controller.setDiscount();
                        break;
                    case 2:
                        invalidOption = false;
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción válida\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número entero\n");
                scann.next();
            }
        }
    }

    private void operationalMenu() {
        Scanner scann = new Scanner(System.in);
        boolean invalidOption = true;
        int option;
        while (invalidOption) {
            System.out.println("""                    
                    Menú administrativo
                    Por favor elija una opción:
                    1. Validar acceso del cliente a la atracción
                    2. Volver a la pantalla de inicio
                    3. Cerrar el aplicativo
                    """);
            try {
                option = scann.nextInt();
                switch (option) {
                    case 1:
                        int auxId;
                        System.out.println("Por favor ingrese el número de documento del cliente que desea acceder a la atracción");
                        auxId = scann.nextInt();
                        scann.next();
                        int result = controller.allowAccess(employee.getId(), auxId);
                        switch(result){
                            case 1:
                                System.out.println("Acceso permitido");
                            case 2:
                                System.out.println("Acceso denegado por incumplimiento de la clasificación de edad");
                            case 3:
                                System.out.println("Acceso denegado por incumplimiento del mínimo de altura");
                            case 4:
                                System.out.println("Acceso denegado por pasaporte inválido para la atracción");
                            case 5:
                                System.out.println("Acceso denegado por atracción inhabilitada");
                        }
                        break;
                    case 2:
                        invalidOption = false;
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción válida\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número entero\n");
                scann.next();
            }
        }
    }

    private void logisticsMenu() {
        Scanner scann = new Scanner(System.in);
        boolean invalidOption = true;
        int option;
        while (invalidOption) {
            System.out.println("""                    
                    Menú logístico
                    Por favor elija una opción:
                    1. Vender un pasaporte
                    2. Volver a la pantalla de inicio
                    3. Cerrar el aplicativo
                    """);
            try {
                option = scann.nextInt();
                switch (option) {
                    case 1:
                        int aux = 0;
                        int attractionOption = 0;
                        int auxNum = 1;
                        String passportAcquired;
                        Person client;
                        System.out.println("Ingrese por favor el número de documento del cliente");
                        try {
                            int clientId = scann.nextInt();
                            do {
                                client = switch (aux) {
                                    case 0 -> controller.readClientById(clientId);
                                    //case 1 -> controller.readUnderAgeClientsById(clientId);
                                    default -> new Person();
                                };
                                aux++;
                            } while (client == null);
                            switch (client) {
                                case UnderAgeClient underAgeClient:
                                    System.out.println("Cliente encontrado");
                                    System.out.println("Digite el tipo de pasaporte que desea adquirir el cliente [1, 2 o 3]");
                                    passportAcquired = scann.nextLine();
                                    controller.updateUnderAgeClientsById(client.getId(), "passportType", passportAcquired);
                                    controller.registerVisitor();
                                    break;
                                case Client adultClient:
                                    System.out.println("Cliente encontrado");
                                    System.out.println("Digite el tipo de pasaporte que desea adquirir el cliente [1, 2 o 3]");
                                    passportAcquired = scann.nextLine();
                                    controller.updateClientById(client.getId(), "passportType", passportAcquired);
                                    controller.registerVisitor();
                                    break;
                                default:
                                    String nameAux;
                                    String emailAux;
                                    int idAux;
                                    long phoneNumberAux;
                                    int heightOnCmAux;
                                    int ageAux;
                                    String attendantNameAux;
                                    int attendantIdAux;
                                    long attendantPhoneNumberAux;
                                    String passportAcquiredAux;
                                    System.out.println("Cliente no encontrado");

                                    System.out.println("Es necesario llevar a cabo el registro del nuevo cliente");
                                    System.out.println("Por favor ingrese el nombre del cliente");
                                    scann.next();
                                    nameAux = scann.nextLine();
                                    System.out.println("Por favor ingrese el correo electrónico del cliente");
                                    emailAux = scann.nextLine();
                                    System.out.println("Por favor ingrese el número de documento del cliente");
                                    idAux = scann.nextInt();
                                    System.out.println("Por favor ingrese el número de teléfono del cliente");
                                    phoneNumberAux = scann.nextLong();
                                    System.out.println("Por favor ingrese la altura del cliente en centímetros");
                                    heightOnCmAux = scann.nextInt();
                                    System.out.println("Digite el tipo de pasaporte que desea adquirir el cliente [1, 2 o 3]");
                                    passportAcquiredAux = scann.next();
                                    System.out.println("Por favor ingrese la edad del cliente");
                                    ageAux = scann.nextInt();

                                    if (ageAux > 17) {
                                        controller.createClient(nameAux, emailAux, idAux, phoneNumberAux, heightOnCmAux, ageAux, 0, 0);
                                        controller.updateClientById(idAux, "passportType", passportAcquiredAux);
                                    } else {
                                        System.out.println("Cliente menor de edad");
                                        System.out.println("Por favor ingrese el nombre del acudiente");
                                        attendantNameAux = scann.nextLine();
                                        System.out.println("Por favor ingrese el número de documento del acudiente");
                                        attendantIdAux = scann.nextInt();
                                        scann.next();
                                        System.out.println("Por favor ingrese el número de teléfono del acudiente");
                                        attendantPhoneNumberAux = scann.nextLong();
                                        scann.next();
                                        controller.createUnderAgeClient(nameAux, emailAux, idAux, phoneNumberAux, heightOnCmAux, ageAux, 0, 0, attendantNameAux, attendantIdAux, attendantPhoneNumberAux);
                                        controller.updateUnderAgeClientsById(idAux, "passportType", passportAcquiredAux);
                                    }
                                    controller.registerVisitor();
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor ingrese un número entero\n");
                            scann.next();
                        }
                        break;
                    case 2:
                        invalidOption = false;
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción válida\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número entero\n");
                scann.next();
            }
        }
    }

    private void administrativeMenu() {
        Scanner scann = new Scanner(System.in);
        boolean invalidOption = true;
        int option;
        while (invalidOption) {
            System.out.println("""                    
                    Menú administrativo
                    Por favor elija una opción:
                    1. Evaluar fecha y ocupación del parque habilitando taquillas en consecuencia
                    2. Volver a la pantalla de inicio
                    3. Cerrar el aplicativo
                    """);
            try {
                option = scann.nextInt();
                switch (option) {
                    case 1:
                        controller.setActiveTicketStations();
                        break;
                    case 2:
                        invalidOption = false;
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción válida\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número entero\n");
                scann.next();
            }
        }
    }

    private void maintenanceMenu() {
        Scanner scann = new Scanner(System.in);
        boolean invalidOption = true;
        int option;
        while (invalidOption) {
            System.out.println("""                    
                    Menú de mantenimiento
                    Por favor elija una opción:
                    1. Habilitar/inhabilitar una atracción
                    2. Volver a la pantalla de inicio
                    3. Cerrar el aplicativo
                    """);
            try {
                option = scann.nextInt();
                switch (option) {
                    case 1:
                        int attractionOption = 0;
                        int auxNum = 1;
                        Attraction selectedAttraction;
                        ArrayList<Attraction> attractions = controller.readAllAttraction();
                        do {
                            System.out.println("Elija una atracción de la lista para habilitar/inhabilitar");
                            for (Attraction attraction : attractions) {
                                System.out.println(auxNum + ". " + attraction.getName() + " ¿Disponible? " + attraction.isAvailable());
                                auxNum++;
                            }
                            try {
                                attractionOption = scann.nextInt();
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor ingrese un número entero\n");
                                scann.next();
                            } finally {
                                auxNum = 1;
                            }
                        } while (attractionOption <= 0 || attractionOption > attractions.size());

                        selectedAttraction = attractions.get(attractionOption - 1);

                        controller.setAttractionAvailability(selectedAttraction.getName(), String.valueOf(!selectedAttraction.isAvailable()));
                        break;
                    case 2:
                        invalidOption = false;
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Por favor ingrese una opción válida\n");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor ingrese un número entero\n");
                scann.next();
            }
        }

    }
}

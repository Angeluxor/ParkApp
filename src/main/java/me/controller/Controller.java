package me.controller;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import me.model.Attraction;
import me.model.Park;
import me.model.TicketStation;
import me.model.clients.Client;
import me.model.clients.UnderAgeClient;
import me.model.employees.*;
import me.view.MenuView;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.all;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Controller {

    private MenuView menu;
    private Park park;
    private final CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    private final CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    private final String connectionString = "mongodb+srv://idmiguelangel123:1234@parkdb.0sawctg.mongodb.net/?retryWrites=true&w=majority";

    public Controller(MenuView menu, Park park) {
        this.menu = menu;
        this.park = park;
    }

    public Controller() {

    }

    public MenuView getMenu() {
        return menu;
    }

    public Park getPark() {
        return park;
    }

    public Employee ReadEmployee(int employeeId) {
        for (Employee employee : park.getEmployeesList()) {
            if (employee.getId() == employeeId) {
                return employee;
            }
        }
        return null;
    }

    /*
    CRUD methods for ticketStation
     */
    public boolean createTicketStation(int number, boolean active) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TicketStation> collection = database.getCollection("TicketStations", TicketStation.class);
            TicketStation ticketStation = new TicketStation(number, active);
            collection.insertOne(ticketStation);
            return true;
        }

    }

    public ArrayList<TicketStation> readAllTicketStation() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TicketStation> collection = database.getCollection("TicketStations", TicketStation.class);
            ArrayList<TicketStation> ticketStations = new ArrayList<>();
            collection.find().into(ticketStations);
            return ticketStations;
//            for (TicketStation ticketStation : ticketStations) {
//                System.out.println(ticketStation + "\n");
//            }
        }

    }

    public TicketStation readTicketStationByNumber(int number) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TicketStation> collection = database.getCollection("TicketStations", TicketStation.class);
            return collection.find(eq("number", number)).first();
        }
    }

    public boolean updateTicketStationByNumber(int number, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TicketStation> collection = database.getCollection("TicketStations", TicketStation.class);
            collection.updateOne(Filters.eq("number", number), Updates.set(property, newValue));
            return true;
        }
    }

    public boolean deleteTicketStationByNumber(int number) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TicketStation> collection = database.getCollection("TicketStations", TicketStation.class);
            collection.deleteOne(Filters.eq("number", number));
            return true;
        }
    }

    /*
    CRUD methods for Attraction
     */
    public void createAttraction(String name, int ageClassification, int minimumHeight, ArrayList<Integer> validPassports, int visitCount, boolean available) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Attraction> collection = database.getCollection("Attractions", Attraction.class);
            Attraction attraction = new Attraction(name, ageClassification, minimumHeight, validPassports, visitCount, available);
            collection.insertOne(attraction);
            System.out.println("Atracción: " + attraction + " creada de manera exitosa");
        }

    }
    public ArrayList<Attraction> readAllAttraction() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Attraction> collection = database.getCollection("Attractions", Attraction.class);
            ArrayList<Attraction> attractions = new ArrayList<>();
            collection.find().into(attractions);
            return attractions;
        }

    }
    public Attraction readAttractionByName(String name) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Attraction> collection = database.getCollection("Attractions", Attraction.class);
            return collection.find(eq("name", name)).first();
        }

    }
    public boolean updateAttractionByName(String name, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Attraction> collection = database.getCollection("Attractions", Attraction.class);
            collection.updateOne(Filters.eq("name", name), Updates.set(property, newValue));
            return true;
        }
    }
    public boolean deleteAttractionByName(String name) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Attraction> collection = database.getCollection("Attractions", Attraction.class);
            collection.deleteOne(Filters.eq("name", name));
            return true;
        }
    }

    public void createAdministrativeEmployee(String name, String email, int id, int phoneNumber, int workScheduleType) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdministrativeEmployee> collection = database.getCollection("Administrative employees", AdministrativeEmployee.class);
            AdministrativeEmployee administrativeEmployee = new AdministrativeEmployee(name, email, id, phoneNumber, workScheduleType);
            collection.insertOne(administrativeEmployee);
            System.out.println("Empleado administrativo: " + administrativeEmployee + " creado de manera exitosa");
        }
    }

    public void createAdvertisingEmployee(String name, String email, int id, int phoneNumber, int workScheduleType) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdvertisingEmployee> collection = database.getCollection("Advertising employees", AdvertisingEmployee.class);
            AdvertisingEmployee advertisingEmployee = new AdvertisingEmployee(name, email, id, phoneNumber, workScheduleType);
            collection.insertOne(advertisingEmployee);
            System.out.println("Empleado publicitario: " + advertisingEmployee + " creado de manera exitosa");
        }
    }

    public void createLogisticsEmployee(String name, String email, int id, int phoneNumber, int workScheduleType, TicketStation currentTicketStation) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<LogisticsEmployee> collection = database.getCollection("Logistics employee", LogisticsEmployee.class);
            LogisticsEmployee logisticsEmployee = new LogisticsEmployee(name, email, id, phoneNumber, workScheduleType, currentTicketStation);
            collection.insertOne(logisticsEmployee);
            System.out.println("Empleado logístico: " + logisticsEmployee + " creado de manera exitosa");
        }
    }

    public void createMaintenanceEmployee(String name, String email, int id, int phoneNumber, int workScheduleType) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<MaintenanceEmployee> collection = database.getCollection("Maintenance employees", MaintenanceEmployee.class);
            MaintenanceEmployee maintenanceEmployee = new MaintenanceEmployee(name, email, id, phoneNumber, workScheduleType);
            collection.insertOne(maintenanceEmployee);
            System.out.println("Empleado de mantenimiento: " + maintenanceEmployee + " creado de manera exitosa");
        }
    }

    public void createOperationalEmployee(String name, String email, int id, int phoneNumber, int workScheduleType, Attraction attraction) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<OperationalEmployee> collection = database.getCollection("Operational employees", OperationalEmployee.class);
            OperationalEmployee operationalEmployee = new OperationalEmployee(name, email, id, phoneNumber, workScheduleType, attraction);
            collection.insertOne(operationalEmployee);
            System.out.println("Empleado operativo: " + operationalEmployee + " creado de manera exitosa");
        }
    }

    /*
    Administrative employee methods
     */

    public void setActiveTicketStations() {
        ArrayList<TicketStation> ticketStations = park.getTicketStationsList();
        if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.MONDAY) || LocalDate.now().getDayOfWeek().equals(DayOfWeek.TUESDAY)) {
            for (int i = 0; i < 2; i++) {
                TicketStation randomTicketStation = ticketStations.get((int) (Math.random() * 4));
                while (!randomTicketStation.isActive()) {
                    randomTicketStation = ticketStations.get((int) (Math.random() * 4));
                }
                randomTicketStation.setActive(false);

            }
        }
    }

    public void setActiveTicketStations(double occupancy) {
        ArrayList<TicketStation> ticketStations = park.getTicketStationsList();
        if (occupancy > 60.00) {
            for (TicketStation ticketStation : ticketStations) {
                ticketStation.setActive(true);
            }
        }

    }

    public void setAttractionCondition(Attraction attraction, int newAge, int newHeight, ArrayList<Integer> newPassports) {
        attraction.setAgeClassification(newAge);
        attraction.setMinimumHeight(newHeight);
        attraction.setValidPassports(newPassports);
    }

    /*
    Advertising employee methods
     */

    public void setDiscount(Client client) {
        if ((client.getVisitCount() % 3) == 0) {
            client.setDiscountPercentage(25);
        }
    }

    /*
    Logistic employee methods
     */

    public void createClient(String name, String email, int id, int phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Client> collection = database.getCollection("Clients", Client.class);
            Client client = new Client(name, email, id, phoneNumber, heightOnCm, age, visitCount, discountPercentage);
            collection.insertOne(client);
            System.out.println("Cliente: " + client + " creada de manera exitosa");
        }
    }

    public void createUnderAgeClient(String name, String email, int id, int phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage, String attendantName, int attendantId, int attendantPhoneNumber) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<UnderAgeClient> collection = database.getCollection("Underage clients", UnderAgeClient.class);
            UnderAgeClient underAgeClient = new UnderAgeClient(name, email, id, phoneNumber, heightOnCm, age, visitCount, discountPercentage, attendantName, attendantId, attendantPhoneNumber);
            collection.insertOne(underAgeClient);
            System.out.println("Cliente menor de edad: " + underAgeClient + " creada de manera exitosa");
        }
    }

    public void sellPassport(Client client, int passportType) {
        client.setPassportType(passportType);
        client.setVisitCount(client.getVisitCount() + 1);
    }

    /*
    Maintenance employee methods
     */

    public void setAttractionAvailability(Attraction attraction) {
        attraction.setAvailable(!attraction.isAvailable());
    }

    /*
    Operational employee methods
     */

    public int allowAccess(OperationalEmployee operationalEmployee, Client client) {
        Attraction currentAttraction = operationalEmployee.getCurrentAttraction();
        if (client.getAge() >= currentAttraction.getAgeClassification()
                && client.getHeightOnCm() >= currentAttraction.getMinimumHeight()
                && currentAttraction.getValidPassports().contains(client.getPassportType())
                && currentAttraction.isAvailable()) {
            currentAttraction.setVisitCount(currentAttraction.getVisitCount() + 1);
            return 1;
        } else if (!(client.getAge() >= currentAttraction.getAgeClassification())) {
            return 2;
        } else if (!(client.getHeightOnCm() >= currentAttraction.getMinimumHeight())) {
            return 3;
        } else if (!(currentAttraction.getValidPassports().contains(client.getPassportType()))) {
            return 4;
        } else if (!(currentAttraction.isAvailable())) {
            return 5;
        } else {
            return 6;
        }
    }

}

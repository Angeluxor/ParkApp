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

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class Controller {

    private Park park;
    private final CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    private final CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    private final String connectionString = "mongodb+srv://idmiguelangel123:1234@parkdb.0sawctg.mongodb.net/?retryWrites=true&w=majority";

    public Controller(Park park) {

        this.park = park;
    }

    public Controller() {
    }


    public Park getPark() {
        return park;
    }

//    public Employee ReadEmployee(int employeeId) {
//        for (Employee employee : park.getEmployeesList()) {
//            if (employee.getId() == employeeId) {
//                return employee;
//            }
//        }
//        return null;
//    }

    /**
     * CRUD methods for ticketStation
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
        }

    }

    public TicketStation readTicketStationByNumber(int number) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TicketStation> collection = database.getCollection("TicketStations", TicketStation.class);
            return collection.find(eq("stationNumber", number)).first();
        }
    }

    public boolean updateTicketStationByNumber(int number, String property, String newValue) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<TicketStation> collection = database.getCollection("TicketStations", TicketStation.class);
            switch (property) {
                case "stationNumber":
                    collection.updateOne(Filters.eq("stationNumber", number), Updates.set(property, Integer.parseInt(newValue)));
                    break;
                case "active":
                    collection.updateOne(Filters.eq("stationNumber", number), Updates.set(property, Boolean.getBoolean(newValue)));
                    break;
            }
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

    /**
     * CRUD methods for Attraction
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
            if(property.equalsIgnoreCase("name")){
                collection.updateOne(Filters.eq("name", name), Updates.set(property, newValue));
            } else if(property.equalsIgnoreCase("available")){
                collection.updateOne(Filters.eq("name", name), Updates.set(property, Boolean.getBoolean(newValue)));
            } else if(property.equalsIgnoreCase("validPassports")) {
                collection.updateOne(Filters.eq("name", name), Updates.addToSet(property, Integer.parseInt(newValue)));
            } else if(property.equalsIgnoreCase("visitCount")){
                collection.updateOne(Filters.eq("name", name), Updates.inc(property, 1));
            } else {
                collection.updateOne(Filters.eq("name", name), Updates.set(property, Integer.parseInt(newValue)));
            }
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

    /**
     * CRUD methods for Client
     */
    public boolean createClient(String name, String email, int id, long phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Client> collection = database.getCollection("Clients", Client.class);
            Client client = new Client(name, email, id, phoneNumber, heightOnCm, age, visitCount, discountPercentage);
            collection.insertOne(client);
            return true;
        }
    }

    public ArrayList<Client> readAllClient() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Client> collection = database.getCollection("Clients", Client.class);
            ArrayList<Client> clients = new ArrayList<>();
            collection.find().into(clients);
            return clients;
        }

    }

    public Client readClientById(int id) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Client> collection = database.getCollection("Clients", Client.class);
            return collection.find(eq("_id", id)).first();
        }

    }

    public boolean updateClientById(int id, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Client> collection = database.getCollection("Clients", Client.class);
            if(property.equalsIgnoreCase("name") || property.equalsIgnoreCase("email")){
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, newValue));
            } else {
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, Integer.parseInt(newValue)));
            }
            return true;
        }
    }

    public boolean deleteClientById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<Client> collection = database.getCollection("Clients", Client.class);
            collection.deleteOne(Filters.eq("_id", id));
            return true;
        }
    }

    /**
     * CRUD methods for UnderAgeClient
     */
    public boolean createUnderAgeClient(String name, String email, int id, long phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage, String attendantName, int attendantId, long attendantPhoneNumber) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<UnderAgeClient> collection = database.getCollection("UnderAgeClients", UnderAgeClient.class);
            UnderAgeClient underAgeClient = new UnderAgeClient(name, email, id, phoneNumber, heightOnCm, age, visitCount, discountPercentage, attendantName, attendantId, attendantPhoneNumber);
            collection.insertOne(underAgeClient);
            return true;
        }
    }

    public ArrayList<UnderAgeClient> readAllUnderAgeClient() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<UnderAgeClient> collection = database.getCollection("UnderAgeClients", UnderAgeClient.class);
            ArrayList<UnderAgeClient> underAgeClients = new ArrayList<>();
            collection.find().into(underAgeClients);
            return underAgeClients;
        }

    }

    public UnderAgeClient readUnderAgeClientsById(int id) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<UnderAgeClient> collection = database.getCollection("UnderAgeClients", UnderAgeClient.class);
            return collection.find(eq("_id", id)).first();
        }

    }

    public boolean updateUnderAgeClientsById(int id, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<UnderAgeClient> collection = database.getCollection("UnderAgeClients", UnderAgeClient.class);
            if(property.equalsIgnoreCase("name") || property.equalsIgnoreCase("email") || property.equalsIgnoreCase("attendantName")){
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, newValue));
            } else {
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, Integer.parseInt(newValue)));
            }
            return true;
        }
    }

    public boolean deleteUnderAgeClientsById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<UnderAgeClient> collection = database.getCollection("UnderAgeClients", UnderAgeClient.class);
            collection.deleteOne(Filters.eq("_id", id));
            return true;
        }
    }

    /**
     * CRUD methods for administrativeEmployee
     */
    public boolean createAdministrativeEmployee(String name, String email, int id, long phoneNumber, int workScheduleType) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdministrativeEmployee> collection = database.getCollection("AdministrativeEmployees", AdministrativeEmployee.class);
            AdministrativeEmployee administrativeEmployee = new AdministrativeEmployee(name, email, id, phoneNumber, workScheduleType);
            collection.insertOne(administrativeEmployee);
            return true;
        }
    }

    public ArrayList<AdministrativeEmployee> readAllAdministrativeEmployee() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdministrativeEmployee> collection = database.getCollection("AdministrativeEmployees", AdministrativeEmployee.class);
            ArrayList<AdministrativeEmployee> administrativeEmployees = new ArrayList<>();
            collection.find().into(administrativeEmployees);
            return administrativeEmployees;
        }
    }

    public AdministrativeEmployee readAdministrativeEmployeeById(int id) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdministrativeEmployee> collection = database.getCollection("AdministrativeEmployees", AdministrativeEmployee.class);
            return collection.find(eq("_id", id)).first();
        }

    }

    public boolean updateAdministrativeEmployeeById(int id, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdministrativeEmployee> collection = database.getCollection("AdministrativeEmployees", AdministrativeEmployee.class);
            collection.updateOne(Filters.eq("_id", id), Updates.set(property, newValue));
            if(property.equalsIgnoreCase("name") || property.equalsIgnoreCase("email")){
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, newValue));
            } else {
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, Integer.parseInt(newValue)));
            }
            return true;
        }
    }

    public boolean deleteAdministrativeEmployeeById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdministrativeEmployee> collection = database.getCollection("AdministrativeEmployees", AdministrativeEmployee.class);
            collection.deleteOne(Filters.eq("_id", id));
            return true;
        }
    }

    /**
     * CRUD methods for AdvertisingEmployee
     */
    public boolean createAdvertisingEmployee(String name, String email, int id, long phoneNumber, int workScheduleType) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdvertisingEmployee> collection = database.getCollection("AdvertisingEmployees", AdvertisingEmployee.class);
            AdvertisingEmployee advertisingEmployee = new AdvertisingEmployee(name, email, id, phoneNumber, workScheduleType);
            collection.insertOne(advertisingEmployee);
            return true;
        }
    }

    public ArrayList<AdvertisingEmployee> readAllAdvertisingEmployee() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdvertisingEmployee> collection = database.getCollection("AdvertisingEmployees", AdvertisingEmployee.class);
            ArrayList<AdvertisingEmployee> advertisingEmployees = new ArrayList<>();
            collection.find().into(advertisingEmployees);
            return advertisingEmployees;
        }
    }

    public AdvertisingEmployee readAdvertisingEmployeeById(int id) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdvertisingEmployee> collection = database.getCollection("AdvertisingEmployees", AdvertisingEmployee.class);
            return collection.find(eq("_id", id)).first();
        }

    }

    public boolean updateAdvertisingEmployeeById(int id, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdvertisingEmployee> collection = database.getCollection("AdvertisingEmployees", AdvertisingEmployee.class);
            if(property.equalsIgnoreCase("name") || property.equalsIgnoreCase("email")){
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, newValue));
            } else {
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, Integer.parseInt(newValue)));
            }
            return true;
        }
    }

    public boolean deleteAdvertisingEmployeeById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<AdvertisingEmployee> collection = database.getCollection("AdvertisingEmployees", AdvertisingEmployee.class);
            collection.deleteOne(Filters.eq("_id", id));
            return true;
        }
    }

    /**
     * CRUD methods for LogisticsEmployee
     */
    public boolean createLogisticsEmployee(String name, String email, int id, long phoneNumber, int workScheduleType, TicketStation currentTicketStation) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<LogisticsEmployee> collection = database.getCollection("LogisticsEmployees", LogisticsEmployee.class);
            LogisticsEmployee logisticsEmployee = new LogisticsEmployee(name, email, id, phoneNumber, workScheduleType, currentTicketStation);
            collection.insertOne(logisticsEmployee);
            return true;
        }
    }

    public ArrayList<LogisticsEmployee> readAllLogisticsEmployee() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<LogisticsEmployee> collection = database.getCollection("LogisticsEmployees", LogisticsEmployee.class);
            ArrayList<LogisticsEmployee> logisticsEmployees = new ArrayList<>();
            collection.find().into(logisticsEmployees);
            return logisticsEmployees;
        }
    }

    public LogisticsEmployee readLogisticsEmployeeById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<LogisticsEmployee> collection = database.getCollection("LogisticsEmployees", LogisticsEmployee.class);
            return collection.find(eq("_id", id)).first();
        }

    }

    public boolean updateLogisticsEmployeeById(int id, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<LogisticsEmployee> collection = database.getCollection("LogisticsEmployees", LogisticsEmployee.class);
            if(property.equalsIgnoreCase("name") || property.equalsIgnoreCase("email")){
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, newValue));
            } else if(property.equalsIgnoreCase("currentTicketStation")){
                TicketStation newTicketStation = readTicketStationByNumber(Integer.parseInt(newValue));
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, newTicketStation));
            } else {
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, Integer.parseInt(newValue)));
            }
            return true;
        }
    }

    public boolean deleteLogisticsEmployeeById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<LogisticsEmployee> collection = database.getCollection("LogisticsEmployees", LogisticsEmployee.class);
            collection.deleteOne(Filters.eq("_id", id));
            return true;
        }
    }

    /**
     * CRUD methods for MaintenanceEmployee
     */
    public boolean createMaintenanceEmployee(String name, String email, int id, long phoneNumber, int workScheduleType) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<MaintenanceEmployee> collection = database.getCollection("MaintenanceEmployees", MaintenanceEmployee.class);
            MaintenanceEmployee maintenanceEmployee = new MaintenanceEmployee(name, email, id, phoneNumber, workScheduleType);
            collection.insertOne(maintenanceEmployee);
            return true;
        }
    }

    public ArrayList<MaintenanceEmployee> readAllMaintenanceEmployee() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<MaintenanceEmployee> collection = database.getCollection("MaintenanceEmployees", MaintenanceEmployee.class);
            ArrayList<MaintenanceEmployee> maintenanceEmployees = new ArrayList<>();
            collection.find().into(maintenanceEmployees);
            return maintenanceEmployees;
        }
    }

    public MaintenanceEmployee readMaintenanceEmployeeById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<MaintenanceEmployee> collection = database.getCollection("MaintenanceEmployees", MaintenanceEmployee.class);
            return collection.find(eq("_id", id)).first();
        }

    }

    public boolean updateMaintenanceEmployeeById(int id, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<MaintenanceEmployee> collection = database.getCollection("MaintenanceEmployees", MaintenanceEmployee.class);
            if(property.equalsIgnoreCase("name") || property.equalsIgnoreCase("email")){
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, newValue));
            } else {
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, Integer.parseInt(newValue)));
            }
            return true;
        }
    }

    public boolean deleteMaintenanceEmployeeById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<MaintenanceEmployee> collection = database.getCollection("MaintenanceEmployees", MaintenanceEmployee.class);
            collection.deleteOne(Filters.eq("_id", id));
            return true;
        }
    }

    /**
     * CRUD methods for OperationalEmployee
     */
    public boolean createOperationalEmployee(String name, String email, int id, long phoneNumber, int workScheduleType, Attraction attraction) {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<OperationalEmployee> collection = database.getCollection("OperationalEmployees", OperationalEmployee.class);
            OperationalEmployee operationalEmployee = new OperationalEmployee(name, email, id, phoneNumber, workScheduleType, attraction);
            collection.insertOne(operationalEmployee);
            return true;
        }
    }

    public ArrayList<OperationalEmployee> readAllOperationalEmployee() {

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<OperationalEmployee> collection = database.getCollection("OperationalEmployees", OperationalEmployee.class);
            ArrayList<OperationalEmployee> operationalEmployees = new ArrayList<>();
            collection.find().into(operationalEmployees);
            return operationalEmployees;
        }
    }

    public OperationalEmployee readOperationalEmployeeById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<OperationalEmployee> collection = database.getCollection("OperationalEmployees", OperationalEmployee.class);
            return collection.find(eq("_id", id)).first();
        }

    }

    public boolean updateOperationalEmployeeById(int id, String property, String newValue) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<OperationalEmployee> collection = database.getCollection("OperationalEmployees", OperationalEmployee.class);
            if(property.equalsIgnoreCase("name") || property.equalsIgnoreCase("email")){
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, newValue));
            } else if(property.equalsIgnoreCase("currentTicketStation")){
                Attraction attraction = readAttractionByName(newValue);
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, attraction));
            } else {
                collection.updateOne(Filters.eq("_id", id), Updates.set(property, Integer.parseInt(newValue)));
            }
            return true;
        }
    }

    public boolean deleteOperationalEmployeeById(int id) {
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("Park").withCodecRegistry(pojoCodecRegistry);
            MongoCollection<OperationalEmployee> collection = database.getCollection("OperationalEmployees", OperationalEmployee.class);
            collection.deleteOne(Filters.eq("_id", id));
            return true;
        }
    }

    /**
     * Capabilities methods for administrativeEmployee
     */

    public void setActiveTicketStations() {
        ArrayList<TicketStation> ticketStations = readAllTicketStation();

        if (LocalDate.now().getDayOfWeek().equals(DayOfWeek.MONDAY) || LocalDate.now().getDayOfWeek().equals(DayOfWeek.WEDNESDAY) || park.calculateOccupancy()<=60) {
            for (int i = 0; i < 2; i++) {
                int randomAux = (int) (Math.random() * 5);
                TicketStation randomTicketStation = readTicketStationByNumber(randomAux);
                while (!randomTicketStation.isActive()) {
                    randomAux = (int) (Math.random() * 5);
                    randomTicketStation = readTicketStationByNumber(randomAux);
                }
                updateTicketStationByNumber(randomAux, "active", "false");
            }
        } else {
            for (TicketStation ticketStation : ticketStations) {
                ticketStation.setActive(true);
            }
        }
    }

    /**
     * Capabilities methods for advertisingEmployee
     */
    public void setDiscount() {
        ArrayList<Client> clients = readAllClient();
        for (Client client : clients) {
            if ((client.getVisitCount() % 3) == 0) {
                client.setDiscountPercentage(25);
            }
        }
    }

    /**
     * Capabilities methods for logisticsEmployee
     */

    public void registerVisitor() {
        park.setVisitors(park.getVisitors()+1);
    }

    /**
     * Capabilities methods for maintenanceEmployee
     */

    public void setAttractionAvailability(String name, String available) {
        updateAttractionByName(name, "available",available);
    }

    /**
     * Capabilities methods for operationalEmployee
     */

    public int allowAccess(int operationalEmployeeId, int clientId) {
        OperationalEmployee operationalEmployee = readOperationalEmployeeById(operationalEmployeeId);
        Client client = readClientById(clientId);
        Attraction currentAttraction = operationalEmployee.getCurrentAttraction();
        if (client.getAge() >= currentAttraction.getAgeClassification()
                && client.getHeightOnCm() >= currentAttraction.getMinimumHeight()
                && currentAttraction.getValidPassports().contains(client.getPassportType())
                && currentAttraction.isAvailable()) {
            updateAttractionByName(currentAttraction.getName(), "visitCount", "");
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

package me.model;

public class Person {
    private String name;
    private String email;
    private int id;
    private long phoneNumber;

    public Person() {
    }
    public Person(String name, String email, int id, long phoneNumber) {
        this.name = name;
        this.email = email;
        this.id = id;
        this.phoneNumber = phoneNumber;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", phoneNumber=" + phoneNumber +
                '}';
    }
}

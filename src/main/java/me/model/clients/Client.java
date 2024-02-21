package me.model.clients;

import me.model.Person;

public class Client extends Person {

    private int heightOnCm;
    private int age;
    private int visitCount;
    private int passportType;
    private int discountPercentage;

    public Client() {
    }

    public Client(String name, String email, int id, int phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage) {
        super(name, email, id, phoneNumber);
        this.heightOnCm = heightOnCm;
        this.age = age;
        this.visitCount = visitCount;
        this.discountPercentage = discountPercentage;
    }

    public int getHeightOnCm() {
        return heightOnCm;
    }

    public void setHeightOnCm(int heightOnCm) {
        this.heightOnCm = heightOnCm;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public int getPassportType() {
        return passportType;
    }

    public void setPassportType(int passportType) {
        this.passportType = passportType;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Client{" +
                "heightOnCm=" + heightOnCm +
                ", age=" + age +
                ", visitCount=" + visitCount +
                ", passportType=" + passportType +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}

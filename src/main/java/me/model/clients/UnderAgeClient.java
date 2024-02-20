package me.model.clients;

public class UnderAgeClient extends Client {

    private String attendantName;
    private int attendantId;
    private int attendantPhoneNumber;

    public UnderAgeClient() {
    }

    public UnderAgeClient(String name, String email, int id, int phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage, String attendantName, int attendantId, int attendantPhoneNumber) {
        super(name, email, id, phoneNumber, heightOnCm, age, visitCount, discountPercentage);
        this.attendantName = attendantName;
        this.attendantId = attendantId;
        this.attendantPhoneNumber = attendantPhoneNumber;
    }
}

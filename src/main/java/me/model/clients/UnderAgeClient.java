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

    public String getAttendantName() {
        return attendantName;
    }

    public void setAttendantName(String attendantName) {
        this.attendantName = attendantName;
    }

    public int getAttendantId() {
        return attendantId;
    }

    public void setAttendantId(int attendantId) {
        this.attendantId = attendantId;
    }

    public int getAttendantPhoneNumber() {
        return attendantPhoneNumber;
    }

    public void setAttendantPhoneNumber(int attendantPhoneNumber) {
        this.attendantPhoneNumber = attendantPhoneNumber;
    }

    @Override
    public String toString() {
        return "UnderAgeClient{" +
                "attendantName='" + attendantName + '\'' +
                ", attendantId=" + attendantId +
                ", attendantPhoneNumber=" + attendantPhoneNumber +
                '}';
    }
}

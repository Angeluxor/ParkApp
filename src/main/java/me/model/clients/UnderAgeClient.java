package me.model.clients;

public class UnderAgeClient extends Client {

    private String attendantName;
    private int attendantId;
    private long attendantPhoneNumber;

    public UnderAgeClient() {
    }

    public UnderAgeClient(String name, String email, int id, long phoneNumber, int heightOnCm, int age, int visitCount, int passportType, int discountPercentage, String attendantName, int attendantId, long attendantPhoneNumber) {
        super(name, email, id, phoneNumber, heightOnCm, age, visitCount, passportType, discountPercentage);
        this.attendantName = attendantName;
        this.attendantId = attendantId;
        this.attendantPhoneNumber = attendantPhoneNumber;
    }

    public UnderAgeClient(String name, String email, int id, long phoneNumber, int heightOnCm, int age, int visitCount, int discountPercentage, String attendantName, int attendantId, long attendantPhoneNumber) {
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

    public long getAttendantPhoneNumber() {
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

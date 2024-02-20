package me.model;

public class TicketStation {

    private int stationNumber;
    private boolean active;

    public TicketStation() {
    }

    public TicketStation(int stationNumber, boolean active) {
        this.stationNumber = stationNumber;
        this.active = active;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

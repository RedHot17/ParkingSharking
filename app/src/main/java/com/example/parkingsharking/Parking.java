package com.example.parkingsharking;

public class Parking {
    private String name;
    private String city;
    private int slots;
    private int slotsTaken;
    private double lat;
    private double lng;

    public Parking() {
    }

    public Parking(String name, String city, int slots, int slotsTaken, double lat, double lng) {
        this.name = name;
        this.city = city;
        this.slots = slots;
        this.slotsTaken = slotsTaken;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSlots() {
        return slots;
    }

    public void setSlots(int slots) {
        this.slots = slots;
    }

    public int getSlotsTaken() {
        return slotsTaken;
    }

    public void setSlotsTaken(int slotsTaken) {
        this.slotsTaken = slotsTaken;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}

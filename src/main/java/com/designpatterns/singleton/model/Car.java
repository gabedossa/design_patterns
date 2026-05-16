package com.designpatterns.singleton.model;

public class Car {

    private final String brand;
    private final String model;
    private final int year;

    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }

    @Override
    public String toString() {
        return String.format("%-15s | %-25s | %d", brand, model, year);
    }
}

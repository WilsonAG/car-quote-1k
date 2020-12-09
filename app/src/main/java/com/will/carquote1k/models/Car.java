package com.will.carquote1k.models;

public class Car {

    private String code;
    private String brand;
    private String model;
    private String year;
    private double price;
    private boolean isNew;
    private int milage;
    private boolean singleOwner;

    public Car(String code, String brand, String model, String year, double price, boolean isNew, int milage, boolean singleOwner) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isNew = isNew;
        this.milage = milage;
        this.singleOwner = singleOwner;
    }

    public String getCode() {
        return code;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public boolean isNew() {
        return isNew;
    }

    public int getMilage() {
        return milage;
    }

    public boolean isSingleOwner() {
        return singleOwner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "code='" + code + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                ", price=" + price +
                ", isNew=" + isNew +
                ", milage=" + milage +
                ", singleOwner=" + singleOwner +
                '}';
    }
}

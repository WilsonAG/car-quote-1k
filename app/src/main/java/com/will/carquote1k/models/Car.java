package com.will.carquote1k.models;

public class Car {

    private String code;
    private String brand;
    private String model;
    private int year;
    private int cylinder;
    private String country;
    private double price;
    private boolean isNew;
    private int milage;
    private boolean singleOwner;


    public Car(String code, String brand, String model, int year, int cylinder, String country, double price, boolean isNew, int milage, boolean singleOwner) {
        this.code = code;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.cylinder = cylinder;
        this.country = country;
        this.price = price;
        this.isNew = isNew;
        this.milage = milage;
        this.singleOwner = singleOwner;
    }

    public void update(Car c) {
        this.code = c.getCode();
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.year = c.getYear();
        this.cylinder = c.getCylinder();
        this.country = c.getCountry();
        this.price = c.getPrice();
        this.isNew = c.isNew;
        this.milage = c.milage;
        this.singleOwner = c.isSingleOwner();
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

    public int getYear() {
        return year;
    }

    public int getCylinder() {
        return cylinder;
    }

    public String getCountry() {
        return country;
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
                ", year=" + year +
                ", cylinder=" + cylinder +
                ", country='" + country + '\'' +
                ", price=" + price +
                ", isNew=" + isNew +
                ", milage=" + milage +
                ", singleOwner=" + singleOwner +
                '}';
    }
}

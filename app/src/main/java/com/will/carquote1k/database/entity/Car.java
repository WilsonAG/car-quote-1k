package com.will.carquote1k.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Car implements Serializable {

    @PrimaryKey
    @NonNull
    public String code;

    public String brand;
    public String model;
    public int year;
    public double price;
    public int cylinder;
    public String country;
    public boolean isNew;
    public int milage;
    public boolean uniqueOwner;

    @NonNull
    public String getCode() {
        return code;
    }

    public void setCode(@NonNull String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCylinder() {
        return cylinder;
    }

    public void setCylinder(int cylinder) {
        this.cylinder = cylinder;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public int getMilage() {
        return milage;
    }

    public void setMilage(int milage) {
        this.milage = milage;
    }

    public boolean isUniqueOwner() {
        return uniqueOwner;
    }

    public void setUniqueOwner(boolean uniqueOwner) {
        this.uniqueOwner = uniqueOwner;
    }

    @Override
    public String toString() {
        return "Car{" +
                "code='" + code + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", cylinder=" + cylinder +
                ", country='" + country + '\'' +
                ", isNew='" + isNew + '\'' +
                ", milage=" + milage +
                ", uniqueOwner='" + uniqueOwner + '\'' +
                '}';
    }
}

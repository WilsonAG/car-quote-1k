package com.will.carquote1k.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
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

    @Ignore
    public int ownerQuantity;

    @Ignore
    public boolean haveCrashes;

    @Ignore
    public boolean haveAir;

    @Ignore
    public String tapiz;

    @Ignore
    public String painting;

    @Ignore
    public String body;

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

    public void setNew(boolean aNew) {
        isNew = aNew;
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

    public int getOwnerQuantity() {
        return ownerQuantity;
    }

    public void setOwnerQuantity(int ownerQuantity) {
        this.ownerQuantity = ownerQuantity;
    }

    public boolean isHaveCrashes() {
        return haveCrashes;
    }

    public void setHaveCrashes(boolean haveCrashes) {
        this.haveCrashes = haveCrashes;
    }

    public boolean isHaveAir() {
        return haveAir;
    }

    public void setHaveAir(boolean haveAir) {
        this.haveAir = haveAir;
    }

    public String getTapiz() {
        return tapiz;
    }

    public void setTapiz(String tapiz) {
        this.tapiz = tapiz;
    }

    public String getPainting() {
        return painting;
    }

    public void setPainting(String painting) {
        this.painting = painting;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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
                ", isNew=" + isNew +
                ", milage=" + milage +
                ", uniqueOwner=" + uniqueOwner +
                ", ownerQuantity=" + ownerQuantity +
                ", haveCrashes=" + haveCrashes +
                ", haveAir=" + haveAir +
                ", tapiz='" + tapiz + '\'' +
                ", painting='" + painting + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

package com.will.carquote1k.repository;

import android.content.Context;

import com.will.carquote1k.database.AppDatabase;
import com.will.carquote1k.database.dao.CarDAO;
import com.will.carquote1k.database.entity.Car;

public class CarRepository {

    private CarDAO dao;

    public CarRepository(Context context) {
        this.dao = AppDatabase.getInstance(context).carDAO();
    }

    public Car find(String code) {
        return this.dao.find(code);
    }

    public void add(Car car){
        this.dao.register(car);
    }

    public int update(Car car){
        return this.dao.update(car);
    }

    public int delete(Car car){
        return this.dao.delete(car);
    }
}

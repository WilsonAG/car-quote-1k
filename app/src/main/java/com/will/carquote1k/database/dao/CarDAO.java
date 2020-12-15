package com.will.carquote1k.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.will.carquote1k.database.entity.Car;

@Dao
public interface CarDAO {

    @Query("select * from car where code = :code")
    Car find(String code);

    @Insert
    void register(Car car);

    @Update
    int update(Car car);

    @Delete
    int delete(Car car);

}

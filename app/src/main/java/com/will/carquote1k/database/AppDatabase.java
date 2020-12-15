package com.will.carquote1k.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.will.carquote1k.database.dao.CarDAO;
import com.will.carquote1k.database.dao.UserDAO;
import com.will.carquote1k.database.entity.Car;
import com.will.carquote1k.database.entity.User;

@Database(entities = {User.class, Car.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public static AppDatabase INSTACE;
    public abstract UserDAO userDAO();
    public abstract CarDAO carDAO();

    public static AppDatabase getInstance(Context context){
        if (INSTACE == null) {
            INSTACE = Room.databaseBuilder(context, AppDatabase.class, "concesionario")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTACE;
    }
}

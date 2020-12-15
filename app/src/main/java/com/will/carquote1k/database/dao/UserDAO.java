package com.will.carquote1k.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.will.carquote1k.database.entity.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Query("select * from user where username = :username and password = :password")
    User find(String username, String password);

    @Query("select * from user")
    List<User> getAll();

    @Insert
    void register(User user);

}

package com.will.carquote1k.repository;

import android.content.Context;

import com.will.carquote1k.database.AppDatabase;
import com.will.carquote1k.database.dao.UserDAO;
import com.will.carquote1k.database.entity.User;

import java.util.List;

public class UserRepository {

    private UserDAO dao;

    public UserRepository(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);
        this.dao = db.userDAO();
    }

    public List<User> getUsers() {
        return this.dao.getAll();
    }

    public User find(String username, String code){
        return this.dao.find(username, code);
    }

    public void register(User user){
        this.dao.register(user);
    }

}

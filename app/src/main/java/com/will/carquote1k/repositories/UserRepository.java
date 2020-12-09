package com.will.carquote1k.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.will.carquote1k.models.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UserRepository {

    private static final String FILE = "APP_USERS.json";
    private String fullpath;
    private List<User> users;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public UserRepository(String path, String[] files) throws IOException {
        Gson gson = new Gson();
        this.users = new ArrayList<>();
        this.fullpath = new StringBuilder().append(path).append("/").append(UserRepository.FILE).toString();

        Path filepath = Paths.get(fullpath);

        if (this.haveUsers(files)) {
            byte[] bytes = Files.readAllBytes(filepath);
            String jsonContent = new String(bytes);
            User[] loadedUsers = gson.fromJson(jsonContent, User[].class);
            for (User u : loadedUsers) {
                this.users.add(u);
            }
        } else {
            // Create file and init array
            String usersJson = gson.toJson(this.users);
//            System.out.println(usersJson);
            Files.write(filepath, usersJson.getBytes());
        }
    }

    public boolean isAuth(String username, String password) {
        for (User u :
                this.users) {
//            System.out.println(u);
            if (username.equals(u.getUsername()) && password.equals(u.getPassword())){
                return true;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addNew(User u) throws IOException {
        this.users.add(u);
        this.saveFile();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveFile() throws IOException {
        Gson gson = new Gson();
        Path path = Paths.get(this.fullpath);
        String usersJson = gson.toJson(this.users);
//        System.out.println(usersJson);
        Files.write(path, usersJson.getBytes());
    }

    private boolean haveUsers(String[] files) {
        for (String filename : files) {
            if (filename.equals(UserRepository.FILE)) {
                return true;
            }
        }
        return false;
    }



}

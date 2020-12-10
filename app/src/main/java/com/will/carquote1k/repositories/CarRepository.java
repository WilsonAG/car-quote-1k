package com.will.carquote1k.repositories;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.will.carquote1k.models.Car;
import com.will.carquote1k.models.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CarRepository {

    private static final String FILE = "APP_CARS.json";
    private String fullpath;
    private List<Car> cars;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CarRepository(String path, String[] files) throws IOException {
        Gson gson = new Gson();
        this.cars = new ArrayList<>();
        this.fullpath = new StringBuilder().append(path).append("/").append(CarRepository.FILE).toString();

        Path filepath = Paths.get(fullpath);

        if (this.haveCars(files)) {
            byte[] bytes = Files.readAllBytes(filepath);
            String jsonContent = new String(bytes);
//            System.out.println(jsonContent);
            Car[] loadedCars = gson.fromJson(jsonContent, Car[].class);
            for (Car c : loadedCars) {
                this.cars.add(c);
            }
        } else {
            // Create file and init array
//            System.out.println("Voy a crear un archivo");
            String json = gson.toJson(this.cars);
            Files.write(filepath, json.getBytes());
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void add(Car c) throws IOException {
        this.cars.add(c);
        this.saveFile();
    }

    public Car find(String code) {
        for (Car c :
                this.cars) {
            if (code.equals(c.getCode())) {
                return c;
            }
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean updateCar(String code, Car c) throws IOException {
        Car car = this.find(code);
        if (car == null) {
            return false ;
        }

        int idx = this.cars.indexOf(car);
        this.cars.get(idx).update(c);
        this.saveFile();

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean deleteCar(String code) throws IOException {
        Car car = this.find(code);
        if (car == null) {
            return false ;
        }
        int idx = this.cars.indexOf(car);
        this.cars.remove(idx);
        this.saveFile();

        System.out.println(this.cars);

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveFile() throws IOException {
        Gson gson = new Gson();
        Path path = Paths.get(this.fullpath);
        String usersJson = gson.toJson(this.cars);
        Files.write(path, usersJson.getBytes());
    }

    private boolean haveCars(String[] files) {
        for (String filename : files) {
            if (filename.equals(CarRepository.FILE)) {
                return true;
            }
        }
        return false;
    }
}

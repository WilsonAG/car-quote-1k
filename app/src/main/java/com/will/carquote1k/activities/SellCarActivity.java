package com.will.carquote1k.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.will.carquote1k.R;
import com.will.carquote1k.database.entity.Car;
import com.will.carquote1k.repository.CarRepository;

public class SellCarActivity extends AppCompatActivity {
    private Button btnRegister;
    private Button btnFind;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnQuoter;

    private Spinner options;

    private EditText code;
    private EditText brand;
    private EditText model;
    private EditText year;
    private EditText price;
    private EditText cylinder;
    private EditText country;
    private CheckBox isNew;
    private EditText milage;
    private CheckBox isUniqueOwner;

    private CarRepository carRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_car);

        this.carRepo = new CarRepository(this.getApplicationContext());

        this.code = findViewById(R.id.et_car_code);
        this.brand = findViewById(R.id.et_car_brand);
        this.model = findViewById(R.id.et_car_model);
        this.year = findViewById(R.id.et_car_year);
        this.price = findViewById(R.id.et_car_price);
        this.cylinder = findViewById(R.id.et_car_cylinder);
        this.country = findViewById(R.id.et_car_country);
        this.isNew = findViewById(R.id.chk_new_car);
        this.milage = findViewById(R.id.et_car_km);
        this.isUniqueOwner = findViewById(R.id.chk_car_owner);

        // Toolbar settings
        Toolbar myToolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        //Save button
        this.btnRegister = findViewById(R.id.btn_register);
        this.btnFind = findViewById(R.id.btn_find);
        this.btnUpdate = findViewById(R.id.btn_update);
        this.btnDelete = findViewById(R.id.btn_delete);
        this.btnQuoter = findViewById(R.id.btn_quoter);

        this.btnRegister.setOnClickListener(this::register);
        this.btnFind.setOnClickListener(this::find);
        this.btnUpdate.setOnClickListener(this::update);
        this.btnDelete.setOnClickListener(this::delete);
        this.btnQuoter.setOnClickListener(this::quoter);

    }

    private void quoter(View v) {
        if (!this.validateAll()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent quoter = new Intent(this, QuoterActivity.class);
        quoter.putExtra("price", this.getCar());
        startActivity(quoter);
    }

    private void find(View v) {
        String code = this.code.getText().toString().trim();
        if (code.isEmpty()) {
            Toast.makeText(this, "El codigo es obligatorio", Toast.LENGTH_SHORT).show();
            this.code.setError("Completa este campo");
            return;
        }

        Car car = this.carRepo.find(code);
        if (car == null) {
            Toast.makeText(this, "El codigo no es válido", Toast.LENGTH_SHORT).show();
            return;
        }

        this.code.setText(car.getCode());
        this.brand.setText(car.getBrand());
        this.model.setText(car.getModel());
        this.year.setText(String.valueOf(car.getYear()));
        this.price.setText(String.valueOf(car.getPrice()));
        this.cylinder.setText(String.valueOf(car.getCylinder()));
        this.country.setText(car.getCountry());
        this.isNew.setChecked(car.isNew);
        this.milage.setText(String.valueOf(car.getMilage()));
        this.isUniqueOwner.setChecked(car.isUniqueOwner());
    }

    private void update(View v) {
        if (!this.validateAll()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Car car = this.carRepo.find(this.getCar().getCode());
        if (car == null) {
            Toast.makeText(this, "El codigo no es válido", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            this.carRepo.update(this.getCar());
        }catch (Exception ex) {
            Toast.makeText(this, "Error al actualizar los datos .", Toast.LENGTH_SHORT);
            return;
        }

        Toast.makeText(this, "Vehiculo actualizado.", Toast.LENGTH_SHORT).show();
        this.clean();
    }

    private void delete(View v) {
        if (!this.validateAll()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Car car = this.carRepo.find(this.getCar().getCode());
        if (car == null) {
            Toast.makeText(this, "El codigo no es válido", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            this.carRepo.delete(this.getCar());
        }catch (Exception ex) {
            Toast.makeText(this, "Error al eliminar el vehiculo .", Toast.LENGTH_SHORT);
            return;
        }

        Toast.makeText(this, "Vehiculo eliminado.", Toast.LENGTH_SHORT).show();
        this.clean();
    }

    private void register(View v) {
        if (!this.validateAll()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Car car = this.getCar();

        try {
            this.carRepo.add(car);
        }catch (Exception ex) {
            Toast.makeText(this, "Error al guardar, verifique el codigo.", Toast.LENGTH_SHORT);
            return;
        }

        Toast.makeText(this, "Vehiculo registrado.", Toast.LENGTH_SHORT).show();
        this.clean();
    }

    private boolean validateAll() {
        String code = this.code.getText().toString();
        String brand = this.brand.getText().toString();
        String model = this.model.getText().toString();
        String year = this.year.getText().toString();
        String price = this.price.getText().toString();
        String cylinder = this.cylinder.getText().toString();
        String coutnry = this.country.getText().toString();
        String milage = this.milage.getText().toString();


        if (code.trim().isEmpty() || brand.trim().isEmpty() || model.trim().isEmpty() || price.isEmpty() || year.trim().isEmpty() || cylinder.trim().isEmpty() || coutnry.trim().isEmpty() || milage.trim().isEmpty()) {
            return false;
        }

        return true;
    }

    private Car getCar(){
        String code = this.code.getText().toString();
        String brand = this.brand.getText().toString();
        String model = this.model.getText().toString();
        String year = this.year.getText().toString();
        String price = this.price.getText().toString();
        String cylinder = this.cylinder.getText().toString();
        String country = this.country.getText().toString();
        String milage = this.milage.getText().toString();
        boolean uniqueOwner = this.isUniqueOwner.isChecked();
        boolean isNew = this.isNew.isChecked();

        Car car = new Car();

        car.setCode(code);
        car.setBrand(brand);
        car.setModel(model);
        car.setYear(Integer.parseInt(year));
        car.setPrice(Double.parseDouble(price));
        car.setCylinder(Integer.parseInt(cylinder));
        car.setCountry(country);
        car.setMilage(Integer.parseInt(milage));
        car.setIsNew(isNew);
        car.setUniqueOwner(uniqueOwner);

        return car;
    }

    private void clean() {
        this.code.setText("");
        this.brand.setText("");
        this.model.setText("");
        this.year.setText("");
        this.price.setText("");
        this.cylinder.setText("");
        this.country.setText("");
        this.milage.setText("");
        this.isUniqueOwner.setChecked(false);
        this.isNew.setChecked(false);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }
}
package com.will.carquote1k.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.will.carquote1k.R;
import com.will.carquote1k.models.Car;
import com.will.carquote1k.repositories.CarRepository;

import java.io.IOException;

public class SellCarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button saveChanges;
    private CarRepository cr;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_car);

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

        // Spinner settings
        this.options = findViewById(R.id.sp_options);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sell_options, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        options.setAdapter(adapter);
        options.setOnItemSelectedListener(this);

        //Save button
        this.saveChanges = findViewById(R.id.btn_save);
        saveChanges.setOnClickListener(this::onSubmit);

        String path = this.getFilesDir().toString();
        String[] files = fileList();
        try {
            this.cr = new CarRepository(path, files);
        } catch (IOException e) {
            Toast.makeText(this, "Error al leer los datos", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
        }

        Toast.makeText(this,
                "Para consula, eliminacion o actualizar datos use el codigo del automovil",
                Toast.LENGTH_LONG).show();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onSubmit(View v) {
        if (!this.validate()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (this.options.getSelectedItem().toString()) {
            case "Registrar":
                Car newCar = new Car(
                        this.code.getText().toString(),
                        this.brand.getText().toString(),
                        this.model.getText().toString(),
                        Integer.parseInt(this.year.getText().toString()),
                        Integer.parseInt(this.cylinder.getText().toString()),
                        this.country.getText().toString(),
                        Double.parseDouble(this.price.getText().toString()),
                        this.isNew.isChecked(),
                        Integer.parseInt(this.milage.getText().toString()),
                        this.isUniqueOwner.isChecked()
                );
                try {
                    this.cr.add(newCar);
                    Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(this, "Error al registrar nuevo automovil", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;

            case "Consultar":
                String code = this.code.getText().toString();
                Car searchedCar = cr.find(code);
                if (searchedCar == null) {
                    Toast.makeText(this, "El automovil con ese codigo no existe", Toast.LENGTH_SHORT).show();
                    return;
                }

//                System.out.println(searchedCar);

                this.code.setText(searchedCar.getCode());
                this.brand.setText(searchedCar.getBrand());
                this.model.setText(searchedCar.getModel());
                this.year.setText(String.valueOf(searchedCar.getYear()));
                this.price.setText(String.valueOf(searchedCar.getPrice()));
                this.country.setText(searchedCar.getCountry());
                this.cylinder.setText(String.valueOf(searchedCar.getCylinder()));
                this.isNew.setChecked(searchedCar.isNew());
                this.milage.setText(String.valueOf(searchedCar.getMilage()));
                this.isUniqueOwner.setChecked(searchedCar.isSingleOwner());
                break;

            case "Actualizar":
                Car updated = new Car(
                        this.code.getText().toString(),
                        this.brand.getText().toString(),
                        this.model.getText().toString(),
                        Integer.parseInt(this.year.getText().toString()),
                        Integer.parseInt(this.cylinder.getText().toString()),
                        this.country.getText().toString(),
                        Double.parseDouble(this.price.getText().toString()),
                        this.isNew.isChecked(),
                        Integer.parseInt(this.milage.getText().toString()),
                        this.isUniqueOwner.isChecked()
                );

                try {
                    boolean status = this.cr.updateCar(updated.getCode(), updated);
                    if (status) {
                        Toast.makeText(this, "Automovil actualizado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "El codigo no es válido", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(this, "Error al guardar cambios", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;

            case "Eliminar":
                String carCode = this.code.getText().toString();
                try {
                    boolean status = this.cr.deleteCar(carCode);
                    if (status) {
                        Toast.makeText(this, "Automovil Eliminado", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "El codigo no es válido", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    Toast.makeText(this, "Error al guardar cambios", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }

    private boolean validate() {
        String code = this.code.getText().toString();
        String brand = this.brand.getText().toString();
        String model = this.model.getText().toString();
        String year = this.year.getText().toString();
        String price = this.price.getText().toString();
        String cylinder = this.cylinder.getText().toString();
        String coutnry = this.country.getText().toString();
        String milage = this.milage.getText().toString();

        if ((this.options.getSelectedItem().toString().equals("Consultar") || this.options.getSelectedItem().toString().equals("Eliminar"))
                && !code.trim().isEmpty()) {
            System.out.println("query o delete con code vacio");
            return true;
        }

        if (code.trim().isEmpty() || brand.trim().isEmpty() || model.trim().isEmpty() || price.isEmpty() || year.trim().isEmpty() || cylinder.trim().isEmpty() || coutnry.trim().isEmpty() || milage.trim().isEmpty()) {
            return false;
        }

        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();

        if (!selected.equals("Cotizar")){
            return;
        }

        if(this.validate()) {
            Intent quoter = new Intent(this, QuoterActivity.class);
            quoter.putExtra("price", Double.parseDouble(this.price.getText().toString()));
            quoter.putExtra("year", Integer.parseInt(this.year.getText().toString()));

            startActivity(quoter);
        } else {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
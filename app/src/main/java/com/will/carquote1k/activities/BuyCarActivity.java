package com.will.carquote1k.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.will.carquote1k.R;
import com.will.carquote1k.database.entity.Car;
import com.will.carquote1k.enums.Status;

import java.util.Calendar;

import static com.will.carquote1k.enums.Status.*;

public class BuyCarActivity extends AppCompatActivity {

    private EditText brand;
    private EditText model;
    private EditText year;
    private EditText milage;
    private EditText price;
    private EditText ownerQuantity;
    private CheckBox haveCrashes;
    private CheckBox haveAir;
    private Spinner tapiz;
    private Spinner painting;
    private Spinner body;

    private Button btnPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_car);

        // Toolbar settings
        Toolbar myToolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        this.initView();

        // Test
//        this.brand.setText("asdasdas");
//        this.model.setText("asdasdas");
//        this.year.setText("2018");
//        this.milage.setText("5461");
//        this.price.setText("25000");
//        this.ownerQuantity.setText("1");
    }

    private void getPutchaseValue(View v) {

        if (!this.validate()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Car car = this.getCarInstance();

        Double price = car.getPrice();
        Double originalPrice = car.getPrice();

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if (car.getYear() > currentYear) {
            Toast.makeText(this, "El año es mayor al actual.", Toast.LENGTH_SHORT).show();
            return;
        }
        price -= originalPrice * (currentYear - car.getYear()) * 0.015;

        if (car.getMilage() > 80000) {
            price -= originalPrice * 0.025;
        }

        if (car.ownerQuantity > 1) {
            price -= originalPrice * (car.ownerQuantity * 0.02);
        }

        if (car.isHaveCrashes()){
            price -= originalPrice * 0.15;
        }

        if (!car.isHaveAir()) {
            price -= originalPrice * 0.005;
        }

        price += car.getTapiz().equals(EXCELENTE.toString()) ? originalPrice * 0.01 : 0;
        price -= car.getTapiz().equals(REGULAR.toString()) ? originalPrice * 0.015 : 0;
        price -= car.getTapiz().equals(MALA.toString()) ? originalPrice * 0.03 : 0;

        price += car.getPainting().equals(EXCELENTE.toString()) ? originalPrice * 0.02 : 0;
        price -= car.getPainting().equals(REGULAR.toString()) ? originalPrice * 0.02 : 0;
        price -= car.getPainting().equals(MALA.toString()) ? originalPrice * 0.1 : 0;

        price += car.getBody().equals(EXCELENTE.toString()) ? originalPrice * 0.02 : 0;
        price -= car.getBody().equals(REGULAR.toString()) ? originalPrice * 0.02 : 0;
        price -= car.getBody().equals(MALA.toString()) ? originalPrice * 0.1 : 0;

        TextView desc = findViewById(R.id.tv_text_buy_price);
        TextView buyPrice = findViewById(R.id.tv_total_buy_price);

        buyPrice.setText(String.format("%.2f", price));
        desc.setVisibility(View.VISIBLE);
        buyPrice.setVisibility(View.VISIBLE);


    }

    private Car getCarInstance() {

        String brand = this.brand.getText().toString();
        String model = this.model.getText().toString();
        String year = this.year.getText().toString();
        String milage = this.milage.getText().toString();
        String price = this.price.getText().toString();
        String ownerQuantity = this.ownerQuantity.getText().toString();

        boolean haveCrashes = this.haveCrashes.isChecked();
        boolean haveAir = this.haveAir.isChecked();

        String tapiz = this.tapiz.getSelectedItem().toString();
        String painting = this.painting.getSelectedItem().toString();
        String body = this.body.getSelectedItem().toString();

        Car car = new Car();

        car.setBrand(brand);
        car.setModel(model);
        car.setYear(Integer.parseInt(year));
        car.setMilage(Integer.parseInt(milage));
        car.setPrice(Double.parseDouble(price));
        car.setOwnerQuantity(Integer.parseInt(ownerQuantity));

        car.setHaveCrashes(haveCrashes);
        car.setHaveAir(haveAir);

        car.setTapiz(tapiz);
        car.setPainting(painting);
        car.setBody(body);

        return car;
    }

    private boolean validate() {
        String brand = this.brand.getText().toString();
        String model = this.model.getText().toString();
        String year = this.year.getText().toString();
        String milage = this.milage.getText().toString();
        String price = this.price.getText().toString();
        String ownerQuantity = this.ownerQuantity.getText().toString();

        if (brand.isEmpty() || model.isEmpty() || year.isEmpty() || milage.isEmpty() || price.isEmpty() || ownerQuantity.isEmpty() ){
            return false;
        }

        return true;
    }

    private void initView() {
        this.brand = findViewById(R.id.et_brand);
        this.model = findViewById(R.id.et_model);
        this.year = findViewById(R.id.et_year);
        this.milage = findViewById(R.id.et_milage);
        this.price = findViewById(R.id.et_sell_price);
        this.ownerQuantity = findViewById(R.id.et_owner_quantity);

        this.haveCrashes = findViewById(R.id.chk_crashes);
        this.haveAir = findViewById(R.id.chk_air);

        this.tapiz = findViewById(R.id.sp_tapiz);
        this.painting = findViewById(R.id.sp_painting);
        this.body = findViewById(R.id.sp_body);

        ArrayAdapter<Status> adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values());
        this.tapiz.setAdapter(adp);
        this.painting.setAdapter(adp);
        this.body.setAdapter(adp);

        this.btnPrice =findViewById(R.id.btn_eval_price);
        this.btnPrice.setOnClickListener(this::getPutchaseValue);
    }


    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }
}
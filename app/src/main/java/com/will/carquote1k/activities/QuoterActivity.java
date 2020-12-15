package com.will.carquote1k.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.will.carquote1k.R;
import com.will.carquote1k.database.entity.Car;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class QuoterActivity extends AppCompatActivity {

    private final Integer[] ARRAY_QUOTES = new Integer[]{12, 24, 48, 60};
    private final double INTEREST = 0.15;

    private Spinner quotes;
    private Button btnCompute;
    private EditText entry;
    private TextView totalPrice;
    private TextView priceDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoter);

        // Toolbar settings
        Toolbar myToolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        this.initView();
    }

    private void getFinalPrice(View v) {
        String entryText = this.entry.getText().toString();
        Double entryVal = entryText.isEmpty()
                ? 0
                : Double.parseDouble(entryText);

        Car car = (Car) getIntent().getSerializableExtra("car");
        if (entryVal < car.getPrice() * 0.1) {
            this.entry.setError("El valor de entrada debe ser mayor al 10% del precio.");
            return;
        }

        Double price = car.getPrice() - entryVal;
        int quotes = (int) this.quotes.getSelectedItem();
        // Eval monthly price
        double monthlyPrice = (price + (price * this.INTEREST)) / quotes;
        double totalPrice = monthlyPrice * quotes;

        DecimalFormat df = new DecimalFormat("#.##");

        this.totalPrice.setText(new StringBuilder().append("$ ").append(df.format(totalPrice)));
        this.priceDetail.setText(
                new StringBuilder()
                        .append(quotes)
                        .append(" Meses de $ ")
                        .append(df.format(monthlyPrice))
                        .append(" con intereses de ")
                        .append(this.INTEREST * 100).append("%")
        );

    }

    private void initView() {
        this.entry = findViewById(R.id.et_entry);
        this.totalPrice = findViewById(R.id.tv_total_price);
        this.priceDetail = findViewById(R.id.tv_detail);

        this.btnCompute = findViewById(R.id.btn_quoter);
        this.btnCompute.setOnClickListener(this::getFinalPrice);

        this.quotes = findViewById(R.id.sp_quotes);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, this.ARRAY_QUOTES);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.quotes.setAdapter(adapter);
    }


    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }
}
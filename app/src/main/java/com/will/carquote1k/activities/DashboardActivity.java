package com.will.carquote1k.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.will.carquote1k.R;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Toolbar settings
        Toolbar myToolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        TextView title = findViewById(R.id.dashboard_title);
        String username = getIntent().getStringExtra("user");
        title.setText(new StringBuilder().append("Bienvenido ").append(username));

        Button btnSell = findViewById(R.id.btn_sell_car);
        Button btnBuy = findViewById(R.id.btn_buy_car);

        btnSell.setOnClickListener( v -> {
            Intent sellPage = new Intent(this, SellCarActivity.class);
            startActivity(sellPage);
        });

        btnBuy.setOnClickListener(v -> {
            Intent buyPage = new Intent(this, BuyCarActivity.class);
            startActivity(buyPage);
        });
    }
}
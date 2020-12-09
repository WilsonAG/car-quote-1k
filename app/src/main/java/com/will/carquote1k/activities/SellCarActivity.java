package com.will.carquote1k.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.will.carquote1k.R;
import com.will.carquote1k.repositories.CarRepository;

import java.io.IOException;

public class SellCarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button saveChanges;
    private CarRepository cr;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_car);

        // Toolbar settings
        Toolbar myToolbar = findViewById(R.id.dashboard_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Spinner settings
        Spinner options = findViewById(R.id.sp_options);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sell_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        options.setAdapter(adapter);

        //Save button
        this.saveChanges = findViewById(R.id.btn_save);
        saveChanges.setOnClickListener(this::onSubmit);

        String path = this.getFilesDir().toString();
        String[] files = fileList();
        try {
            this.cr = new CarRepository(path, files);
        } catch (IOException e) {
            Toast.makeText(this, "Error al leer los datos", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void onSubmit(View v) {

    }


    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(parent.getItemAtPosition(position).toString());;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
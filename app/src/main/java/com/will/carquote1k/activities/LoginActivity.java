package com.will.carquote1k.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.will.carquote1k.R;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.user = findViewById(R.id.etUsername);
        this.password = findViewById(R.id.etPassword);

        Button submit = findViewById(R.id.btnLogin);
        Button register = findViewById(R.id.btnRegister);

        submit.setOnClickListener(this::redirectToDashboard);
        register.setOnClickListener(this::redirectToRegister);

        // Lunch first time
        SharedPreferences settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        boolean isFirstTime = settings.getBoolean("alreadyStarted", true);

        if (isFirstTime) {
            // Nav To register
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("alreadyStarted", false); // set started to false
            editor.apply();

            Intent registerPage = new Intent(this, RegisterActivity.class);
            startActivity(registerPage);
        }
    }

    private void redirectToRegister(View v) {
        Intent registerPage = new Intent(this, RegisterActivity.class);
        startActivity(registerPage);
    }

    private void redirectToDashboard(View v) {
        // TODO: Redirect when login success
        System.out.println("Redirect to Dashboard");
    }
}
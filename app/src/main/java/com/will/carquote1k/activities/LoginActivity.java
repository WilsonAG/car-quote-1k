package com.will.carquote1k.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.will.carquote1k.R;
import com.will.carquote1k.repositories.UserRepository;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;
    private UserRepository userRepo;
    private SharedPreferences settings;
    private boolean loggedIn;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.user = findViewById(R.id.etUsername);
        this.password = findViewById(R.id.etPassword);

        Button submit = findViewById(R.id.btnLogin);
        Button register = findViewById(R.id.btnRegister);

        String[] files = fileList();
        String path = this.getFilesDir().getPath().toString();
        try {
            this.userRepo = new UserRepository(path, files);
        } catch (IOException e) {
            Toast.makeText(this, "Error al leer archivo", Toast.LENGTH_SHORT).show();
        }

        submit.setOnClickListener(this::redirectToDashboard);
        register.setOnClickListener(this::redirectToRegister);

        // Lunch first time
        this.settings = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        boolean isFirstTime = settings.getBoolean("alreadyStarted", true);
        this.loggedIn = settings.getBoolean("isLoggedIn", false);

        // Comment the below line to enable auto-loggin
//        this.loggedIn = false;

        if (isFirstTime) {
            // Nav To register
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("alreadyStarted", false); // set started to false
            editor.apply();

            Intent registerPage = new Intent(this, RegisterActivity.class);
            startActivity(registerPage);
        }

        if (loggedIn) {
            this.redirectToDashboard();
        }


    }

    private void redirectToRegister(View v) {
        Intent registerPage = new Intent(this, RegisterActivity.class);
        startActivity(registerPage);
    }

    private void redirectToDashboard(View v) {
        this.redirectToDashboard();
    }

    private void redirectToDashboard() {
        String user;
        String password;
        if (!this.loggedIn) {
            user = this.user.getText().toString();
            password = this.password.getText().toString();
        } else {
            user = this.settings.getString("user", "no hay");
            password = this.settings.getString("pass", "no hay");
        }

        System.out.println(user);
        System.out.println(password);

        boolean authorized = this.userRepo.isAuth(user, password);
        if (!authorized) {
            Toast.makeText(this, "Credenciales no validas", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences.Editor editor = this.settings.edit();
        editor.putString("user", user);
        editor.putString("pass", password);
        editor.putBoolean("isLoggedIn", true);
        editor.apply();

        Intent next = new Intent(this, DashboardActivity.class);
        next.putExtra("user", user);
        startActivity(next);
    }
}
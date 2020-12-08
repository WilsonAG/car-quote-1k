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

import androidx.appcompat.app.AppCompatActivity;

import com.will.carquote1k.R;
import com.will.carquote1k.repositories.UserRepository;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;
    private UserRepository userRepo;


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

        String[] files = fileList();
        String path = this.getFilesDir().getPath().toString();
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                this.userRepo = new UserRepository(path, files);
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error al leer archivo", Toast.LENGTH_SHORT).show();
        }
    }

    private void redirectToRegister(View v) {
        Intent registerPage = new Intent(this, RegisterActivity.class);
        startActivity(registerPage);
    }

    private void redirectToDashboard(View v) {
        // TODO: Redirect when login success
        String user = this.user.getText().toString();
        String password = this.password.getText().toString();
        boolean authorized = this.userRepo.isAuth(user, password);

        if (!authorized) {
            Toast.makeText(this, "Credenciales no validas", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "Redirect to dashboard", Toast.LENGTH_SHORT).show();

//        Intent next = new Intent(this, ??);
//        startActivity(next);

    }
}
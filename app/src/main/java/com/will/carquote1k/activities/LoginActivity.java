package com.will.carquote1k.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.will.carquote1k.R;
import com.will.carquote1k.database.entity.User;
import com.will.carquote1k.repository.UserRepository;

public class LoginActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;

    private UserRepository userRepo;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.userRepo = new UserRepository(this.getApplicationContext());

        this.user = findViewById(R.id.etUsername);
        this.password = findViewById(R.id.etPassword);

//        Test
        this.user.setText("will");
        this.password.setText("will123.");

        Button submit = findViewById(R.id.btnLogin);
        Button register = findViewById(R.id.btnRegister);


        submit.setOnClickListener(this::redirectToDashboard);
        register.setOnClickListener(this::redirectToRegister);

        // Lunch first time
        if (this.userRepo.getUsers().isEmpty()){
            this.redirectToRegister();
        }

    }

    private void redirectToRegister() {
        Intent registerPage = new Intent(this, RegisterActivity.class);
        startActivity(registerPage);
    }

    private void redirectToRegister(View v) {
        this.redirectToRegister();
    }

    private void redirectToDashboard(View v) {
        this.redirectToDashboard();
    }

    private void redirectToDashboard() {
        String username =  this.user.getText().toString();;
        String password = this.password.getText().toString();;

        User user  = this.userRepo.find(username, password);

        if (user == null) {
            Toast.makeText(this, "Credenciales no validas", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent next = new Intent(this, DashboardActivity.class);
        next.putExtra("user", user.getName());
        startActivity(next);
    }
}
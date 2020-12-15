package com.will.carquote1k.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.will.carquote1k.R;
import com.will.carquote1k.database.entity.User;
import com.will.carquote1k.repository.UserRepository;

import java.util.List;

import static com.will.carquote1k.R.string.error_ced;
import static com.will.carquote1k.R.string.error_confirm_password;
import static com.will.carquote1k.R.string.error_password;
import static com.will.carquote1k.R.string.error_required_message;

public class RegisterActivity extends AppCompatActivity implements Validator.ValidationListener {

    @NotEmpty(messageResId = error_required_message )
    private EditText name;

    @NotEmpty(messageResId = error_required_message )
    @Length(min = 10, max = 10, messageResId = error_ced)
    private EditText ced;

    @NotEmpty(messageResId = error_required_message )
    private EditText code;

    @NotEmpty(messageResId = error_required_message )
    private EditText username;

    @NotEmpty(messageResId = error_required_message )
    @Password(min = 8, scheme = Password.Scheme.ALPHA_NUMERIC_SYMBOLS, messageResId = error_password)
    private EditText password;

    @ConfirmPassword(messageResId = error_confirm_password)
    private EditText password2;

    private Validator registerValidator;
    private Button submit;
    private UserRepository usersRepo;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.initView();

        // Users DB
        this.usersRepo = new UserRepository(this.getApplicationContext());
        List<User> users = this.usersRepo.getUsers();
        System.out.println(users.isEmpty());


        // Toolbar settings
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        this.submit.setOnClickListener(this::onClickSubmit);

        this.registerValidator = new Validator(this);
        registerValidator.setValidationListener(this);

    }

    private void onClickSubmit(View v) {
        this.registerValidator.validate();
    }

    private void initView() {
        this.name = findViewById(R.id.etName);
        this.ced = findViewById(R.id.etCed);
        this.code = findViewById(R.id.etCode);
        this.username = findViewById(R.id.etUsernameRegister);
        this.password = findViewById(R.id.etPassowordRegister);
        this.password2 = findViewById(R.id.etPassowrdRegister2);
        this.submit = findViewById(R.id.btnRegisterRegister);

        // For test
//        this.name.setText("Will");
//        this.ced.setText("1721181210");
//        this.code.setText("123456");
//        this.username.setText("will");
//        this.password.setText("will123.");
//        this.password2.setText("will123.");
    }

    @Override
    public boolean onSupportNavigateUp() {
        // Back button action
        finish();
        return true;
    }

    @Override
    public void onValidationSucceeded() {
        String name = this.name.getText().toString();
        String ced = this.ced.getText().toString();
        String code = this.code.getText().toString();
        String user = this.username.getText().toString();
        String pass = this.password.getText().toString();

        try {
            User newUser = new User();
            newUser.setName(name);
            newUser.setId(ced);
            newUser.setCode(code);
            newUser.setUsername(user);
            newUser.setPassword(pass);

            this.usersRepo.register(newUser);
        } catch (Exception ex) {
            Toast.makeText(this, "Error al registar, verifique su cedula", Toast.LENGTH_SHORT).show();
            return;
        }

        startActivity(new Intent(this, LoginActivity.class));
        Toast.makeText(this, "Registro completado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }

    }
}
package com.example.ebite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    Button SignUp,Login;
    TextInputLayout email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       //Hooks
        Login = findViewById(R.id.login);
        SignUp = findViewById(R.id.SignUp);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        //Get values entered by the user
        String EMAIL = email.getEditText().getText().toString();
        String PASSWORD = password.getEditText().getText().toString();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail()|!validatePassword()){
                    return;
                }
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
            }
        });



    }
    //methods to display errors
    public Boolean validateEmail(){
        String val = email.getEditText().getText().toString();
        if(val.isEmpty()){
            email.setError("Field cannot be empty.");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }
    public Boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        if(val.isEmpty()){
            password.setError("Field cannot be empty.");
            return false;
        }else{
            password.setError(null);
            return true;
        }
    }
}
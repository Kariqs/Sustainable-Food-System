package com.example.ebite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    Button SignUp, Login;
    TextInputLayout Phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Hooks
        Login = findViewById(R.id.login);
        SignUp = findViewById(R.id.SignUp);
        Phone = findViewById(R.id.phoneNo);
        password = findViewById(R.id.password);

        //Get values entered by the user
        String EMAIL = Phone.getEditText().getText().toString();
        String PASSWORD = password.getEditText().getText().toString();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateUsername() | !validatePassword()) {
                    return;
                } else {
                    isUser();
                    finish();
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
    private Boolean validateUsername() {
        String val = Phone.getEditText().getText().toString();
        if (val.isEmpty()) {
            Phone.setError("Field cannot be empty.");
            return false;
        } else {
            Phone.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()) {
            password.setError("Field cannot be empty.");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private void isUser() {
        String userEnteredPhonenumber = Phone.getEditText().getText().toString().trim();
        String userEnteredPassword = password.getEditText().getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUser = reference.orderByChild("phonenumber").equalTo(userEnteredPhonenumber);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Phone.setError(null);
                    Phone.setErrorEnabled(false);
                    String passwordFromDB = snapshot.child(userEnteredPhonenumber).child("password").getValue(String.class);
                    if (passwordFromDB.equals(userEnteredPassword)) {

                        password.setError(null);
                        password.setErrorEnabled(false);

                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                        finish();
                    } else {
                        password.setError("Wrong password.");
                        password.requestFocus();
                    }
                } else {
                    Phone.setError("No such user exist.");
                    Phone.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
package com.example.ebite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    ImageView logo;
    TextView tv1,tv2;
    TextInputLayout name,username,email,phonenumber,password,confirmPassword;
    Button register,haveAccount;

    FirebaseDatabase rootNode;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         //Hooks to xml elements on the sign up activity
        haveAccount = findViewById(R.id.haveAccount);
        register = findViewById(R.id.register);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phonenumber = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmpassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //Get Values entered by the user
            if (!password.getEditText().getText().toString().equals(confirmPassword.getEditText().getText().toString())){
                Toast.makeText(SignUp.this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            }else if (!validateName() | !validateUserName()|!validateEmail() | !validatePhoneNumber()|!validatePassword()|!validateConfirmPassword()){
               return;
            }
            else {
                registerUser();
                clear();
                Toast.makeText(SignUp.this, "Registration was successful.", Toast.LENGTH_SHORT).show();
            }


            }
        });

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);

            }
        });

    }

    private Boolean validateName(){
        String val = name.getEditText().getText().toString();
        if (val.isEmpty()){
            name.setError("Field cannot be empty.");
            return false;
        }else{
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUserName(){
        String val = username.getEditText().getText().toString();
        if (val.isEmpty()){
            username.setError("Field cannot be empty.");
            return false;
        }else if (val.length()>=15){
            username.setError("Username is too long.");
            return false;
        } else{
            username.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val = email.getEditText().getText().toString();
        if (val.isEmpty()){
            email.setError("Field cannot be empty.");
            return false;
        } else {
            email.setError(null);
            email.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNumber(){
        String val = phonenumber.getEditText().getText().toString();
        if (val.isEmpty()){
            phonenumber.setError("Field cannot be empty.");
            return false;
        }else{
            username.setError(null);
            phonenumber.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        if (val.isEmpty()){
            password.setError("Field cannot be empty.");
            return false;
        }else{
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateConfirmPassword(){
        String val = confirmPassword.getEditText().getText().toString();
        if (val.isEmpty()){
            confirmPassword.setError("Field cannot be empty.");
            return false;
        }else{
            confirmPassword.setError(null);
            confirmPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void clear(){
        name.getEditText().setText("");
        username.getEditText().setText("");
        email.getEditText().setText("");
        phonenumber.getEditText().setText("");
        password.getEditText().setText("");
        confirmPassword.getEditText().setText("");
    }

    public void registerUser(){
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");
        String NAME = name.getEditText().getText().toString();
        String USERNAME = username.getEditText().getText().toString();
        String EMAIL = email.getEditText().getText().toString();
        String PHONENUMBER = phonenumber.getEditText().getText().toString();
        String PASSWORD = password.getEditText().getText().toString();
        String CONFIRMPASSWORD = confirmPassword.getEditText().getText().toString();
        DBHelperClass dbHelperClass = new DBHelperClass(NAME,USERNAME,EMAIL,PHONENUMBER,PASSWORD,CONFIRMPASSWORD);
        reference.child(PHONENUMBER).setValue(dbHelperClass);


    }
}
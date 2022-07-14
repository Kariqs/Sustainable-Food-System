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
            rootNode = FirebaseDatabase.getInstance();
            reference = rootNode.getReference("users");

            //Get Values entered by the user
            if (password.getEditText().getText().toString().equals(confirmPassword.getEditText().getText().toString())){
                String NAME = name.getEditText().getText().toString();
                String USERNAME = username.getEditText().getText().toString();
                String EMAIL = email.getEditText().getText().toString();
                String PHONENUMBER = phonenumber.getEditText().getText().toString();
                String PASSWORD = password.getEditText().getText().toString();
                String CONFIRMPASSWORD = confirmPassword.getEditText().getText().toString();

                DBHelperClass dbHelperClass = new DBHelperClass(NAME,USERNAME,EMAIL,PHONENUMBER,PASSWORD,CONFIRMPASSWORD);
                reference.child(PHONENUMBER).setValue(dbHelperClass);
            } else {
                Toast.makeText(SignUp.this, "Please confirm your password to proceed", Toast.LENGTH_SHORT).show();
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
}
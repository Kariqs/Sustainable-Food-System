package com.example.ebite;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

public class Post extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    Button chooseImage,uploadImage;
    TextInputLayout Description;
    ImageView showImage;
    ProgressBar uploadProgress;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        chooseImage = findViewById(R.id.select_image);
        uploadImage = findViewById(R.id.upload_post);
        Description = findViewById(R.id.description);
        showImage = findViewById(R.id.show_image);
        uploadProgress = findViewById(R.id.upload_progress);

        chooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             openFileChooser();
            }
        });
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() !=null){
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(showImage);
        }
    }
}
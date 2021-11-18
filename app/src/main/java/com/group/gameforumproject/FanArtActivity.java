package com.group.gameforumproject;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FanArtActivity extends AppCompatActivity {

    Button logout;
    FloatingActionButton create_post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_art);

        //logout button
        logout = findViewById(R.id.btn_fanArtLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent (FanArtActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });

        create_post = findViewById(R.id.btn_createFanArtPost);

        create_post.setOnClickListener(view -> {
            Intent create_fan_art_intent = new Intent(FanArtActivity.this, CreateFanArtPost.class);
            startActivity(create_fan_art_intent);
        });



    }




}
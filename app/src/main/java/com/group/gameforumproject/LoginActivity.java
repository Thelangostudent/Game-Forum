package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * This class is the loginclass. It sends user login info to server.
 * Can also send user to the createUser page.
 *
 * @author Gruppe 2
 * @version 0.1 Aplha
 * */

public class LoginActivity extends AppCompatActivity {

    Button createUser;
    Button login;
    Button testModelViewer;
    EditText password;
    EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // On page buttons
        createUser = findViewById(R.id.btn_createUser);
        login = findViewById(R.id.btn_login);
        // user input fields
        password = findViewById(R.id.txt_Password);
        username = findViewById(R.id.txt_userName);
        testModelViewer= findViewById(R.id.ModelViewerTest);


        //sends the user to the create user page
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createUser = new Intent(LoginActivity.this, CreateUserActivity.class);
                startActivity(createUser);
            }
        });


        /**
         * This is a temporary method to get access to the rets of the app
         * use serverlogic instead for final production
         * */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });

        testModelViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                Uri modelIntent=
                Uri.parse("https://arvr.google.com/scene-viewer/1.0").buildUpon()
                        .appendQueryParameter("file", "Models/mainShipRaw.glb")
                        .appendQueryParameter("title", "ShipModel")
                        .build();
                sceneViewerIntent.setData(modelIntent);
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
                startActivity(sceneViewerIntent);
            }
        });

    }
}
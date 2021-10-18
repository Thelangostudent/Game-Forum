package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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


        //sends the user to the create user page
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createUser = new Intent(LoginActivity.this, CreateUserActivity.class);
                startActivity(createUser);
            }
        });
    }
}
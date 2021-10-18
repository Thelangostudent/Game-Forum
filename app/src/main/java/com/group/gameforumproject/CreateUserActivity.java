package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

/**
 * This class creates the user and sends info to server.
 * After user is created, they are sent back to LoginPage (to see if user works).
 *
 * @version 0.1 alpha
 * @author Gruppe 2
 *
 * */
public class CreateUserActivity extends AppCompatActivity {

    EditText password;
    EditText userName;
    Button userCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        // user input fields
        password = findViewById(R.id.createPassword);
        userName = findViewById(R.id.createPassword);

        // button that sends user data to server and sends user to login page
        userCreated = findViewById(R.id.userCreated);
    }
}
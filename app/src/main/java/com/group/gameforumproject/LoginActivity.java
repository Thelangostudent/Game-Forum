package com.group.gameforumproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Environment;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


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

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

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


        /**
         * This is a temporary method to get access to the rets of the app
         * use serverlogic instead for final production
         * */
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {


        String emailString = this.username.getText().toString().trim();
        String passwordString = this.password.getText().toString().trim();

        if (emailString.isEmpty()) {
            username.setError("Email is required!");
            username.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()) {
            username.setError("This is not a valid email!");
            username.requestFocus();
            return;
        }

        if (passwordString.isEmpty()) {
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if (passwordString.length() < 6) {
            password.setError("Minimum length is 6 character!");
            password.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(emailString, passwordString).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "User is logged in", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    Toast.makeText(LoginActivity.this, "Login Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
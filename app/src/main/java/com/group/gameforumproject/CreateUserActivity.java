package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


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
    EditText email;
    EditText username;
    Button userCreated;


    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        mAuth = FirebaseAuth.getInstance();



        // user input fields
        email = findViewById(R.id.createUserId);
        password = findViewById(R.id.createPassword);
        username = findViewById(R.id.createUserName);


        // cancel button

        Button cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(CreateUserActivity.this, LoginActivity.class);
                startActivity(cancel);
            }
        });


        // button that sends user data to server and sends user to login page
        userCreated = findViewById(R.id.userCreated);

        userCreated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();


            }
        });
    }


    private void createAccount() {

        String emailString = this.email.getText().toString().trim();
        String passwordString = this.password.getText().toString().trim();
        String usernameString = this.username.getText().toString().trim();

        if (emailString.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
            email.setError("This is not a valid email!");
            email.requestFocus();
            return;
        }

        if (usernameString.isEmpty() || usernameString.length() < 4){
            username.setError("This is not a valid username\nusername must be at least 4 character");
            username.requestFocus();
            return;
        }

        if (passwordString.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if (passwordString.length() < 6){
            password.setError("Minimum length is 6 character!");
            password.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(emailString, usernameString);

                            FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(CreateUserActivity.this, "User has been created!", Toast.LENGTH_LONG).show();
                                        Intent userCreated = new Intent(CreateUserActivity.this, LoginActivity.class);
                                        startActivity(userCreated);

                                    } else {
                                        Toast.makeText(CreateUserActivity.this, "User was not created! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(CreateUserActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


}
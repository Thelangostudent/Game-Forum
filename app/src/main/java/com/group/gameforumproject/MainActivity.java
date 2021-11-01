package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

/**
 * This class is the main activity for this project.
 * Acts as a main page with links to other parts of the App.
 * @version alpha 0.1
 * @author Gruppe 2
 *
 * */
public class MainActivity extends AppCompatActivity {

    private Button threeDModels;
    private Button fanArt;
    private Button discussion;
    private Button newsButton;
    private Button logoutButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        // page buttons
        threeDModels = findViewById(R.id.btn_3dModels);
        fanArt = findViewById(R.id.btn_FanArt);
        discussion = findViewById(R.id.btn_discussion);
        newsButton = findViewById(R.id.btn_News);
        logoutButton = findViewById(R.id.btn_bulletPointsLogout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent logoutIntent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
            }
        });

        threeDModels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent threeDmodels = new Intent(MainActivity.this, ThreeDModelActivity.class);
                startActivity(threeDmodels);
            }
        });

        fanArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fanArt = new Intent(MainActivity.this, FanArtActivity.class);
                startActivity(fanArt);
            }
        });

        discussion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent discussion = new Intent(MainActivity.this, DiscussionActivity.class);
                startActivity(discussion);
            }
        });

        newsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent news = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(news);
            }
        });
    }
}
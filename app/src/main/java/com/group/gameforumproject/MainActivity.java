package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * This class is the main activity for this project.
 * Acts as a main page with links to other parts of the App.
 * @version alpha 0.1
 * @author Gruppe 2
 *
 * */
public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        // page buttons
        ImageView fanArt = findViewById(R.id.fanart);
        ImageView discussion = findViewById(R.id.discussion);
        ImageView threeDModels = findViewById(R.id.models);
        ImageView newsButton = findViewById(R.id.news);
        ImageView logoutButton = findViewById(R.id.logout);

        Context context = this;

        Glide.with(this).load(getImage("fanart")).into(fanArt);
        Glide.with(this).load(getImage("discussion")).into(discussion);
        Glide.with(this).load(getImage("models")).into(threeDModels);
        Glide.with(this).load(getImage("news")).into(newsButton);
        Glide.with(this).load(getImage("logout")).into(logoutButton);


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

    public int getImage(String imageName) {

        int drawableResourceId = this.getResources().getIdentifier(imageName, "drawable", this.getPackageName());

        return drawableResourceId;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
    }
}
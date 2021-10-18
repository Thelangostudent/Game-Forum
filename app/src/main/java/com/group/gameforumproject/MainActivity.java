package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button threeDModels;
    private Button fanArt;
    private Button discussion;
    private Button newsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        threeDModels = findViewById(R.id.btn_3dModels);
        fanArt = findViewById(R.id.btn_FanArt);
        discussion = findViewById(R.id.btn_discussion);
        newsButton = findViewById(R.id.btn_News);

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
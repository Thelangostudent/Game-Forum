package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class FanArtDetailPage extends AppCompatActivity {
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_art_detail_page);

        backButton = findViewById(R.id.btn_backtofanPosts);
    }
}
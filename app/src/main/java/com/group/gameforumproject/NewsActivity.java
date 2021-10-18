package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/**
 * This newsClass fetches the latest news from the server
 *
 * @author Gruppe 2
 * @version 0.1 Aplha
 *
 * */
public class NewsActivity extends AppCompatActivity {

    TextView latestNewsTitle;
    TextView latestNewsDescription;
    TextView latestNewsAuthor;
    Button newsLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //fields for the news article
        latestNewsTitle = findViewById(R.id.txt_LatestNewsTitle);
        latestNewsDescription = findViewById(R.id.txt_LatestNewsDescription);
        latestNewsAuthor = findViewById(R.id.txt_latestNewsAuthor);

        newsLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent (NewsActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
            }
        });
    }
}
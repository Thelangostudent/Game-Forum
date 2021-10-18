package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * This class is the discussion page where people can post discussion threads.
 * @version 0.1 Alpha
 *
 * @author Gruppe 2
 * */
public class DiscussionActivity extends AppCompatActivity {

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        //page logout button
        logoutButton = findViewById(R.id.btn_discussionLogout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(DiscussionActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
            }
        });
    }
}
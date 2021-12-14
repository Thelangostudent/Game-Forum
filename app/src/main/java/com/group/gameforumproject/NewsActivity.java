package com.group.gameforumproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

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
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        //Creates basic youtube player view
        youTubePlayerView = findViewById(R.id.activity_main_youtubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);

        //gets and plays the game tutorial from youtube in the yt app viewer
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "twT9tdZSaaQ";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        //fields for the news article
        //latestNewsTitle = findViewById(R.id.txt_LatestNewsTitle);
        //latestNewsDescription = findViewById(R.id.txt_LatestNewsDescription);
        //latestNewsAuthor = findViewById(R.id.txt_latestNewsAuthor);

        newsLogout = findViewById(R.id.btn_newsLogout);

        newsLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent (NewsActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });
    }
}
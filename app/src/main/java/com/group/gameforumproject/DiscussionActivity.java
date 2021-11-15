package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * This class is the discussion page where people can post discussion threads.
 * @version 0.1 Alpha
 *
 * @author Gruppe 2
 * */
public class DiscussionActivity extends AppCompatActivity {

    public static ArrayList<DiscussionPost> DISCUSSIONLIST;

    public ArrayList<DiscussionPost> discussionPostList = new ArrayList<>();
    public ArrayList<Comments> commentList = new ArrayList<>();
    public User user;

    private RecyclerView recyclerView;
    private DiscussionPostAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton addNewDiscssionPost;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        //page logout button
        logoutButton = findViewById(R.id.btn_discussionLogout);

        addNewDiscssionPost = findViewById(R.id.btn_addDiscussionPost);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(DiscussionActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });

        // sets up a globally accessible version of the discussionPosts.
        DISCUSSIONLIST = discussionPostList;

        recyclerView = findViewById(R.id.discussionRecyclerview);

        adapter = new DiscussionPostAdapter(discussionPostList,DiscussionActivity.this);

        addNewDiscssionPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newDiscussionPost = new Intent(DiscussionActivity.this, CreateDiscussionPost.class);
                startActivity(newDiscussionPost);
            }
        });

        //fill list with dummy entries, implementing with firebase later.
        dummyPostEntries();
    }


    private void dummyPostEntries()
    {
        DiscussionPost d1 = new DiscussionPost(0,"When is the game releasing?","Just wanted to know k thnx", commentList, user);
        DiscussionPost d2 = new DiscussionPost(1, "LOL looking forward to your game", "What the title said", commentList, user);
        DiscussionPost d3 = new DiscussionPost(2, "I must Alpha test!", "Am on my sigma grind, need to test a game", commentList, user);

        discussionPostList.add(d1);
        discussionPostList.add(d2);
        discussionPostList.add(d3);

    }
}
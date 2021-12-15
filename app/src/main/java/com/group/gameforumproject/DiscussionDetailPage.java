package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DiscussionDetailPage extends AppCompatActivity {

    private RecyclerView commentRecycler;
    public ArrayList<Comments> commentList = new ArrayList<>();
    public ArrayList <DiscussionPost> discussionList = DiscussionActivity.DISCUSSIONLIST;

    private TextView discussionDetailTitle;
    private TextView discussionDetailDescription;
    private TextView discussionDetailUserName;


    int id; // specific post ID.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_detail_page);

        commentRecycler = findViewById(R.id.discussionRecyclerview);

        discussionDetailTitle = findViewById(R.id.txt_discussionDetailPageTitle);
        discussionDetailUserName = findViewById(R.id.txt_detail_user_name);
        discussionDetailDescription = findViewById(R.id.txt_detailDiscussionDescription);

        Intent postIntent = getIntent();
        id = postIntent.getIntExtra("id",-1);

        DiscussionPost discussionPost = null;

        for (DiscussionPost p: discussionList)
        {
            if (p.getId() == id)
            {
                discussionPost = p;
            }
        }

        discussionDetailTitle.setText(discussionPost.getTitle());
        discussionDetailDescription.setText(discussionPost.getDescription());
        discussionDetailUserName.setText(discussionPost.getUser().getUsername());




    }
}
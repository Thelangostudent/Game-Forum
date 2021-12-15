package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class DiscussionDetailPage extends AppCompatActivity {

    private RecyclerView commentRecycler;
    public ArrayList<Comments> commentList = new ArrayList<>();

    private TextView discussionDetailTitle;
    private TextView discussionDetailDescription;
    private TextView discussionDetailUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_detail_page);





    }
}
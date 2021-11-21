package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateDiscussionPost extends AppCompatActivity {

    Button returnButton;

    Button postButton;

    EditText discussionTitle;
    EditText discussionDescriptionMultiLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_discussion_post);

        returnButton = findViewById(R.id.btn_createDiscussionPostReturn);
        discussionTitle = findViewById(R.id.txt_TitleOfNewDiscussion);
        discussionDescriptionMultiLine = findViewById(R.id.txt_DescriptionNewDiscussionPost);
        postButton = findViewById(R.id.btn_DiscussionPostButtonPostNewpost);




    }
}
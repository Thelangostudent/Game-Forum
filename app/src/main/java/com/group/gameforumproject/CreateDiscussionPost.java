package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateDiscussionPost extends AppCompatActivity {

    Button returnButton;

    Button postButton;

    DiscussionPost newPost;

    User currentUser;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    ArrayList<Comments> commentsForThisPost;


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


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/");

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(CreateDiscussionPost.this, DiscussionActivity.class);
                startActivity(mainIntent);
            }
        });

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String discissionTitle = discussionTitle.getText().toString();
                String discussionContent = discussionDescriptionMultiLine.getText().toString();

                int newPostId = DiscussionActivity.DISCUSSIONLIST.size();
                commentsForThisPost = new ArrayList<Comments>();

                String username = firebaseUser.getDisplayName();

                String eMail = firebaseUser.getEmail();

                currentUser = new User(eMail,username);

                newPost = new DiscussionPost(newPostId, discissionTitle, discussionContent,commentsForThisPost,currentUser);

                addPost(newPost);
            }
        });



    }

   // This method will add the Post to the discussion posts list in firebase
    private void addPost(DiscussionPost discussionPost) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("DiscussionPostAlmostFinished").push();

        // get post unique ID and update post key


        // add post data to firebase database

        myRef.setValue(discussionPost).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //showMessage("Post Added successfully");

                Intent mainIntent = new Intent(CreateDiscussionPost.this, DiscussionActivity.class);
                startActivity(mainIntent);
            }
        });
    }


}
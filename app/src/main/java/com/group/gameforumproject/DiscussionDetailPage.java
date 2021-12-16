package com.group.gameforumproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DiscussionDetailPage extends AppCompatActivity {

    public static ArrayList<Comments> COMMENTLIST;

    private RecyclerView commentRecycler;
    private CommentsAdapter commentsAdapter;
    public ArrayList<Comments> commentList = new ArrayList<>();
    public ArrayList <DiscussionPost> discussionList = DiscussionActivity.DISCUSSIONLIST;

    private TextView discussionDetailTitle;
    private TextView discussionDetailDescription;
    private TextView discussionDetailUserName;
    private RecyclerView.LayoutManager layoutManager;
    private EditText commentInput;
    private Button postCommentButton;
    private User currentUser;
    private Comments newComment;

    FirebaseDatabase remoteDataBase;
    DatabaseReference referenceData;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;


    int id; // specific post ID.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_detail_page);

        commentRecycler = findViewById(R.id.discusssioncommentRecyclerView);

        COMMENTLIST = commentList;

        discussionDetailTitle = findViewById(R.id.txt_discussionDetailPageTitle);
        discussionDetailUserName = findViewById(R.id.txt_detail_user_name);
        discussionDetailDescription = findViewById(R.id.txt_detailDiscussionDescription);
        postCommentButton = findViewById(R.id.btn_postDiscussionComment);
        commentInput = findViewById(R.id.txt_commentDiscussionInput);

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


        layoutManager = new LinearLayoutManager(this);
        commentRecycler.setLayoutManager(layoutManager);

        commentsAdapter = new CommentsAdapter(commentList,DiscussionDetailPage.this);

        commentRecycler.setHasFixedSize(true);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/");

        postCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String commentContent = commentInput.getText().toString();

                int newCommentId = COMMENTLIST.size();


                String username = firebaseUser.getDisplayName();

                String eMail = firebaseUser.getEmail();

                currentUser = new User(eMail,username);

                newComment = new Comments(newCommentId,id,currentUser,commentContent);

                addComment(newComment);

            }
        });




        remoteDataBase = FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/");
        referenceData = remoteDataBase.getReference("DiscussionPostCommentsTest");
        referenceData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {



                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {

                    Comments post = postsnap.getValue(Comments.class);
                    COMMENTLIST.add(post);
                    if (post.getCommentid() == id)
                    {
                        commentList.add(post);
                    }



                }





                commentRecycler.setAdapter(commentsAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    // adds comment and pushes it to firebase.
    private void addComment(Comments commentPost) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("DiscussionPostCommentsTest").push();

        // get post unique ID and update post key


        // add post data to firebase database

        myRef.setValue(commentPost).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //showMessage("Post Added successfully");


            }
        });
    }
}
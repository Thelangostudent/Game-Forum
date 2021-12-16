package com.group.gameforumproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

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

    FirebaseDatabase remoteDataBase;
    DatabaseReference referenceData;


    int id; // specific post ID.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_detail_page);

        commentRecycler = findViewById(R.id.discusssioncommentRecyclerView);



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


        layoutManager = new LinearLayoutManager(this);
        commentRecycler.setLayoutManager(layoutManager);

        commentsAdapter = new CommentsAdapter(commentList,DiscussionDetailPage.this);

        commentRecycler.setHasFixedSize(true);





        remoteDataBase = FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/");
        referenceData = remoteDataBase.getReference("DiscussionPostCommentsTest");
        referenceData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {

                    Comments post = postsnap.getValue(Comments.class);

                    if (post.getPostId() == id)
                    commentList.add(post);



                }



                COMMENTLIST = commentList;

                commentRecycler.setAdapter(commentsAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
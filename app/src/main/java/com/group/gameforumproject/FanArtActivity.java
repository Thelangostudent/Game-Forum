package com.group.gameforumproject;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FanArtActivity extends AppCompatActivity {

    Button logout;
    FloatingActionButton create_post;
    RecyclerView fanArtPostRecyclerView ;
    FanArtPostAdapter fanArtPostAdapter ;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference ;
    List<FanArtPost> fanArtPostList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_art);

        //logout button
        logout = findViewById(R.id.btn_fanArtLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent (FanArtActivity.this, MainActivity.class);
                startActivity(logoutIntent);
            }
        });


        // create post button sends you to create fan art page
        create_post = findViewById(R.id.btn_createFanArtPost);

        create_post.setOnClickListener(view -> {
            Intent create_fan_art_intent = new Intent(FanArtActivity.this, CreateFanArtPost.class);
            startActivity(create_fan_art_intent);
        });



    }


    //when activity starts posts from database gets added to recyclerview
    @Override
    public void onStart() {
        super.onStart();

        // Get List Posts from the database
        fanArtPostRecyclerView  = findViewById(R.id.fanArtRecyclerview);
        fanArtPostRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fanArtPostRecyclerView.setHasFixedSize(true);
        firebaseDatabase = FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = firebaseDatabase.getReference("Posts");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            //everytime database changes (for stuff involving fan art posts and not other children of root), code inside runs
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                fanArtPostList = new ArrayList<>();
                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {

                    FanArtPost post = postsnap.getValue(FanArtPost.class);
                    fanArtPostList.add(post) ;



                }

                fanArtPostAdapter = new FanArtPostAdapter(FanArtActivity.this,fanArtPostList);
                fanArtPostRecyclerView.setAdapter(fanArtPostAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w("FanArtActivity", databaseError.toException());
            }
        });



    }




}
package com.group.gameforumproject;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CreateFanArtPost extends AppCompatActivity {


    private static final int PReqCode = 2 ;
    ActivityResultLauncher<String> getContent;
    Uri pickedImgUri;
    ImageView ImgAddPhoto, ImgUserPhoto;
    FirebaseUser currentUser;
    FirebaseAuth mAuth;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_fan_post);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        ImgAddPhoto = findViewById(R.id.post_creation_image);
        getContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        pickedImgUri = result;
                        ImgAddPhoto.setImageURI(pickedImgUri);
                    }
                });


        ImgAddPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // here when image clicked we need to open the gallery
                // before we open the gallery we need to check if our app have the access to user files
                // we did this before in register activity I'm just going to copy the code to save time ...

                checkAndRequestForPermission();


            }
        });

        ImgUserPhoto = findViewById(R.id.regUserPhoto);

        Glide.with(CreateFanArtPost.this).load(currentUser.getPhotoUrl()).into(ImgUserPhoto);


        //add function
        EditText title = findViewById(R.id.post_creation_title);
        EditText description = findViewById(R.id.post_creation_description);

        addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                // we need to test all input fields (Title and description ) and post image

                if (!title.getText().toString().isEmpty()
                        && !description.getText().toString().isEmpty()
                        && pickedImgUri != null ) {

                    //everything is okey no empty or null value
                    // TODO Create Post Object and add it to firebase database
                    // first we need to upload post Image
                    // access firebase storage
                    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("blog_images");
                    final StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownlaodLink = uri.toString();
                                    // create post Object
                                    FanArtPost post = new FanArtPost(title.getText().toString(),
                                            description.getText().toString(),
                                            imageDownlaodLink,
                                            currentUser.getUid(),
                                            currentUser.getPhotoUrl().toString());

                                    // Add post to firebase database

                                    addPost(post);



                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // something goes wrong uploading picture

                                    showMessage(e.getMessage());




                                }
                            });


                        }
                    });








                }
                else {
                    showMessage("Please verify all input fields and choose Post Image") ;

                }



            }
        });

    }

    private void addPost(FanArtPost fanArtPost) {

        FirebaseDatabase database = FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/");
        DatabaseReference myRef = database.getReference("Posts").push();

        // get post unique ID and update post key
        String key = myRef.getKey();
        fanArtPost.setPostKey(key);


        // add post data to firebase database

        myRef.setValue(fanArtPost).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                showMessage("Post Added successfully");

                Intent mainIntent = new Intent(CreateFanArtPost.this, FanArtActivity.class);
                startActivity(mainIntent);
            }
        });





    }

    private void openGallery() {
        getContent.launch("image/*");
    }

    private void showMessage(String message) {

        Toast.makeText(CreateFanArtPost.this,message,Toast.LENGTH_LONG).show();

    }

    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(CreateFanArtPost.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(CreateFanArtPost.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(CreateFanArtPost.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(CreateFanArtPost.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();

    }
}
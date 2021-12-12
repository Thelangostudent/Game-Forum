package com.group.gameforumproject;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;


/**
 * This class creates the user and sends info to server.
 * After user is created, they are sent back to LoginPage (to see if user works).
 *
 * @version 0.1 alpha
 * @author Gruppe 2
 *
 * */
public class CreateUserActivity extends AppCompatActivity {

    EditText password;
    EditText email;
    EditText username;
    Button userCreated;


    //profile picture
    ImageView ImgUserPhoto;
    ImageView addImage;
    ActivityResultLauncher<String> getContent;
    static int PReqCode = 1;
    Uri pickedImgUri;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        mAuth = FirebaseAuth.getInstance();


        // user input fields
        email = findViewById(R.id.createUserId);
        password = findViewById(R.id.createPassword);
        username = findViewById(R.id.createUserName);


        // cancel button

        Button cancel = findViewById(R.id.cancel_button);
        cancel.setOnClickListener(view -> {
            Intent cancel1 = new Intent(CreateUserActivity.this, LoginActivity.class);
            startActivity(cancel1);
        });


        // button that sends user data to server and sends user to login page
        userCreated = findViewById(R.id.userCreated);

        userCreated.setOnClickListener(view -> createAccount());

        ImgUserPhoto = findViewById(R.id.regUserPhoto) ;
        addImage = findViewById(R.id.add_image);

        addImage.setOnClickListener(view -> {

            if (Build.VERSION.SDK_INT >= 22) {

                checkAndRequestForPermission();


            }
            else
            {
                openGallery();
            }





        });

        getContent = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        pickedImgUri = result;
                        ImgUserPhoto.setImageURI(pickedImgUri);
                    }
                });


    }







    private void createAccount() {

        String emailString = this.email.getText().toString().trim();
        String passwordString = this.password.getText().toString().trim();
        String usernameString = this.username.getText().toString().trim();

        if (emailString.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
            email.setError("This is not a valid email!");
            email.requestFocus();
            return;
        }

        if (usernameString.isEmpty() || usernameString.length() < 4){
            username.setError("This is not a valid username\nusername must be at least 4 character");
            username.requestFocus();
            return;
        }

        if (passwordString.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if (passwordString.length() < 6){
            password.setError("Minimum length is 6 character!");
            password.requestFocus();
            return;
        }

        if (pickedImgUri == null) {
            Toast.makeText(CreateUserActivity.this, "You must add a profile image!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(emailString, passwordString)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(emailString, usernameString);

                            FirebaseDatabase.getInstance("https://game-forum-backend-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
                                    .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(CreateUserActivity.this, "User has been created!", Toast.LENGTH_LONG).show();
                                        updateUserInfo( usernameString ,pickedImgUri,mAuth.getCurrentUser());

                                    } else {
                                        Toast.makeText(CreateUserActivity.this, "User was not created! Try again!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(CreateUserActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    // update user photo and name
    private void updateUserInfo(final String name, Uri pickedImgUri, final FirebaseUser currentUser) {

        // first we need to upload user photo to firebase storage and get url

        StorageReference mStorage = FirebaseStorage.getInstance().getReference().child("users_photos");
        final StorageReference imageFilePath = mStorage.child(pickedImgUri.getLastPathSegment());
        imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                // image uploaded successfully
                // now we can get our image url

                imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        // uri contain user image url


                        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .setPhotoUri(uri)
                                .build();


                        currentUser.updateProfile(profileUpdate)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            // user info updated successfully
                                            Toast.makeText(getApplicationContext(),"Register Complete",Toast.LENGTH_LONG).show();
                                            Intent userCreated = new Intent(CreateUserActivity.this, LoginActivity.class);
                                            startActivity(userCreated);
                                        }

                                    }
                                });

                    }
                });





            }
        });






    }



    //profile picture code
    private void openGallery() {
        getContent.launch("image/*");
    }

    private void checkAndRequestForPermission() {


        if (ContextCompat.checkSelfPermission(CreateUserActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(CreateUserActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(CreateUserActivity.this,"Please accept for required permission",Toast.LENGTH_SHORT).show();

            }

            else
            {
                ActivityCompat.requestPermissions(CreateUserActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }

        }
        else
            openGallery();

    }




    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESTCODE && data != null ) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData() ;
            ImgUserPhoto.setImageURI(pickedImgUri);


        }


    }*/


}
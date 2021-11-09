package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ThreeDModelActivity extends AppCompatActivity {

    Button shipModelButton;
<<<<<<< HEAD
    Button playercharacterButton;
    Button asteroid;
=======
    Button playerCharacterButton;
    Button asteroidButton;

>>>>>>> b26e14f1978ee3b29c87b828c5d025bc97a62187
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_dmodel);

        shipModelButton = findViewById(R.id.btn_IngameShipModel);
        playerCharacterButton = findViewById(R.id.btn_playerCharacterModel);
        asteroidButton = findViewById(R.id.btn_asteroid);


        shipModelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/Thelangostudent/Models/master/ShipModel/mainShipRaw.gltf"));
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
                startActivity(sceneViewerIntent);
            }
        });

        playerCharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/Thelangostudent/Models/master/ShipModel/player.gltf"));
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
                startActivity(sceneViewerIntent);
            }
        });

        asteroidButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/Thelangostudent/Models/master/ShipModel/asteroid.gltf"));
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
                startActivity(sceneViewerIntent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainpageIntent = new Intent(ThreeDModelActivity.this, MainActivity.class);
                startActivity(mainpageIntent);
            }
        });

        playercharacterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/Thelangostudent/Models/master/ShipModel/player.gltf"));
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
                startActivity(sceneViewerIntent);
            }
        });

        asteroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/Thelangostudent/Models/master/ShipModel/asteroid.gltf"));
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
                startActivity(sceneViewerIntent);
            }
        });


    }
}
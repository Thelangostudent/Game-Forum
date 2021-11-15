package com.group.gameforumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

public class ThreeDModelActivity extends AppCompatActivity {

    Button viewModelButton;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_dmodel);

        Context context = this;

        logoutButton= findViewById(R.id.btn_3dModelLogout_);
        viewModelButton= findViewById(R.id.btn_viewModelButton);

        Spinner spinner=findViewById(R.id.spinner);
        int singleItem = R.layout.spinner_dropdown_item;

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                updatePicture(context);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                System.out.println("Nothing is currently selected");
            }
        });

        ImageView previewImage=(ImageView) findViewById(R.id.previewImage);

        Glide.with(context)
                .load("https://raw.githubusercontent.com/Thelangostudent/Models/master/Pictures/none.png")
                .into(previewImage);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(context, R.array.models, singleItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        spinner.setAdapter(adapter);

        viewModelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startModelViewer();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainpageIntent = new Intent(ThreeDModelActivity.this, MainActivity.class);
                startActivity(mainpageIntent);
            }
        });
    }


    public void startModelViewer(){
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String text = mySpinner.getSelectedItem().toString();

        String selectedModel = "none";
        switch (text) {
            case "Ship":
                selectedModel = "mainShipRaw";
                break;
            case "Player":
                selectedModel = "player";
                break;
            case "Asteroid":
                selectedModel = "asteroid";
                break;
            case "Forest Village Planet":
                selectedModel = "forestVillage";
                break;
            case "Select model":
                selectedModel = "none";
                break;
        }

        if (!selectedModel.equals("none")){
            Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
            sceneViewerIntent.setData(Uri.parse("https://arvr.google.com/scene-viewer/1.0?file=https://raw.githubusercontent.com/Thelangostudent/Models/master/ShipModel/" + selectedModel + ".gltf"));
            sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
            startActivity(sceneViewerIntent);
        }
    }


    public void updatePicture(Context context){
        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        String text = mySpinner.getSelectedItem().toString();

        String imageResultURL = "none";
        switch (text) {
            case "Ship":
                imageResultURL = "shipPicture.png";
                break;
            case "Player":
                imageResultURL = "player.png";
                break;
            case "Asteroid":
                imageResultURL = "asteroid.png";
                break;
            case "Forest Village Planet":
                imageResultURL = "forestVillagePlanet.png";
                break;
            case "Select model":
                imageResultURL = "none.png";
                break;
        }

        ImageView previewImage=(ImageView) findViewById(R.id.previewImage);

        Glide.with(context)
                .load("https://raw.githubusercontent.com/Thelangostudent/Models/master/Pictures/" + imageResultURL)
                .into(previewImage);
    }
}
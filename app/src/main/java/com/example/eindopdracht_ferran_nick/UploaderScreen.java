package com.example.eindopdracht_ferran_nick;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UploaderScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploader_screen);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String prefference = sharedpreferences.getString("check", "empty");
        if(prefference == "true"){
            getSupportActionBar().setCustomView(R.layout.custom_toolbar2);

        } else{
            getSupportActionBar().setCustomView(R.layout.custom_toolbar);

        }
        ImageView imageView = (ImageView) findViewById(R.id.home_icon);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UploaderScreen.this, MainActivity.class);
                startActivity(intent);
                finish();


            }
        });
        ImageView imageView1 = (ImageView) findViewById(R.id.settings_icon);
        imageView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploaderScreen.this, SettingsScreen.class);
                startActivity(intent);
                finish();

            }
        });
        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.ToMain);
        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UploaderScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
    @Override
    public void onBackPressed() {

    }
}
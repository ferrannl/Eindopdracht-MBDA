package com.example.eindopdracht_ferran_nick;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE}, 1);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        Intent intent = getIntent();
        String message = intent.getStringExtra(Intent.EXTRA_TEXT);
        if(message != null){

            writeFileOnInternalStorage(message);

            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        String prefference = sharedpreferences.getString("check", "empty");
        if(prefference == "true"){
            getSupportActionBar().setCustomView(R.layout.custom_toolbar2);

        } else{
            getSupportActionBar().setCustomView(R.layout.custom_toolbar);

        }
        ImageView imageView1 = (ImageView) findViewById(R.id.settings_icon);
        imageView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsScreen.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private List<String> readImgurList() {

        String yourFilePath = getApplicationContext().getFilesDir() + "/imgurs/" + "imgur-links.txt";
        File yourFile = new File( yourFilePath );
        List<String> imgurList = new ArrayList<>();

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        BufferedReader in = null;

        try {
            in = new BufferedReader(new FileReader(yourFile));
            while ((line = in.readLine()) != null) stringBuilder.append(line);


        } catch (FileNotFoundException e) {
            imgurList.add("You have no links yet!");

        } catch (IOException e) {
            return null;
        }
        String[] linkList = stringBuilder.toString().split(",");

        for (String link : linkList) {
            imgurList.add(link);
        }
        if(in != null) {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return imgurList;
    }

    public void writeFileOnInternalStorage(String message){
        List<String> linkList = readImgurList();
        linkList.add(message);
        String linkListString = "";
        if(linkList.get(0) != "You have no links yet!") {
            for (String link : linkList) {
                linkListString = linkListString + link + ",";
            }
        }else{
            linkListString = message;
        }
        StringBuffer sb = new StringBuffer(linkListString);
        if(linkList.get(0) != "You have no links yet!") {
            sb.deleteCharAt(sb.length() - 1);
        }
        String sBody = sb.toString();
        String sFileName = "imgur-links.txt";
        Context mcoContext = getApplicationContext();
        File dir = new File(mcoContext.getFilesDir(), "imgurs");
        if(!dir.exists()){
            dir.mkdir();
        }

        try {
            File gpxfile = new File(dir, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {

    }

}
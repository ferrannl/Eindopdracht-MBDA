package com.example.eindopdracht_ferran_nick;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

public class OverviewFragmentUploaderScreen extends Fragment {
ImageView imageView;
View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_overview_uploader_screen, container, false);
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.choose);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(intent, 0);

            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0)
        {
            if(data.getExtras() != null)
            {
                Bitmap photo = (Bitmap) data.getExtras().get("data");

                ImgurUploader imgurUploader = new ImgurUploader();
                String url = "https://api.imgur.com/3/image";
                String body = convertImgToBase64(photo);
                String result = "";
                try {
                    result = imgurUploader.execute(url, body).get();
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                    Fragment myFragment = new DetailFragmentUploaderScreen();
                    myFragment.setArguments(new Bundle());
                    myFragment.getArguments().putString("url", result);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.detailFragmentUploaderScreen, myFragment).addToBackStack(null).commit();

                    writeFileOnInternalStorage(result);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
            }
        }
    }
    public String convertImgToBase64(Bitmap bitmap){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        try {
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
    public List<String> readImgurList(){

        String yourFilePath = view.getContext().getFilesDir() + "/imgurs/" + "imgur-links.txt";
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
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
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
        Context mcoContext = view.getContext();
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
}

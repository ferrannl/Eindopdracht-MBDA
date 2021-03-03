package com.example.eindopdracht_ferran_nick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class DetailFragment extends Fragment {

    ImageDownloader downloader;

    public DetailFragment() {
    }

    ImageView imageView;
    View view;
    Bitmap currentImage;
    public static String url;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       view = inflater.inflate(R.layout.fragment_detail, container, false);
        imageView = view.findViewById(R.id.imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);
        url = "1";
        if(getArguments() != null){
            url = getArguments().getString("url");

            new ImageDownloader(imageView).execute(url);

        }
        Button button = (Button) view.findViewById(R.id.share);
        button.setOnClickListener(null);
        Log.d("outside click",url);
        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("inside click",url);
                shareImage(view);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        Button button = (Button) view.findViewById(R.id.share);
        button.setOnClickListener(null);
        super.onDestroyView();
    }

    public void shareImage(View view){


        Intent share = new Intent(Intent.ACTION_SEND);
        share.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share.setType("text/plain");
        share.putExtra(android.content.Intent.EXTRA_TEXT, url);
        startActivity(Intent.createChooser(share,"Share via"));

    }
}
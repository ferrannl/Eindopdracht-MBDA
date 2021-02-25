package com.example.eindopdracht_ferran_nick;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailFragment extends Fragment {

    ImageDownloader downloader;

    public DetailFragment() {
    }

    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        imageView = view.findViewById(R.id.imageView);

        if(getArguments() != null){
            String url = getArguments().getString("url");
            new ImageDownloader(imageView).execute(url);

        }

        return view;
    }

    public void setImage(String imgurLink) {
        URL url = null;
        try {
            url = new URL(imgurLink);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView.setImageBitmap(bmp);

    }
}
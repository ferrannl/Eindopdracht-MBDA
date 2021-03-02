package com.example.eindopdracht_ferran_nick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import java.io.File;
import java.io.FileOutputStream;

public class DetailFragment extends Fragment {

    ImageDownloader downloader;

    public DetailFragment() {
    }

    ImageView imageView;
    Bitmap currentImage;

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
        Button button = (Button) view.findViewById(R.id.share);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                shareImage(view);
            }
        });

        return view;
    }


    public void shareImage(View view){

        Toast toast=Toast.makeText(view.getContext(),"Hello Javatpoint",Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();

        View content = imageView;
        content.setDrawingCacheEnabled(true);

        Bitmap bitmap = content.getDrawingCache();


        File root = Environment.getExternalStorageDirectory();
        File cachePath = new File(root.getAbsolutePath() + "/DCIM/ImageFromAwesomeApp.jpg");
        try {
            cachePath.createNewFile();
            FileOutputStream ostream = new FileOutputStream(cachePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
            ostream.flush();
            ostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share.setType("image/*");
        share.putExtra(Intent.EXTRA_STREAM, FileProvider.getUriForFile(view.getContext(), view.getContext().getApplicationContext().getPackageName() + ".provider", cachePath));
        startActivity(Intent.createChooser(share,"Share via"));

    }
}
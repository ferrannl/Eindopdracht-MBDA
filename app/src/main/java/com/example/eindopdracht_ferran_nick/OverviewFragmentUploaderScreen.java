package com.example.eindopdracht_ferran_nick;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.ByteArrayOutputStream;

public class OverviewFragmentUploaderScreen extends Fragment {
ImageView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_overview_uploader_screen, container, false);
        imageView = view.findViewById(R.id.imageView2);
        Button button = view.findViewById(R.id.choose);
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
            if(data != null)
            {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                photo = Bitmap.createScaledBitmap(photo, 80, 80, false);
                imageView.setImageBitmap(photo);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream .toByteArray();
                String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                ImgurUploader downloadInfoOfWeather = new ImgurUploader();

                String url = "https://api.imgur.com/3/image";
                String body = encoded;
                Log.e("Response1", encoded);


                downloadInfoOfWeather.execute(url, body);

            }
            else{
            }
        }
    }
}

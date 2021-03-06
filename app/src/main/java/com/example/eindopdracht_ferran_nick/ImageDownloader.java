package com.example.eindopdracht_ferran_nick;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public ImageDownloader(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
            in.close();
        } catch (FileNotFoundException e) {
        return null;
        }
        catch (Exception e) {
            Log.e("Error-Uploader", e.toString());
            if(e.getMessage().contains("no protocol") || e.getMessage().toLowerCase().contains("unable to resolve host") || e.toString() == "java.io.FileNotFoundException: " + urldisplay){
                return null;
            }
            e.printStackTrace();
        }

        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        if(result != null){
            bmImage.setImageBitmap(result);

        }
    }
}
package com.example.eindopdracht_ferran_nick;


import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.Buffer;

public class ImgurUploader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty ("Authorization", "Client-ID 749896d77f12715");
            connection.setRequestMethod("POST");
           connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Accept", "*/*");

            connection.setDoOutput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            String data = URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(params[1], "UTF-8");
            writer.write(data);

            writer.close();

            connection.connect();
           BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
             StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            // Response: 400

            Log.e("Response", sb.toString());

        } catch (Exception e) {
            Log.e(e.toString(), "Something with request");
        }

        return null;
    }
}

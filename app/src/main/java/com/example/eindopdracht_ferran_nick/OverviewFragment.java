package com.example.eindopdracht_ferran_nick;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment {

    public OverviewFragment() {
        // Required empty public constructor
    }

    interface OnClickListener{
        void onItemSelected(String pokemon);
    }
    OnClickListener listener;
    View view;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_overview, container, false);

        List<String> imgurList = readImgurList();


        CustomAdapter adapter = new CustomAdapter(imgurList);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        FloatingActionButton button = (FloatingActionButton ) view.findViewById(R.id.addImg);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent menuIntent = new Intent(view.getContext(), UploaderScreen.class);
                startActivity(menuIntent);
            }
        });

        return view;
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




        return imgurList;
    }
}
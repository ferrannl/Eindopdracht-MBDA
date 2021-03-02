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

import java.util.ArrayList;
import java.util.List;

public class OverviewFragment extends Fragment {

    interface OnClickListener{
        void onItemSelected(String pokemon);
    }
    OnClickListener listener;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        List<String> imgurList = new ArrayList<>();
        imgurList.add("https://i.imgur.com/xs8hnaa.jpeg");
        imgurList.add("https://i.imgur.com/nm0JRmI.jpeg");
        imgurList.add("https://web.tue.nl/cursor/internet/jaargang50/cursor33/student/images/s_sleutel3.jpg");

        CustomAdapter adapter = new CustomAdapter(imgurList);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        Button button = (Button) view.findViewById(R.id.addImg);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent menuIntent = new Intent(view.getContext(), UploaderScreen.class);
                startActivity(menuIntent);
            }
        });

        return view;
    }

}
package com.example.eindopdracht_ferran_nick;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DetailFragmentUploaderScreen extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_uploader_screen, container, false);

        if(getArguments() != null){
            String url = getArguments().getString("url");
            TextView textView = (TextView) view.findViewById(R.id.resultPost);
            textView.setText(url);
        }
        FloatingActionButton button = (FloatingActionButton) view.findViewById(R.id.ToMain);
        button.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}

package com.example.eindopdracht_ferran_nick;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<ImgurViewHolder> {

    List<String> imgurList;
    CustomAdapter(List<String> imgurList){

        this.imgurList = imgurList;
    }
    @NonNull
    @Override
    public ImgurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.imgur_item, parent, false);

        ImgurViewHolder imgurViewHolder = new ImgurViewHolder(view);
        DetailFragment detailFragment = new DetailFragment();
        imgurViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Fragment myFragment = new DetailFragment();
                myFragment.setRetainInstance(true);
                myFragment.setArguments(new Bundle());
                myFragment.getArguments().putString("url", ((TextView) view.findViewById(R.id.textView)).getText().toString());
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.detailFragment, myFragment).addToBackStack(null).commit();
            }
        });
        return new ImgurViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgurViewHolder holder, int position) {
        String url = imgurList.get(position);
        holder.textView.setText(url);
    }


    @Override
    public int getItemCount() {
        return imgurList.size();
    }
}

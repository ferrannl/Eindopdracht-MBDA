package com.example.eindopdracht_ferran_nick;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImgurViewHolder extends RecyclerView.ViewHolder {
    TextView textView;

    public ImgurViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.textView);
    }

}
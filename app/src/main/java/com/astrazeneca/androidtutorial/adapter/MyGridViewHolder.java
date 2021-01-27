package com.astrazeneca.androidtutorial.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.astrazeneca.androidtutorial.R;

public class MyGridViewHolder extends RecyclerView.ViewHolder {

ImageView imgIcon;
TextView txtTitle;


public MyGridViewHolder(View view) {

    super(view);
    imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
    txtTitle = (TextView) view.findViewById(R.id.txtTitle);
}
}

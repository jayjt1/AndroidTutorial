package com.astrazeneca.androidtutorial.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.astrazeneca.androidtutorial.R;

public class MyListViewHolder extends RecyclerView.ViewHolder {

ImageView imgIcon;
TextView txtDesc;
TextView txtTitle;
TextView txtTime;
TextView txtNotiCount;

public MyListViewHolder(View view) {

    super(view);

    imgIcon = (ImageView) view.findViewById(R.id.imgIcon);
    txtDesc = (TextView) view.findViewById(R.id.txtDesc);
    txtTitle = (TextView) view.findViewById(R.id.txtTitle);
    txtTime = (TextView) view.findViewById(R.id.txtTime);
    txtTime = (TextView) view.findViewById(R.id.txtTime);
    txtNotiCount = (TextView) view.findViewById(R.id.txtNotiCount);
}

}

package com.astrazeneca.androidtutorial.adapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.astrazeneca.androidtutorial.R;

import java.util.ArrayList;
import java.util.Arrays;

public class WhatsappActivity extends AppCompatActivity {

    RecyclerView recyclerWhatsApp;
//    WhatsappListAdapter listAdapter;
    WhatsappGridAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_whatsapp);
        recyclerWhatsApp = (RecyclerView) findViewById(R.id.recycleWhatsapp);


        // ListView

      //  RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        recyclerWhatsApp.setLayoutManager(mLayoutManager);

        //        listAdapter = new WhatsappListAdapter(this, names);
//
//        recyclerWhatsApp.setAdapter(listAdapter);

        // GridView

        recyclerWhatsApp.setLayoutManager(new GridLayoutManager(this, 4));

        ArrayList<String> names = new ArrayList<String>(
                Arrays.asList("Saket Newaskar", "Jayant Tiwari", "Imran Khan",
                        "Pallavi Patil", "Lokesh Kuruva", "Tejasvi Kadam", "Kajal Gaikwad", "Vaibhav Keware"));

        gridAdapter = new WhatsappGridAdapter(this, names);
        recyclerWhatsApp.setAdapter(gridAdapter);

    }

}

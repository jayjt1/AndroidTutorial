package com.astrazeneca.androidtutorial.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.astrazeneca.androidtutorial.R;

public class JobIntentActivity extends AppCompatActivity {

    Button btnFetch;
    TextView txtTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnFetch = (Button) findViewById(R.id.btnFetch);

        IntentFilter mFilter = new IntentFilter("DOWNLOAD_COMPLETE");
        registerReceiver(mBroadcastReceiver, mFilter);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(JobIntentActivity.this, "Button Clicked", Toast.LENGTH_LONG).show();

                Intent jobIntent = new Intent(JobIntentActivity.this, MyIntentService.class);
                MyJobIntentService.enqueueWork(JobIntentActivity.this, jobIntent);
            }
        });
    }

    @Override
    protected void onPause() {

        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String responseString = intent.getStringExtra("title");
            txtTitle.setText(responseString);
        }
    };
}

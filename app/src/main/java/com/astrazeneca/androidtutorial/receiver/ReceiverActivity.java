package com.astrazeneca.androidtutorial.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.astrazeneca.androidtutorial.R;

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_network);
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter mFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        registerReceiver(broadcastReceiver, mFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(broadcastReceiver);

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(ReceiverActivity.this, "Flight mode status changed", Toast.LENGTH_LONG).show();
        }
    };
}

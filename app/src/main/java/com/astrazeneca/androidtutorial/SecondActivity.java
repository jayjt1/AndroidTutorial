package com.astrazeneca.androidtutorial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView txtMsgReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        String receivedData = getIntent().getStringExtra("msg");
        txtMsgReceived = findViewById(R.id.txtMsgReceived);
        txtMsgReceived.setText(receivedData);

    }
}

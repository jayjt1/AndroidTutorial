package com.astrazeneca.androidtutorial.services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.astrazeneca.androidtutorial.FirstActivity;
import com.astrazeneca.androidtutorial.R;


public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnStart, btnStop,btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnNext =  findViewById(R.id.btnNext);

        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }
    public void onClick(View src) {
        switch (src.getId()) {
            case R.id.btnStart:

                startService(new Intent(this, MyService.class));

                break;
            case R.id.btnStop:

                stopService(new Intent(this, MyService.class));
                break;
            case R.id.btnNext:

                Intent nextIntent = new Intent(this, FirstActivity.class);
                startActivity(nextIntent);
                break;
        }
    }
}
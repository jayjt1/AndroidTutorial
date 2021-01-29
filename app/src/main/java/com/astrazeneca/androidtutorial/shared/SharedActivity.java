package com.astrazeneca.androidtutorial.shared;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.astrazeneca.androidtutorial.R;

public class SharedActivity extends AppCompatActivity implements View.OnClickListener{

    EditText edtMsg;
    TextView txtMsg;
    Button btnSetData;
    Button btnGetData;

    SharedPreferences sharedPreferences;
    int REQUEST_PHONE_CALL = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared);

        ActivityCompat.requestPermissions(SharedActivity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        edtMsg = (EditText) findViewById(R.id.edtMsg);
        txtMsg = (TextView) findViewById(R.id.txtMsg);

        btnSetData = (Button) findViewById(R.id.btnSetData);
        btnGetData = (Button) findViewById(R.id.btnGetData);

        btnSetData.setOnClickListener(this);
        btnGetData.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnSetData) {

            if (ActivityCompat.checkSelfPermission(SharedActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            } else {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0377778888"));
                startActivity(callIntent);
            }

//            if (!edtMsg.getText().toString().trim().isEmpty()) {
//
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("android", edtMsg.getText().toString().trim());
//                editor.apply();
//            }

        } else if (view.getId() == R.id.btnGetData) {

            String retrivedData = sharedPreferences.getString("android", "");

            if (!retrivedData.trim().isEmpty()) {
                txtMsg.setText(retrivedData);
            } else {
                txtMsg.setText("");
                Toast.makeText(this, "No Data Found", Toast.LENGTH_LONG).show();
            }

        }

    }
}

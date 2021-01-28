package com.astrazeneca.androidtutorial.databases;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.astrazeneca.androidtutorial.R;

import java.util.List;

public class PersonActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = PersonActivity.class.getSimpleName();

    EditText edtFirstName;
    EditText edtLastName;

    Button btnAddData, btnGetData;

    SQLiteDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_person);

        dbHelper = new SQLiteDBHelper(this);

        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);

        btnAddData = (Button) findViewById(R.id.btnAddData);
        btnGetData = (Button) findViewById(R.id.btnGetData);

        btnAddData.setOnClickListener(this);
        btnGetData.setOnClickListener(this);

        Log.d(TAG, "onCreate is Called");
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnAddData) {

            String firstName = edtFirstName.getText().toString();
            String lastName = edtLastName.getText().toString();

            if (validate(firstName, lastName)) {
                Person person = new Person();
                person.setFirst_name(firstName);
                person.setLast_name(lastName);
                dbHelper.addPerson(person);

                Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_LONG).show();
            }

        } else if (view.getId() == R.id.btnGetData) {

            List<Person> personList = dbHelper.getAllPersons();

            Toast.makeText(this, "Data Added Successfully", Toast.LENGTH_LONG).show();

            for (Person person : personList) {

                Log.d(TAG, person.getFirst_name());
                Log.d(TAG, person.getLast_name());

            }
        }

    }

    private boolean validate(String firstName, String lastName) {

        if (!firstName.isEmpty() && !lastName.isEmpty()) {

            return true;
        } else {
            Toast.makeText(this, "Please enter the valid values.", Toast.LENGTH_LONG).show();
            return false;
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.d(TAG, "onStart is Called");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Log.d(TAG, "onResume is Called");
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        Log.d(TAG, "onRestart is Called");
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.d(TAG, "onPause is Called");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d(TAG, "onStop is Called");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d(TAG, "onDestroy is Called");
//    }
}

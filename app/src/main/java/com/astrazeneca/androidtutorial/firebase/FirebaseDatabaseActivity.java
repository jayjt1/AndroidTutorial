package com.astrazeneca.androidtutorial.firebase;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.astrazeneca.androidtutorial.R;
import com.astrazeneca.androidtutorial.network.ApiService;
import com.astrazeneca.androidtutorial.network.NetworkApi;
import com.astrazeneca.androidtutorial.network.model.Company;
import com.astrazeneca.androidtutorial.network.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FirebaseDatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database;
    DatabaseReference myRef;

    Button btnUsersData, btnCompanyData;
    String TAG = FirebaseDatabaseActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        btnUsersData = (Button) findViewById(R.id.btnUsersData);
        btnCompanyData = (Button) findViewById(R.id.btnCompanyData);
        btnUsersData.setOnClickListener(this);
        btnCompanyData.setOnClickListener(this);

        // Write a message to the database
         database = FirebaseDatabase.getInstance("https://androidtutorial-90ee1-default-rtdb.firebaseio.com/");
         myRef = database.getReference("Users");
         myRef.child("Name").setValue("Jayant");
         myRef.child("Address").setValue("Mumbai");
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnUsersData) {

            Retrofit mRetrofit = NetworkApi.getClient();
            ApiService apiService = mRetrofit.create(ApiService.class);

            Call<User> users = apiService.getFirebaseUserData();

            users.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {

                    if (response.isSuccessful()) {

                        Log.d(TAG, "Name :" + response.body().getName());
                        Log.d(TAG, "Address :" + response.body().getAddress());
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });

        } else if (view.getId() == R.id.btnCompanyData) {


            Retrofit mRetrofit = NetworkApi.getClient();
            ApiService apiService = mRetrofit.create(ApiService.class);
            Call<Company> company = apiService.getFirebaseCompanyData();

            company.enqueue(new Callback<Company>() {
                @Override
                public void onResponse(Call<T> call, Response<Company> response) {

                    response.message().equals("User")
                    if (response.isSuccessful()) {

                        Log.d(TAG, "Company :" + response.body().getCompany());
                        Log.d(TAG, "Address :" + response.body().getAddress());
                    }
                }

                @Override
                public void onFailure(Call<Company> call, Throwable t) {

                }
            });

        }
    }
}

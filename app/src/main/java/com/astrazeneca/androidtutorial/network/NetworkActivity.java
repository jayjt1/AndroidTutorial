package com.astrazeneca.androidtutorial.network;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.astrazeneca.androidtutorial.R;
import com.astrazeneca.androidtutorial.network.model.DummyData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkActivity extends AppCompatActivity implements  View.OnClickListener {

    String TAG = NetworkActivity.class.getSimpleName();

    Button btnFetch;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_network);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnFetch = (Button) findViewById(R.id.btnFetch);

        btnFetch.setOnClickListener(this);

        Log.d(TAG, "onCreate is Called");
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btnFetch) {

            ApiService apiService = NetworkApi.getClient().create(ApiService.class);

            Call<DummyData> dummyData = apiService.getDOTOData();

            dummyData.enqueue(new Callback<DummyData>() {
                @Override
                public void onResponse(Call<DummyData> call, Response<DummyData> response) {
                    Log.d("Response getTitle",response.body().getTitle());
                    Log.d("Response getId","" + response.body().getId());
                    Log.d("Response getUserid","" + response.body().getUserid());
                    Log.d("Response isCompleted","" + response.body().isCompleted());

                    txtTitle.setText(response.body().getTitle());
                }

                @Override
                public void onFailure(Call<DummyData> call, Throwable t) {

                }
            });

        }

    }
}

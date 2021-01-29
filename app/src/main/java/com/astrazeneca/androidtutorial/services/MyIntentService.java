package com.astrazeneca.androidtutorial.services;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.astrazeneca.androidtutorial.R;
import com.astrazeneca.androidtutorial.Util;
import com.astrazeneca.androidtutorial.network.ApiService;
import com.astrazeneca.androidtutorial.network.NetworkApi;
import com.astrazeneca.androidtutorial.network.model.DummyData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

        ApiService apiService = NetworkApi.getClient().create(ApiService.class);

        Call<DummyData> dummyData = apiService.getDOTOData();

        dummyData.enqueue(new Callback<DummyData>() {
            @Override
            public void onResponse(Call<DummyData> call, Response<DummyData> response) {

                String title = response.body().getTitle();

                Intent broadcastIntent = new Intent();
                broadcastIntent.setAction("DOWNLOAD_COMPLETE");
                broadcastIntent.putExtra("title", title);
                sendBroadcast(broadcastIntent);
            }

            @Override
            public void onFailure(Call<DummyData> call, Throwable t) {

            }
        });
    }
}

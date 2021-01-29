package com.astrazeneca.androidtutorial.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

import com.astrazeneca.androidtutorial.network.ApiService;
import com.astrazeneca.androidtutorial.network.NetworkApi;
import com.astrazeneca.androidtutorial.network.model.DummyData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyJobIntentService extends JobIntentService {

    private static final int JOB_ID = 1;

    public static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyJobIntentService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

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

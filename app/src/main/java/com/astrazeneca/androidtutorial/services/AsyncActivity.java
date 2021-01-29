package com.astrazeneca.androidtutorial.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

public class AsyncActivity extends AppCompatActivity {

    Button btnFetch;
    TextView txtTitle;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnFetch = (Button) findViewById(R.id.btnFetch);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new FetchData(AsyncActivity.this).execute("");
            }
        });

        IntentFilter mFilter = new IntentFilter("DOWNLOAD_COMPLETE");
        registerReceiver(mBroadcastReceiver, mFilter);
    }

    @Override
    protected void onPause() {

        super.onPause();
        unregisterReceiver(mBroadcastReceiver);
    }

    public class FetchData extends AsyncTask<String, Void, String> {

        Context mContext;
        public FetchData(Context context) {
            this.mContext = context;
        }

        @Override
        protected void onPreExecute() {
            Util.showLoader(mContext);
        }

        @Override
        protected String doInBackground(String... strings) {

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

            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            Util.hideLoader();
        }
    }

    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String responseString = intent.getStringExtra("title");
            txtTitle.setText(responseString);
        }
    };
}

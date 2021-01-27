package com.astrazeneca.androidtutorial.network;

import com.astrazeneca.androidtutorial.network.model.DummyData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("todos/1")
    Call<DummyData> getDOTOData();
}
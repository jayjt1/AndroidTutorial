package com.astrazeneca.androidtutorial.network;

import com.astrazeneca.androidtutorial.network.model.Company;
import com.astrazeneca.androidtutorial.network.model.DummyData;
import com.astrazeneca.androidtutorial.network.model.User;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

//    https://jsonplaceholder.typicode.com/todos/1

    @GET("todos/1")
    Call<DummyData> getDOTOData();

   // https://firebasestorage.googleapis.com/v0/b/androidtutorial-90ee1.appspot.com/o/dummy.json?alt=media&token=ebec47cf-04d8-426a-8a96-a5d6f5ff61f7

    @GET("v0/b/androidtutorial-90ee1.appspot.com/o/dummy.json?alt=media&token=ebec47cf-04d8-426a-8a96-a5d6f5ff61f7")
    Call<User> getFirebaseUserData();

   // https://firebasestorage.googleapis.com/v0/b/androidtutorial-90ee1.appspot.com/o/company.json?alt=media&token=e3545330-cb67-4324-a907-761b3c1af0eb

    @GET("v0/b/androidtutorial-90ee1.appspot.com/o/company.json?alt=media&token=e3545330-cb67-4324-a907-761b3c1af0eb")
    Call<Company> getFirebaseCompanyData();

}

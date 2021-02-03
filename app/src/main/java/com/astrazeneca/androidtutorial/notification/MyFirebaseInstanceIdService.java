package com.astrazeneca.androidtutorial.notification;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Jayant on 01/01/2018.
 */

//the class extending FirebaseInstanceIdService
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    //this method will be called
    //when the token is generated
    @Override
    public void onTokenRefresh() {

        super.onTokenRefresh();

        //now we will have the token
        String token = FirebaseInstanceId.getInstance().getToken();

        Log.d("Device Token", "Token :" +token);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();


        editor.putString("token", token);
        editor.apply();
        //for now we are displaying the token in the log
        //copy it as this method is called only when the new token is generated
        //and usually new token is only generated when the app is reinstalled or the data is cleared
    }
}
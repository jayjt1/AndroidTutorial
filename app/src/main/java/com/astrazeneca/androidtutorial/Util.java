package com.astrazeneca.androidtutorial;

import android.app.ProgressDialog;
import android.content.Context;

public class Util {

    static ProgressDialog progressDialog;

    public static final String ROOTURL = "https://jsonplaceholder.typicode.com/";

    public static  void showLoader(Context mContext) {

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Expleo");
        progressDialog.setMessage("loading data Please wait...");
        progressDialog.show();
    }

    public static  void hideLoader() {
        progressDialog.dismiss();
    }
}

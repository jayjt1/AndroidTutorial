package com.astrazeneca.androidtutorial;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ImageActivity extends AppCompatActivity {

    Button btnCapture ;
    ImageView imgCaptured ,imgStored;
    Intent intent ;
    public  static final int RequestPermissionCode  = 1 ;

    public String TAG = ImageActivity.class.getSimpleName();

    File imgFile;

    String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        btnCapture = (Button)findViewById(R.id.btnCapture);
        imgCaptured = (ImageView)findViewById(R.id.imgCaptured);
        imgStored = (ImageView)findViewById(R.id.imgStored);

        EnableRuntimePermission();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (ActivityCompat.checkSelfPermission(ImageActivity.this, WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {

                    try {
                        imgFile = createImageFile();
                        imgPath = imgFile.getAbsolutePath();

                        Log.d(TAG, imgPath);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    Uri imageUri = Uri.fromFile(imgFile);
                    intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);
                    startActivityForResult(intent, 7);

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 7 && resultCode == RESULT_OK) {

            Uri imageUri = FileProvider.getUriForFile(
                    ImageActivity.this,
                    "com.astrazeneca.androidtutorial.provider",
                    imgFile);

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                imgCaptured.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private String getImageName() {

        String prefix = "expleo";
        long unixTime = System.currentTimeMillis()/1000L;
        String imageName = prefix + "_" + unixTime;
        return imageName;
    }

    private String getFullImageName() {

        String fullImageName = getImageName() + ".png";
        return fullImageName;
    }

    private File createImageFile() throws IOException {
        String imageFileName =getImageName();
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,".png",storageDir);

        Log.d("Image Name", image.getAbsolutePath());

        return image;
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(ImageActivity.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(ImageActivity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ImageActivity.this,new String[]{
                    Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(ImageActivity.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(ImageActivity.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }

}
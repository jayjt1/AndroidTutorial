package com.astrazeneca.androidtutorial.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.astrazeneca.androidtutorial.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by Jayant on 01/01/2018.
 */

//class extending FirebaseMessagingService
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getData().size() > 0) {

            String title, message;

            title = remoteMessage.getData().get("title");
            message = remoteMessage.getData().get("message");


            Intent home = new Intent(this, MyNotificationActivity.class);
            home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, home, PendingIntent.FLAG_ONE_SHOT);

            final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
            mBuilder.setLargeIcon(largeIcon);
            mBuilder.setContentTitle(title);
            mBuilder.setContentText(message);
            mBuilder.setAutoCancel(true);
            mBuilder.setContentIntent(pendingIntent);

            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (mNotifyMgr != null) {
                mNotifyMgr.notify(0, mBuilder.build());
            }
        }
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);


        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
//        sendRegistrationToServer(token);
    }
}
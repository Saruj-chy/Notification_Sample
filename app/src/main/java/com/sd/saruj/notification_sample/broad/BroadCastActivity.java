package com.sd.saruj.notification_sample.broad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.sd.saruj.notification_sample.MainActivity;
import com.sd.saruj.notification_sample.R;

import java.util.Calendar;
import java.util.Random;

import static com.sd.saruj.notification_sample.App.CHANNEL_1_ID;
import static com.sd.saruj.notification_sample.App.CHANNEL_2_ID;

public class BroadCastActivity extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;
    private EditText editTextTitle;
    private EditText editTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad_cast);
        notificationManager = NotificationManagerCompat.from(this);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextMessage = findViewById(R.id.edit_text_message);
    }
    public void sendOnChannel1(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();

//        long c = Calendar.getInstance().getTimeInMillis();
//        long tenSec = 1000*10 ;
//        long tewSec = 1000*20 ;
//
//        int numb = new Random().nextInt() ;
//        Log.d("TAG", "numb: "+numb) ;
//        Log.d("TAG","time: "+c) ;
//
//        AlarmManager alarmManager1 = (AlarmManager) getSystemService(ALARM_SERVICE);



        Intent activityIntent = new Intent(this, BroadCastActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0, activityIntent, 0);
        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        long[] pattern = { 0, 100, 200, 300 };
        Uri alarmSounds = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);

        Uri alarmSound =
                RingtoneManager. getDefaultUri (RingtoneManager. TYPE_NOTIFICATION );
        MediaPlayer mp = MediaPlayer. create (getApplicationContext(), alarmSound);
        mp.start();

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title+" ")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.BLUE)
                .setContentIntent(contentIntent)

                .setLights(Color.BLUE, 500, 500)
                .setVibrate(pattern)
                .setStyle(new NotificationCompat.InboxStyle())
//                .setSound()


                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
                .build();
        notificationManager.notify(1, notification);

//        alarmManager1.set(AlarmManager.RTC_WAKEUP, c+tenSec, actionIntent);
    }
    public void sendOnChannel2(View v) {
        String title = editTextTitle.getText().toString();
        String message = editTextMessage.getText().toString();
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_notifications_full)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        notificationManager.notify(2, notification);
    }
}

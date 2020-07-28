package com.sd.saruj.notification_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sd.saruj.notification_sample.broad.BroadCastActivity;

public class MainActivity extends AppCompatActivity {

    Button button1, button2 ;
    private final static String default_notification_channel_id = "default";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1) ;
        button2 = findViewById(R.id.button2) ;





        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SampleActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), BroadCastActivity.class));
             /*   Uri alarmSound =
                        RingtoneManager. getDefaultUri (RingtoneManager. TYPE_NOTIFICATION );
                MediaPlayer mp = MediaPlayer. create (getApplicationContext(), alarmSound);
                mp.start();
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MainActivity. this, default_notification_channel_id )
                                .setSmallIcon(R.drawable. ic_launcher_foreground )
                                .setContentTitle( "Test" )
                                .setContentText( "Hello! This is my first push notification" ) ;
                NotificationManager mNotificationManager = (NotificationManager)
                        getSystemService(Context. NOTIFICATION_SERVICE );
                mNotificationManager.notify(( int ) System. currentTimeMillis () ,
                        mBuilder.build());*/

                try {
                    Uri path = Uri.parse("android.resource://" + getPackageName() + "/raw/mehendi.m4a");
                    RingtoneManager.setActualDefaultRingtoneUri(
                            getApplicationContext(), RingtoneManager.TYPE_RINGTONE, path
                    );
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), path);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}

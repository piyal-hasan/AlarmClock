package com.example.mdpiyalhasan.piyalalarmclock;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Md Piyal Hasan on 10/13/2016.
 */
public class RingtonService extends Service {
    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public int onStartCommand(Intent intent,int flags,int startId)
    {
        Log.e("Lopcalservice","receiver id"+startId+":"+intent);
        String state=intent.getExtras().getString("extra");
        Log.e("extra string",state);
        NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Intent intent_main_activity=new Intent(this.getApplicationContext(),piyal.class);
        PendingIntent pendingIntent_main_activity=PendingIntent.getActivity(this,0,intent_main_activity,0);
        Notification notification_popup=new Notification.Builder(this)
                .setContentTitle("Alarm is going off!")
                .setContentText("click me!")
                .setContentIntent(pendingIntent_main_activity)
                .setAutoCancel(true)
                .build();
        notificationManager.notify(0,notification_popup);
        assert state != null;
        switch (state)
        {
            case "alarm on": {
                startId = 1;
                break;
            }
            case "alarm off": {
                startId = 0;
                break;
            }
            default: {
                startId = 0;
                break;
            }
        }
        //rington off_setup setting
        if(!this.isRunning&&startId==1)
        {
            mediaPlayer=MediaPlayer.create(this,R.raw.azan);
            mediaPlayer.start();
            this.isRunning=true;
            this.startId=0;
        }
        else if(this.isRunning&&startId==0)
        {
            mediaPlayer.stop();
            mediaPlayer.reset();
            this.isRunning=false;
            this.startId=0;

        }
        else if(!this.isRunning&&startId==0)
        {
            this.isRunning=false;
            this.startId=0;
        }
        else if(this.isRunning&&startId==1)
        {
            this.isRunning=true;
            this.startId=1;

        }
        else
        {

        }
        return START_NOT_STICKY;
    }
    public void onDestroy()
    {
      super.onDestroy();
        this.isRunning=false;
    }
}

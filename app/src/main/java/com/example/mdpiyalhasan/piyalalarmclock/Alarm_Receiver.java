package com.example.mdpiyalhasan.piyalalarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Md Piyal Hasan on 10/13/2016.
 */
public class Alarm_Receiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receive","yeh!");
        String string=intent.getExtras().getString("extra");
        Log.e("string ",string);
        Intent intent_service=new Intent(context,RingtonService.class);
        intent_service.putExtra("extra",string);
        context.startService(intent_service);
    }
}

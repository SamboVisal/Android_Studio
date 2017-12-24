package com.example.sambovisal.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by sambo visal on 27/09/2017.
 */

public class MyService extends Service {

    MediaPlayer mp;

    @Override
    public void onCreate() {
        mp = MediaPlayer.create(MyService.this,R.raw.christainsong);
        Toast.makeText(this,"Service has created",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service has started",Toast.LENGTH_LONG).show();
        mp.start();
        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service has stopped",Toast.LENGTH_LONG).show();
        mp.stop();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

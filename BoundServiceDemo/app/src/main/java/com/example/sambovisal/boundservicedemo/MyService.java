package com.example.sambovisal.boundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by sambo visal on 28/09/2017.
 */

public class MyService extends Service {
    //to implement service bound we need IBinder interface to interact with the user
    //iBinder is use for service bound that allow to communicate with user 
    IBinder binder = new MBinder();
    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }
    //after we implement interface we just need to extend class that implement interface IBinder and execute service
    //this Binder is defined by IBinder
    public class MBinder extends Binder
    {
        public MyService getService(){
            return MyService.this;
        }
    }
    public int add(int x,int y){
        int res = x+y;
        return res;
    }
}

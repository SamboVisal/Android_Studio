package com.example.sambovisal.boundservicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sambovisal.boundservicedemo.MyService.MBinder;

public class MainActivity extends AppCompatActivity {

    EditText name,pass;
    boolean status;
    MyService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.pwd);

    }

    public void startMethod(View view){
        Intent i = new Intent(this,MyService.class);
        bindService(i,sc, Context.BIND_AUTO_CREATE);
        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        status = true;

    }
    public void stopMethod(View view){
        if(status == true){
            unbindService(sc);
            Toast.makeText(this,"Unbinded",Toast.LENGTH_SHORT).show();
            status = false;
        }else{
            Toast.makeText(this, "Bind first", Toast.LENGTH_SHORT).show();
        }

    }
    public void addService(View view){
        if (status){
            int num1 = Integer.parseInt(name.getText().toString());
            int num2 = Integer.parseInt(pass.getText().toString());
            int res = mService.add(num1,num2);
            Toast.makeText(this,"Sum : "+res,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Bind service first",Toast.LENGTH_SHORT).show();
        }
    }
    //this is abstract that it connects to service for the user
    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //// We've bound to LocalService, cast the IBinder and get LocalService instance
            MBinder binder = (MBinder) iBinder;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}

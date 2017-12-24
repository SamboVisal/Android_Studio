package com.example.sambovisal.seekbarscolorschange;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView t1,t2,t3,t4;
    private SeekBar s1,s2,s3,s4;
    Random r;
    private RelativeLayout ll;
    int max=255,i=0,k1=0,k2=0,k3=0,k4=0,color;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //r = new Random();
        ll = (RelativeLayout) findViewById(R.id.layout);
        t1 = (TextView) findViewById(R.id.view1);
        t2 = (TextView) findViewById(R.id.view2);
        t3 = (TextView) findViewById(R.id.view3);
        t4 = (TextView) findViewById(R.id.view4);
        s1 = (SeekBar) findViewById(R.id.seekBar);
        s1.setMax(max);
        s2 = (SeekBar) findViewById(R.id.seekBar2);
        s2.setMax(max);
        s3 = (SeekBar) findViewById(R.id.seekBar3);
        s3.setMax(max);
        s4 = (SeekBar) findViewById(R.id.seekBar4);

        btn = (Button)findViewById(R.id.next);

        s4.setMax(max);
        t1.setText("0");
        t2.setText("0");
        t3.setText("0");
        t4.setText("0");
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                 k1 = s1.getProgress();
                 t1.setText("" +k1);
                 color = Color.argb(k1,k2,k3,k4);

                ll.setBackgroundColor(color);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                k2 = s2.getProgress();
                t2.setText(""+k2);
                color = Color.argb(k1,k2,k3,k4);
                ll.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        s3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                k3 = s3.getProgress();
                t3.setText(""+k3);
                color = Color.argb(k1,k2,k3,k4);
                ll.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        s4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                k4 = s4.getProgress();
                t4.setText(""+k4);
                color = Color.argb(k1,k2,k3,k4);
                ll.setBackgroundColor(color);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ButtonActivity.class);
                startActivity(i);
            }
        });
    }
}

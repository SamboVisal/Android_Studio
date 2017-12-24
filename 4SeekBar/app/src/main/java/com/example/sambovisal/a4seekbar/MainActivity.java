package com.example.sambovisal.a4seekbar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SeekBar s1,s2,s3,s4;
    TextView t1,t2,t3,t4;
    int k1=0,k2=0,k3=0,k4=0;
    int color=0;
    TextView display;
    RelativeLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView)findViewById(R.id.display);
        s1 = (SeekBar)findViewById(R.id.s1);
        s1.setMax(255);
        s2 = (SeekBar)findViewById(R.id.s2);
        s2.setMax(255);
        s3 = (SeekBar)findViewById(R.id.s3);
        s3.setMax(255);
        s4 = (SeekBar)findViewById(R.id.s4);
        s4.setMax(255);

        t1 = (TextView)findViewById(R.id.textView);
        t1.setText("0");
        t2 = (TextView)findViewById(R.id.textView2);
        t2.setText("0");
        t3 = (TextView)findViewById(R.id.textView3);
        t3.setText("0");
        t4 = (TextView)findViewById(R.id.textView4);
        t4.setText("0");

        ll = (RelativeLayout)findViewById(R.id.layout);

        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                k1 = s1.getProgress();
                t1.setText("" +k1);
                //color = Color.argb(k1,k2,k3,k4);

                display.setBackgroundColor(Color.argb(k1,k2,k3,k4));
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
                //color = Color.argb(k1,k2,k3,k4);
                display.setBackgroundColor(Color.argb(k1,k2,k3,k4));
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
               // color = Color.argb(k1,k2,k3,k4);
                display.setBackgroundColor(Color.argb(k1,k2,k3,k4));
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
                // color = Color.argb(k1,k2,k3,k4);
                display.setBackgroundColor(Color.argb(k1,k2,k3,k4));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void applyMethod(View view) {
        getWindow().getDecorView().setBackgroundColor(Color.argb(k1,k2,k3,k4));
    }

}

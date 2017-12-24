package com.example.sambovisal.ratingbartest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RatingBar rating;
    private Button btn,btn1;
    private ProgressBar pb;
    private int i;

    private SeekBar sk;

    private Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //rating bar
        rating = (RatingBar)findViewById(R.id.ratingBar);
        btn = (Button)findViewById(R.id.button);
        //progressBar
        pb = (ProgressBar)findViewById(R.id.progressBar);

        //switch
        sw = (Switch)findViewById(R.id.switch1);



        //seekbar
        sk = (SeekBar)findViewById(R.id.seekBar);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(MainActivity.this,"Progress "+i,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"Start process",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"Stop processing",Toast.LENGTH_LONG).show();
            }
        });
    }
        //rating
        public void clickMe(View v){
            float value = rating.getRating();
            Toast.makeText(MainActivity.this,"The value is "+value,Toast.LENGTH_LONG).show();
        }

        //progress bar
        public void proClick(View v){

            Thread t = new Thread(){
                @Override
                public void run(){
                super.run();
                    for (i=0; i<=100;){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        pb.setProgress(i);
                        i+=10;
                    }
              }
            };
            t.start();

        }

        //switch
        public void switchClick(View v){
            if(sw.isChecked()){
                Toast.makeText(MainActivity.this,"ON",Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(MainActivity.this,"OFF",Toast.LENGTH_LONG).show();
            }

        }

}

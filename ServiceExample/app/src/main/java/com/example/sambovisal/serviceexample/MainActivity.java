package com.example.sambovisal.serviceexample;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MediaPlayer media;
    int song1 = R.raw.christainsong;
    int song2 = R.raw.secondsong;
    int song3 = R.raw.thridsong;
    int song4 = R.raw.fourthsong;
    int song5 = R.raw.fifthsong;
   // static int music=0;
    TextView songName;
    Button next,prev,pause,stop,play;
    ArrayList<Integer> playlist = new ArrayList<Integer>();
//    static int music[] = {R.raw.secondsong,R.raw.secondsong};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        next = (Button)findViewById(R.id.next_btn);
        prev = (Button)findViewById(R.id.prev_btn);
        pause = (Button)findViewById(R.id.pause_btn);
        stop = (Button)findViewById(R.id.stop_btn);
        play = (Button)findViewById(R.id.play_btn);
        songName = (TextView) findViewById(R.id.song_name);
        //why need service because we need to provide background support for the user

//        final Field[] fields = R.raw.class.getFields();
//        for(int i=0; i<fields.length;i++){
//            playlist.add(fields[i].getName());
//        }
//        for(Field field: R.raw.class.getFields()){
//            try {
//                playlist.add(field.getInt(field));
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }



}

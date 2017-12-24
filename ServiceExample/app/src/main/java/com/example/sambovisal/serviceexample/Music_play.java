package com.example.sambovisal.serviceexample;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sambo visal on 27/09/2017.
 */

public class Music_play extends Fragment {
    MediaPlayer play1,play2,play3,play4,play5;
    int song1 = R.raw.christainsong;
    int song2 = R.raw.secondsong;
    int song3 = R.raw.thridsong;
    int song4 = R.raw.fourthsong;
    int song5 = R.raw.fifthsong;
    TextView songName;
    Button next,prev,pause,stop,play;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.musicplay,container,false);
        next = v.findViewById(R.id.next_btn);
        prev = v.findViewById(R.id.prev_btn);
        pause = v.findViewById(R.id.pause_btn);
        stop = v.findViewById(R.id.stop_btn);
        play = v.findViewById(R.id.play_btn);
        songName = v.findViewById(R.id.song_name);

        return v;
    }
}

package com.example.sambovisal.serviceexample;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by sambo visal on 27/09/2017.
 */

public class Music_list extends Fragment {
    MediaPlayer mp;
    Button s1,s2,s3,s4,s5;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.listsong,container,false);
        s1 = v.findViewById(R.id.first_music);
        s2 = v.findViewById(R.id.second_btn);
        s3 = v.findViewById(R.id.thrid_btn);
        s4 = v.findViewById(R.id.fourth_btn);
        s5 = v.findViewById(R.id.first_music);
        return v;
    }

}

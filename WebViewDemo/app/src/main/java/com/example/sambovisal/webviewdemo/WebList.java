package com.example.sambovisal.webviewdemo;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by sambo visal on 26/09/2017.
 */

public class WebList extends Fragment {
    Button youTube,Facebook,Google,Gmail;
    Link link;
    public interface Link
    {
        void setLink(String li);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            link = (Link) activity;
        }catch (ClassCastException e){

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.web_list,container,false);
        youTube = v.findViewById(R.id.youtube_btn);
        Facebook = v.findViewById(R.id.facebook_btn);
        Google = v.findViewById(R.id.google_btn);
        Gmail = v.findViewById(R.id.gmail_btn);
        youTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("https://www.youtube.com");

            }
        });
        Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("https://www.facebook.com");
            }
        });
        Google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("https://www.google.com");
            }
        });
        Gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick("https://www.gmail.com");
            }
        });
        return v;
    }

    private void buttonClick(String s) {
        link.setLink(s);
    }
}

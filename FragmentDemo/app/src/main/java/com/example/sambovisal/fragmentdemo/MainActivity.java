package com.example.sambovisal.fragmentdemo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        Configuration config = getResources().getConfiguration();

        if(config.orientation==Configuration.ORIENTATION_LANDSCAPE){
            LandscapeClass landscapeClass = new LandscapeClass();
            fragmentTransaction.replace(android.R.id.content,landscapeClass);
        }
        else{
            PartialClass partialClass = new PartialClass();
            fragmentTransaction.replace(android.R.id.content, partialClass);

        }
        fragmentTransaction.commit();

    }

}

    package com.example.sambovisal.fragmentsinteract;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TopFragClass.TopFrag{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setText(String name, String pass) {
        BottomFragClass btf = (BottomFragClass) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        btf.setNamePass(name,pass);
    }
}

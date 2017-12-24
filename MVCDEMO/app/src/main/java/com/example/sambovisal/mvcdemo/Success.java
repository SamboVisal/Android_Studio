package com.example.sambovisal.mvcdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Success extends AppCompatActivity {
    TextView getN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        getN = (TextView)findViewById(R.id.getName);
        Intent i = getIntent();
        String n =  i.getStringExtra("name");
        getN.setText(n);

    }
}

package com.example.sambovisal.vigneshclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextView ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = (Toolbar) findViewById(R.id.log_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Welcome");
        ed = (TextView) findViewById(R.id.show);
        Intent i = getIntent();
        String name = i.getStringExtra("name"); 
        //this argument we will paste the key of the MainActivity
        ed.setText(name);
    }
}

package com.example.sambovisal.loginregister;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {
    EditText sName,sAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);
        sName = (EditText) findViewById(R.id.etUsername);
        sAge = (EditText) findViewById(R.id.et_Age);
        final TextView welcome = (TextView) findViewById(R.id.WelcomeMgs);
    }
}

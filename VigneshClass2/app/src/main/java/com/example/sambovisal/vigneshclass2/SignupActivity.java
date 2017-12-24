package com.example.sambovisal.vigneshclass2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity {
    Toolbar mToolbar;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mToolbar = (Toolbar) findViewById(R.id.signup_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Sign up");
        btn = (Button) findViewById(R.id.signup_btn);

    }
    public void ClickMe(View v){
        Intent i = new Intent(SignupActivity.this,Main2Activity.class);
        startActivity(i);
    }
}

package com.example.sambovisal.vigneshclass2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SigninActivity extends AppCompatActivity {
    Toolbar mToolbar;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        mToolbar = (Toolbar) findViewById(R.id.sign_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Sign In");
        btn = (Button) findViewById(R.id.sign_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SigninActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
}

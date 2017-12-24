package com.example.sambovisal.vigneshclass2;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    private RadioGroup mrGroup;
    private RadioButton b1;
    private RadioButton b2;
    private RadioButton b3;
    private RadioButton b4;
    private Button sBtn;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mrGroup = (RadioGroup) findViewById(R.id.radioGroup);
        b1 = (RadioButton) findViewById(R.id.radio_btn1);
        b2 = (RadioButton) findViewById(R.id.radio_btn2);
        b3 = (RadioButton) findViewById(R.id.radio_btn3);
        b4 = (RadioButton) findViewById(R.id.radio_btn4);
        sBtn = (Button) findViewById(R.id.sub_btn);
        mToolbar = (Toolbar) findViewById(R.id.main2_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Choose");
    }
    public void ClickMe(View v){
        if(b1.isChecked()==true){
            Intent i = new Intent(Main2Activity.this,EmptyActivity.class);
            startActivity(i);
        }else if(b2.isChecked()==true){
            Intent i = new Intent(Main2Activity.this,SignupActivity.class);
            startActivity(i);
        }else if(b3.isChecked()==true){
            Intent i = new Intent(Main2Activity.this,MainActivity.class);
            startActivity(i);
        }else if(b4.isChecked()==true){
            Intent i = new Intent(Main2Activity.this,SigninActivity.class);
            startActivity(i);
        }else{
            Toast.makeText(Main2Activity.this,"Registered Failed",Toast.LENGTH_LONG).show();
        }
    }
}

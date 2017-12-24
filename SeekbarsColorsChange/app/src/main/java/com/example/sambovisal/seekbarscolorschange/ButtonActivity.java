package com.example.sambovisal.seekbarscolorschange;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.graphics.Color.parseColor;

public class ButtonActivity extends AppCompatActivity {
    Button r,y,g,bl,w,p,b,o;
    TextView tw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        r = (Button)findViewById(R.id.red);
        y = (Button)findViewById(R.id.yellow);
        g = (Button)findViewById(R.id.green);
        bl= (Button)findViewById(R.id.blue);
        w = (Button)findViewById(R.id.white);
        p = (Button)findViewById(R.id.purple);
        b = (Button)findViewById(R.id.black);
        o = (Button)findViewById(R.id.orange);
        tw= (TextView)findViewById(R.id.textView);
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(parseColor("red"));
                tw.setTextColor(parseColor("white"));
            }
        });
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(parseColor("yellow"));
                tw.setTextColor(parseColor("black"));
            }
        });
        g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(parseColor("green"));
                tw.setTextColor(Color.argb(255,0,0,0));
            }
        });
        bl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(parseColor("blue"));
                tw.setTextColor(Color.parseColor("red"));
            }
        });
        w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(parseColor("white"));
                tw.setTextColor(Color.parseColor("black"));
            }
        });
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(parseColor("purple"));
                tw.setTextColor(Color.parseColor("aqua"));
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(parseColor("black"));
                tw.setTextColor(parseColor("white"));
            }
        });
        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWindow().getDecorView().setBackgroundColor(parseColor("gray"));
                tw.setTextColor(parseColor("green"));
            }
        });
    }
}

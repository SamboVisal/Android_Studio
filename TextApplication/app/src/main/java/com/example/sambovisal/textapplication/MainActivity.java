package com.example.sambovisal.textapplication;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Toolbar mtoolbar;
    private TextInputLayout mDisname;
    private TextInputLayout mDispass;
    private Button mRegBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtoolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("Minoz Chat");

        mDisname = (TextInputLayout) findViewById(R.id.dis_name);
        mDispass = (TextInputLayout) findViewById(R.id.dis_pass);
        mRegBtn = (Button) findViewById(R.id.reg_btn);
        mRegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_intent = new Intent(MainActivity.this,StartActivity.class);
                startActivity(main_intent);
                finish();
            }
        });
    }

}

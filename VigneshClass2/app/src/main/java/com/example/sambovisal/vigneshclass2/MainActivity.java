package com.example.sambovisal.vigneshclass2;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button logBtn;
    private TextInputLayout mDisname;
    private TextInputLayout mDispass;
    public String name;
    public String pass;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logBtn = (Button) findViewById(R.id.log_btn);
        mDisname = (TextInputLayout) findViewById(R.id.dis_user);
        mDispass = (TextInputLayout) findViewById(R.id.dis_pass);
        mToolbar = (Toolbar) findViewById(R.id.log_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");

    }
    public void ClickMe(View view){
        name = mDisname.getEditText().getText().toString();
        pass = mDispass.getEditText().getText().toString();
        if(name.equals("Pisal")&&pass.equals("12345")){
            Intent i = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(i);
        }
    }

}

package com.example.sambovisal.loginsignupdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Toolbar mToolbar;
    EditText ULNAME,ULPASS;
    Button mLogBtn;
    DataBaseHelperClass dc = new DataBaseHelperClass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = (Toolbar)findViewById(R.id.login_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login To Your Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ULNAME = (EditText) findViewById(R.id.log_User);
        ULPASS = (EditText) findViewById(R.id.log_pass);
        mLogBtn = (Button)  findViewById(R.id.log_acc_btn);
        mLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ULNAME.getText().toString();
                String pass = ULPASS.getText().toString();
                String password = dc.confrimPass(name);
                if(pass.equals(password)){
                    Intent i = new Intent(LoginActivity.this,DisplayData.class);
                    i.putExtra("Username",name);
                    startActivity(i);

                }

            }
        });
    }
}

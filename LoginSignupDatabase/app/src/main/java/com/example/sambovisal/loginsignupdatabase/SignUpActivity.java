package com.example.sambovisal.loginsignupdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    Toolbar mToolbar;
    EditText uName,Pass,ConPass,Email;
    Button mSubbtn;
    DataBaseHelperClass dbh = new DataBaseHelperClass(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
//        mToolbar = (Toolbar)findViewById(R.id.main_tool);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("Sign Up new account");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        uName = (EditText)findViewById(R.id.username);
        Pass  = (EditText)findViewById(R.id.pass);
        ConPass = (EditText)findViewById(R.id.confirm_pass);
        Email = (EditText)findViewById(R.id.email);
        mSubbtn = (Button)findViewById(R.id.submit_btn);
        mSubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = uName.getText().toString();
                String pass = Pass.getText().toString();
                String conPass = ConPass.getText().toString();
                String email = Email.getText().toString();

                if(!pass.equals(conPass)){
                    Toast.makeText(SignUpActivity.this,"The Password is not match",Toast.LENGTH_LONG).show();
                }
                else{
                    LogSigForm input = new LogSigForm();
                    input.setName(name);
                    input.setEmail(email);
                    input.setPass(pass);
                    boolean res = dbh.insertData(input);
                    if(res){
                        Toast.makeText(SignUpActivity.this,"Done",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(SignUpActivity.this,"False",Toast.LENGTH_LONG).show();

                    }
                }

            }
        });
    }
}

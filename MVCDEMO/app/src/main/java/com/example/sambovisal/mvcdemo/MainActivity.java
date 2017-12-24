package com.example.sambovisal.mvcdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userID;
    EditText userPass;
    Button sig_bnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userID = (EditText)findViewById(R.id.userID);
        userPass = (EditText)findViewById(R.id.userPass);
        sig_bnt = (Button)findViewById(R.id.signbtn);



        sig_bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int userid = Integer.parseInt(userID.getText().toString());
                String uname = userPass.getText().toString();
                StudentModel sm = new StudentModel(123,"Pisal");
                if(userid==sm.getUserID() && uname==sm.getUserName()){
                    Intent i = new Intent(MainActivity.this,Success.class);
                    startActivity(i);
                    i.putExtra("name",uname);
                    i.putExtra("pass",userid);
                }
                else{
                    Toast.makeText(MainActivity.this,"There are some error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

package com.example.sambovisal.dbtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DBClass db;
    EditText name,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText)findViewById(R.id.user_input);
        email = (EditText)findViewById(R.id.email_input);
        db = new DBClass(this);
    }
    public void loginMethod(View view){
        String Name = name.getText().toString();
        String Email = email.getText().toString();
        boolean result = db.checkAvailable(Name,Email);
        if(result==true){
            Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
        }else{
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
        }
    }
}

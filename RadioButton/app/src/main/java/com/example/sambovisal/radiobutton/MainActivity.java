package com.example.sambovisal.radiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rg;
    RadioButton m;
    RadioButton g;
    TextView t,o;
    Button btn;
    EditText us;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg = (RadioGroup)findViewById(R.id.gender);
        m = (RadioButton)findViewById(R.id.male);
        g = (RadioButton)findViewById(R.id.female);
        t = (TextView)findViewById(R.id.tw);
        o = (TextView)findViewById(R.id.output);
        btn = (Button)findViewById(R.id.submit);
        us = (EditText)findViewById(R.id.username);
        t.setText("");
        o.setText("");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (m.isChecked()==true){
                    String s = us.getText().toString();
                    t.setText("Welcome Mr.");
                    o.setText(s);

                }else {
                    if (g.isChecked() == true) {
                        String s1 = us.getText().toString();
                        t.setText("Welcome Mrs.");
                        o.setText(s1);
                    } else {
                        Toast.makeText(MainActivity.this,"Please Select",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}

package com.example.sambovisal.calculationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num1=null,num2=null;
    Button add=null,sub=null,mul=null,div=null,clear=null;
    Button pro;
    TextView answer;
    int number1=0,number2=0,result;
    Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        num1 = (EditText)findViewById(R.id.editText);
        num2 = (EditText)findViewById(R.id.editText2);
        answer = (TextView) findViewById(R.id.textView);
        add = (Button)findViewById(R.id.add);
        sub = (Button)findViewById(R.id.sub);
        mul = (Button)findViewById(R.id.mul);
        div = (Button)findViewById(R.id.div);
        clear= (Button)findViewById(R.id.clear);

        mToolbar = (Toolbar)findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Calculation");
        add.setOnClickListener(new View.OnClickListener() {
            @Override //mean this function belong to parent
            public void onClick(View view) {
                if(TextUtils.isEmpty(num1.getText().toString())||TextUtils.isEmpty(num2.getText().toString())){
                    return;
                }

                number1 = Integer.parseInt(num1.getText().toString());
                number2 = Integer.parseInt(num2.getText().toString());
               result = number1+number2;
                answer.setText(String.valueOf(result));
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(num1.getText().toString())||TextUtils.isEmpty(num2.getText().toString())){
                    return;
                }

                number1 = Integer.parseInt(num1.getText().toString());
                number2 = Integer.parseInt(num2.getText().toString());
                result = number1-number2;
                answer.setText(""+result);
            }
        });
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(num1.getText().toString())||TextUtils.isEmpty(num2.getText().toString())){
                    return;
                }

                number1 = Integer.parseInt(num1.getText().toString());
                number2 = Integer.parseInt(num2.getText().toString());
                result = number1*number2;
                answer.setText(""+result);
            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(num1.getText().toString())||TextUtils.isEmpty(num2.getText().toString())){
                    return;
                }

                number1 = Integer.parseInt(num1.getText().toString());
                number2 = Integer.parseInt(num2.getText().toString());
                result = number1/number2;
                answer.setText(""+result);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1.setText("");
                num2.setText("");
                answer.setText("");
                Toast.makeText(MainActivity.this,"Cleared",Toast.LENGTH_LONG).show();
            }
        });
    }

}

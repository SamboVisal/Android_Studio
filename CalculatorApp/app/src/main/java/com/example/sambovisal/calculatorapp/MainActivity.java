package com.example.sambovisal.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button dele,percent,dot;
    Button add,mul,div,sub,equal;
    Button num1,num2,num3,num4,num5,num6,num7,num8,num9,num0;
    TextView n1,n2,result;
    float nu1,nu2,resul;
    boolean mAddition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dele = (Button)findViewById(R.id.btn_delete);
        percent = (Button)findViewById(R.id.btn_percentage);
        dot = (Button)findViewById(R.id.btn_dot);
        add = (Button)findViewById(R.id.btn_plus);
        mul = (Button)findViewById(R.id.btn_multiple);
        div = (Button)findViewById(R.id.btn_div);
        sub = (Button)findViewById(R.id.btn_minus);
        equal = (Button)findViewById(R.id.btn_equal);
        num1 = (Button)findViewById(R.id.btn_one);
        num2 = (Button)findViewById(R.id.btn_two);
        num3 = (Button)findViewById(R.id.btn_three);
        num4 = (Button)findViewById(R.id.btn_four);
        num5 = (Button)findViewById(R.id.btn_five);
        num6 = (Button)findViewById(R.id.btn_six);
        num7 = (Button)findViewById(R.id.btn_seven);
        num8 = (Button)findViewById(R.id.btn_eight);
        num9 = (Button)findViewById(R.id.btn_nine);
        num0 = (Button)findViewById(R.id.btn_zero);
        n1 = (TextView)findViewById(R.id.number1);
        n2= (TextView)findViewById(R.id.number2);
        result = (TextView)findViewById(R.id.result);
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                n1.setText(n1.getText()+"1");
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nu1 = Float.parseFloat(n1.getText()+"");
                mAddition =true;
                n1.setText(n1.getText()+"+");
                n1.setText(null);
            }
        });
        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nu2 = Float.parseFloat(n1.getText()+"");
                if(mAddition==true){
                    n1.setText(nu1+nu2+"");
                    mAddition=false;
                }
            }
        });

    }
}

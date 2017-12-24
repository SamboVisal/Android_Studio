package com.example.sambovisal.testing;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   // private TextView textView;
    private EditText eDtext;
    private Button btn;

    private RadioGroup radioGroup;

    private Button check;
    private CheckBox c1;
    private CheckBox c2;
    private CheckBox c3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //using textView
//        textView = (TextView) findViewById(R.id.text);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String var = textView.getText().toString();
//                Toast.makeText(MainActivity.this,var,Toast.LENGTH_LONG).show();
//            }
//        });
        //EditText
//        eDtext = (EditText) findViewById(R.id.eD);
//        eDtext.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String var = eDtext.getText().toString();
//                Toast.makeText(MainActivity.this,var,Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });

        btn = (Button) findViewById(R.id.button);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.radioButton:
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("Red"));
                        break;
                    case R.id.radioButton2:
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("Blue"));
                        break;
                    case R.id.radioButton3:
                        getWindow().getDecorView().setBackgroundColor(Color.parseColor("Yellow"));
                        break;
                }
            }
        });

        c1 = (CheckBox) findViewById(R.id.checkBox1);
        c2 = (CheckBox) findViewById(R.id.checkBox2);
        c3 = (CheckBox) findViewById(R.id.checkBox3);
        check = (Button) findViewById(R.id.check_btn);
    }
    //click if button is clicked
    public void btnClick(View v){
        Toast.makeText(this,"BUtton was clicked",Toast.LENGTH_SHORT).show();
    }

    public void ClickMe(View v){
        if(c1.isChecked()==true && c2.isChecked()==true && c3.isChecked()==true){
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("aqua"));
        }else if (c1.isChecked()==true && c2.isChecked()==true){
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("black"));
        }else if(c1.isChecked()==true && c3.isChecked()==true){
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("white"));
        }else if(c2.isChecked()==true && c3.isChecked()==true){
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("blue"));
        }
        else if(c1.isChecked()==true){
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("Red"));
        }else if(c2.isChecked()==true){
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("Yellow"));
        }else if(c3.isChecked()==true){
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("Green"));
        }else {
            getWindow().getDecorView().setBackgroundColor(Color.parseColor("black"));
        }
    }
}

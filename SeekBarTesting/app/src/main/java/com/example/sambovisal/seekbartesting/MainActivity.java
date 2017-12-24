package com.example.sambovisal.seekbartesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView tw;
    private TextView tw2;
    private EditText ed;
    private SeekBar sek;
    int progress = 30;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tw = (TextView)findViewById(R.id.textView);
        ed = (EditText)findViewById(R.id.editText);
        sek = (SeekBar) findViewById(R.id.seekBar);
        tw2 = (TextView) findViewById(R.id.textVIewP);
        sek.setMax(100);
        sek.setProgress(progress);

        sek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                String s = ed.getText().toString();
                tw.setText(s);
                tw.setTextSize(progress);
                String num = new String(String.valueOf(progress));
                tw2.setText(num);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}

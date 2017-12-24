package com.example.sambovisal.fragmentsinteract;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sambo visal on 25/09/2017.
 */

public class TopFragClass extends Fragment {
    EditText name,pass;
    Button submit;
    TopFrag topFrag;
    public interface TopFrag{
        void setText(String name, String pass);
    }

    @Override
    public void onAttach(Activity activityasdfasdf) {
        super.onAttach(activityasdfasdf);
        try{
            topFrag = (TopFrag) activityasdfasdf;
        }catch (ClassCastException e){

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.top_frag,container,false);
        name = (EditText)v.findViewById(R.id.username);
        pass = (EditText)v.findViewById(R.id.pwd);
        submit = (Button)v.findViewById(R.id.submit_btn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick(name.getText().toString(),pass.getText().toString());
            }
        });

        return v;
    }

    private void buttonClick(String name, String pass) {
        topFrag.setText(name,pass);
    }
}

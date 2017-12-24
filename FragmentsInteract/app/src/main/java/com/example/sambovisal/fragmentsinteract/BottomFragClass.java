package com.example.sambovisal.fragmentsinteract;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sambo visal on 25/09/2017.
 */

public class BottomFragClass extends Fragment {

    TextView nameT,passT;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_frag,container,false);
        nameT = (TextView)v.findViewById(R.id.name_text);
        passT = (TextView)v.findViewById(R.id.pass_text);

        return v;
    }

    public void setNamePass(String name, String pass) {
        nameT.setText(name);
        passT.setText(pass);
    }
}

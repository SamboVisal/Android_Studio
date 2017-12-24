package com.example.sambovisal.vigneshclass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextInputLayout mName;
    private TextInputLayout mPass;
    private Button logBtn;
    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        mName = (TextInputLayout) findViewById(R.id.dis_name);
        mPass = (TextInputLayout) findViewById(R.id.dis_pass);
        logBtn = (Button) findViewById(R.id.log_btn);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getEditText().getText().toString();
                String pass = mPass.getEditText().getText().toString();
//                mDialog.setTitle("Please Wait");
//                mDialog.setMessage("Logging Your Account");
//                mDialog.setCanceledOnTouchOutside(false);
//                mDialog.show();
                if(name.equals("Pisal") && pass.equals("123") || TextUtils.isEmpty(name)&&TextUtils.isEmpty(pass)){

                    Intent main_log = new Intent(MainActivity.this,LoginActivity.class);
//                    mDialog.dismiss();
                    main_log.putExtra("name",name);
                    main_log.putExtra("password",pass);
                    startActivity(main_log);
                }else{
//                    mDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Registering Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

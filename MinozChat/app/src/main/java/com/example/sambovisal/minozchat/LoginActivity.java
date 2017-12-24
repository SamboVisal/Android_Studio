package com.example.sambovisal.minozchat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private TextInputLayout mUser;
    private TextInputLayout mPass;
    private Button logBtu;
    private FirebaseAuth mAuth;
    private ProgressDialog mLogPro;

    private DatabaseReference mUserDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mToolbar = (Toolbar) findViewById(R.id.login_tool_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login To Your Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        mLogPro = new ProgressDialog(this);
        mUser = (TextInputLayout) findViewById(R.id.log_name);
        mPass = (TextInputLayout) findViewById(R.id.log_pass);
        logBtu = (Button) findViewById(R.id.log_btu);

        mUserDatabase = FirebaseDatabase.getInstance().getReference().child("MyUsers");
        logBtu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String display_name = mUser.getEditText().getText().toString();
                String password = mPass.getEditText().getText().toString();
                if (!TextUtils.isEmpty(display_name)|| !TextUtils.isEmpty(password)){
                    mLogPro.setTitle("Logging Your Account");
                    mLogPro.setMessage("Please Wait");
                    mLogPro.setCanceledOnTouchOutside(false);
                    mLogPro.show();
                    login(display_name,password);
                 }

            }
        });
    }

    private void login(String display_name, String password) {
        mAuth.signInWithEmailAndPassword(display_name, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    String current_user_id = mAuth.getCurrentUser().getUid();
                    String device_token = FirebaseInstanceId.getInstance().getToken();

                    mUserDatabase.child(current_user_id).child("Device_Token").setValue(device_token).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //before moving another intent
                            mLogPro.dismiss();
                            Intent log_main = new Intent(LoginActivity.this,MainActivity.class);
                            log_main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(log_main);
                            finish();
                        }
                    });

                }
                else{
                    mLogPro.hide();
                    Toast.makeText(LoginActivity.this,"Register Failed, Please check the form and try again ",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}

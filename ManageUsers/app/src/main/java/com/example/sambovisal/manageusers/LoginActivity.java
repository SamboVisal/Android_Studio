package com.example.sambovisal.manageusers;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private NestedScrollView nestedScrollView;

    private TextInputLayout InputLayoutEmail;
    private TextInputLayout InputLayoutPassword;

    private TextInputEditText InputEditTextEmail;
    private TextInputEditText InputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private ValidationClass inputValidation;
    private DatabaseHelper databaseHelper;

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ini();
        clickListener();
        inputValidation = new ValidationClass(this);
        databaseHelper = new DatabaseHelper(this);
    }

    private void ini() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        InputEditTextEmail = (TextInputEditText) findViewById(R.id.InputEditTextEmail);
        InputEditTextPassword = (TextInputEditText) findViewById(R.id.InputEditTextPassword);

        InputLayoutEmail = (TextInputLayout) findViewById(R.id.InputLayoutEmail);
        InputLayoutPassword = (TextInputLayout) findViewById(R.id.InputLayoutPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);

    }

    private void clickListener() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                verifyForm();
                break;
            case R.id.textViewLinkRegister:
                Intent i = new Intent(this,RegisterActivity.class);
                startActivity(i);
                break;
            }

    }
    private void verifyForm() {
        if (!inputValidation.isInputEditTextEmail(InputEditTextEmail,InputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(InputEditTextPassword,InputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (databaseHelper.checkUser(InputEditTextEmail.getText().toString().trim()
                , InputEditTextPassword.getText().toString().trim())) {


//            Intent accountsIntent = new Intent(this,UsersListActivity.class);
//            accountsIntent.putExtra("EMAIL", InputEditTextEmail.getText().toString().trim());
//            emptyInputEditText();
//            startActivity(accountsIntent);


        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        InputEditTextEmail.setText(null);
        InputEditTextPassword.setText(null);
    }


}


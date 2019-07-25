package com.example.instagram;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class RegisterAcivity2 extends Activity {
    private Button btnRegister;
     EditText etFullName, etEmail, etPassword, etConfirmPassword, etPhone;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        btnRegister = findViewById(R.id.btnRegister);
        etConfirmPassword =  findViewById(R.id.etConfirmPassword);
        etFullName =  findViewById(R.id.etFullName);
        etEmail =  findViewById(R.id.etEmail);
        etPhone =  findViewById(R.id.etPhone);
        etPassword =  findViewById(R.id.etPassword);
        tvLogin =  findViewById(R.id.tvLogin);

        if(ParseUser.getCurrentUser()!=null)
        {
            ParseUser.getCurrentUser().logOut();
        }

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ParseUser parseUser = new ParseUser();
                parseUser.setUsername(etFullName.getText().toString());
                parseUser.setPassword(etPassword.getText().toString());
                parseUser.setEmail(etEmail.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(RegisterAcivity2.this);
                progressDialog.setMessage("Signing up" + etFullName.getText().toString());
                progressDialog.show();

                parseUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null)
                        {
                            FancyToast.makeText(RegisterAcivity2.this, parseUser.get("username") + " is signed up succesfully", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    } else {
                        FancyToast.makeText(RegisterAcivity2.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                    }
                    progressDialog.dismiss();



            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterAcivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
});
    }
}


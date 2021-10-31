package com.example.androidstudiofirebasephoneauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class manageOPT extends AppCompatActivity {

    EditText et_enterOtp;
    Button btn_verifyOtp;
    String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_opt);

        mobileNumber = getIntent().getStringExtra("mobile").toString();
        et_enterOtp = (EditText) findViewById(R.id.et_enterOtp);
        btn_verifyOtp = (Button) findViewById(R.id.btn_verifyOtp);

    }
}
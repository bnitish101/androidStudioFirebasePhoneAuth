package com.example.androidstudiofirebasephoneauth;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText et_mobile;
    Button btn_getOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(et_mobile);

        et_mobile = (EditText) findViewById(R.id.et_mobile);
        btn_getOtp = (Button) findViewById(R.id.btn_getOtp);

    }

}
package com.example.androidstudiofirebasephoneauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {

    CountryCodePicker ccp;
    EditText et_mobile;
    Button btn_getOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_mobile = (EditText) findViewById(R.id.et_mobile);

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(et_mobile);

        btn_getOtp = (Button) findViewById(R.id.btn_getOtp);

        btn_getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,manageOPT.class);
                intent.putExtra("mobile", ccp.getFullNumberWithPlus().replace(" ", ""));
                startActivity(intent);
            }
        });
    }

}
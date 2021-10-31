package com.example.androidstudiofirebasephoneauth;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class manageOPT extends AppCompatActivity {

    EditText et_enterOtp;
    Button btn_verifyOtp;
    String mobileNumber;
    String otpId;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_opt);

        mobileNumber = getIntent().getStringExtra("mobile").toString();
        et_enterOtp = (EditText) findViewById(R.id.et_enterOtp);
        btn_verifyOtp = (Button) findViewById(R.id.btn_verifyOtp);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        initiateOtp();

        btn_verifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_enterOtp.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Empty field can not process", Toast.LENGTH_LONG).show();
                else if (et_enterOtp.getText().toString().length() != 6)
                    Toast.makeText(getApplicationContext(),"Invalid OTP", Toast.LENGTH_LONG).show();
                else{
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpId, et_enterOtp.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

    }

    private void initiateOtp() {
        // The test phone number and code should be whitelisted in the console.
        // String phoneNumber = "+919699992340";
        // String smsCode = "123456";

        // FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        // FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

        // firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, smsCode);

        PhoneAuthOptions options =
                 PhoneAuthOptions.newBuilder(mAuth)
                // PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(mobileNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    otpId = s;
                                }

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    signInWithPhoneAuthCredential(phoneAuthCredential);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        )          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithCredential:success");

                            // FirebaseUser user = task.getResult().getUser();
                            // Update UI

                            startActivity(new Intent(manageOPT.this,dashboard.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Signin Code Error", Toast.LENGTH_LONG).show();
                            // Sign in failed, display a message and update the UI
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());
                            // if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            // }
                        }
                    }
                });
    }
}
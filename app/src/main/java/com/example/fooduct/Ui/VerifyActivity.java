package com.example.fooduct.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.fooduct.R;
import com.example.fooduct.databinding.ActivityVerifyBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {

    ActivityVerifyBinding binding;
    FirebaseAuth mAuth;
    PhoneAuthCredential credential ;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();

        String verificationId = getIntent().getStringExtra("VerificationId");
        String Number = getIntent().getStringExtra("PhoneNumber");
        Number = Number.replaceAll("962" , "962 ");
        binding.verifyActivityPhoneNumber.setText(Number);

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerifyActivity.this)
                        .setView(R.layout.progress_dialog);

                AlertDialog dia = builder.create();

                dia.show();


                if(!binding.etCod.getText().toString().isEmpty()) {
                    String code = binding.etCod.getText().toString();
                    credential = PhoneAuthProvider.getCredential(verificationId, code);
                    mAuth.getCurrentUser().updatePhoneNumber(credential);

                    while(mAuth.getCurrentUser().getPhoneNumber() == null);

                    dia.dismiss();

                    Intent in = new Intent(VerifyActivity.this , MainActivity.class);
                    startActivity(in);
                }
                else{
                    dia.dismiss();
                    binding.etCod.setError("You have to put the code to continue....");
                }
            }
        });

        binding.LoginMakeNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(VerifyActivity.this).inflate(R.layout.custom_change_dialog , null , false);
                EditText editText = v.findViewById(R.id.custom_change_changeUserPhone);

                AlertDialog.Builder builder = new AlertDialog.Builder(VerifyActivity.this)
                        .setView(v).setPositiveButton("تأكيد الرقم", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                                        mAuth.getCurrentUser().updatePhoneNumber(credential);
                                        finish();
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        // This callback is invoked in an invalid request for verification is made,
                                        // for instance if the the phone number format is not valid.
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String verificationId,
                                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                        // The SMS verification code has been sent to the provided phone number, we
                                        // now need to ask the user to enter the code and then construct a credential
                                        // by combining the code with a verification ID.

                                        // Save verification ID and resending token so we can use them later
                                        Intent intent = new Intent(VerifyActivity.this, VerifyActivity.class);
                                        intent.putExtra("VerificationId", verificationId);
                                        intent.putExtra("PhoneNumber",("+962") + editText.getText().toString());
                                        startActivity(intent);


                                    }
                                };


                                PhoneAuthOptions options =
                                        PhoneAuthOptions.newBuilder(mAuth)
                                                .setPhoneNumber(("+962") + editText.getText().toString())       // Phone number to verify
                                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                                .setActivity(VerifyActivity.this)                 // Activity (for callback binding)
                                                .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                                                .build();
                                PhoneAuthProvider.verifyPhoneNumber(options);

                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


    }

    public AlertDialog WaitingDialog (){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setView(R.layout.progress_dialog);
        return builder.create();
    }
}
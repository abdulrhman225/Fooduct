package com.example.fooduct.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.fooduct.R;
import com.example.fooduct.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth mAuth;
    private static final String TAG = "RegisterActivity";
    String email, password, UserName, Number, PhoneNumber;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.RegisterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registration();
            }
        });



        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null &&  !user.getPhoneNumber().isEmpty()) {
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, mAuth.getCurrentUser().getPhoneNumber() + " ", Toast.LENGTH_SHORT).show();
        }
    }


    public void Registration() {
        email = binding.RegisterEmail.getText().toString();
        password = binding.RegisterPassword.getText().toString();
        UserName = binding.RegisterUserName.getText().toString();
        PhoneNumber = binding.RegisterPhoneNumber.getText().toString();
        Number = ("+962") + PhoneNumber;
        mAuth = FirebaseAuth.getInstance();

        AlertDialog.Builder d = new AlertDialog.Builder(RegisterActivity.this)
                .setView(R.layout.progress_dialog);

        AlertDialog dia = d.create();
        dia.show();

        if (email.isEmpty()) {
            binding.RegisterEmail.setError("The Email Is Required");
        }

        if (password.isEmpty()) {
            binding.RegisterPassword.setError("you must Put a Password");
        }

        if (UserName.isEmpty()) {
            binding.RegisterUserName.setError("The User Name Is Required");
        }

        if (PhoneNumber.isEmpty()) {
            binding.RegisterPhoneNumber.setError("The Phone Number Is Required");
        }

        if (!email.isEmpty() && !password.isEmpty() && !UserName.isEmpty() && !PhoneNumber.isEmpty()) {


            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();


                        UserProfileChangeRequest changeRequest = new UserProfileChangeRequest.Builder().setDisplayName(UserName).build();
                        user.updateProfile(changeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {


                                    mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                        @Override
                                        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                                            user.updatePhoneNumber(credential);


                                        }

                                        @Override
                                        public void onVerificationFailed(@NonNull FirebaseException e) {
                                            // This callback is invoked in an invalid request for verification is made,
                                            // for instance if the the phone number format is not valid.
                                            binding.RegisterPhoneNumber.setError(e.getMessage());
                                        }

                                        @Override
                                        public void onCodeSent(@NonNull String verificationId,
                                                               @NonNull PhoneAuthProvider.ForceResendingToken token) {

                                            dia.dismiss();

                                            // Save verification ID and resending token so we can use them later
                                            Intent intent = new Intent(RegisterActivity.this, VerifyActivity.class);
                                            intent.putExtra("VerificationId", verificationId);
                                            intent.putExtra("PhoneNumber", Number);
                                            startActivity(intent);


                                        }
                                    };


                                    PhoneAuthOptions options =
                                            PhoneAuthOptions.newBuilder(mAuth)
                                                    .setPhoneNumber(Number)       // Phone number to verify
                                                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                                    .setActivity(RegisterActivity.this)                 // Activity (for callback binding)
                                                    .setCallbacks(mCallback)          // OnVerificationStateChangedCallbacks
                                                    .build();
                                    PhoneAuthProvider.verifyPhoneNumber(options);
                                }

                            }
                        });
                    }


                }
            });

        }
    }


}
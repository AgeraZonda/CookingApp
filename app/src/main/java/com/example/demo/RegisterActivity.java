package com.example.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEmailView;
    private EditText mPasswordView;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mEmailView =  findViewById(R.id.email_sign_up);
        mPasswordView =  findViewById(R.id.password_sign_up);
        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.sign_up_btn)
        {
            Toast.makeText(RegisterActivity.this,"Hello",Toast.LENGTH_SHORT).show();
            String email = mEmailView.getText().toString().trim();
            String password = mPasswordView.getText().toString().trim();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                try {
                                    throw task.getException();
                                } catch(FirebaseAuthWeakPasswordException e) {
                                    mPasswordView.setError("error_weak_password");
                                    mPasswordView.requestFocus();
                                } catch(FirebaseAuthInvalidCredentialsException e) {
                                    mEmailView.setError("invalid email");
                                    mEmailView.requestFocus();
                                } catch(FirebaseAuthUserCollisionException e) {
                                    mEmailView.setError("user existed");
                                    mEmailView.requestFocus();
                                } catch(Exception e) {
                                    Toast.makeText(RegisterActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                            if(task.isSuccessful())
                            {
                                startActivity(new Intent(RegisterActivity.this, DailyRecipe.class));
                            }
                        }
                    });
        }
        if(v.getId() == R.id.login_link)
        {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        }


    }
}

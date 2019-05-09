package com.example.demo;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private EditText mEmailView;
    private EditText mPasswordView;
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView =  findViewById(R.id.email);
        mPasswordView =  findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
//        Intent i =new Intent(LoginActivity.this, HomeActivity.class);
//        String userid = "IZ5Fq0YaT5e7S5iUgjqKY2NHxFq2";
//        i.putExtra("user_id", userid);
//        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sign_up_link) {
            startActivity(new Intent(this, RegisterActivity.class));
        }
        if (v.getId() == R.id.login_btn) {
            String email = mEmailView.getText().toString().trim();
            String password = mPasswordView.getText().toString().trim();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                String userid = mAuth.getCurrentUser().getUid();
                                i.putExtra("user_id", userid);
                                mAuth.signOut();
                                startActivity(i);
                            } else {
                                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        if (v.getId() == R.id.login_google) {
            mAuth.signOut();
            startActivity(new Intent(LoginActivity.this, GoogleSignInActivity.class));
        }
    }
}

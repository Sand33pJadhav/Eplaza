package com.example.eplaza;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button loginbtn;

    private FirebaseAuth firebaseAuth;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";
    private ProgressBar progressBar;
    private FirebaseUser user;


    @Override
    protected void onStart(){
        super.onStart();
        FirebaseApp.initializeApp(getApplicationContext());
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null)
        {
            startActivity(new Intent(login.this, Home.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.login_email);
        pass = findViewById(R.id.login_password);
        loginbtn = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressBar2);

        firebaseAuth = FirebaseAuth.getInstance();

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkinput();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_Email_Password();
            }
        });
    }

        public void go_to_signup(View view)
        {
            Intent intent = new Intent(login.this, signup.class);
            startActivity(intent);
        }

    private void checkinput(){
        if(!TextUtils.isEmpty(email.getText().toString())){
            if(!TextUtils.isEmpty(pass.getText().toString())){
                loginbtn.setEnabled(true);
            }
            else {
                loginbtn.setEnabled(false);
            }

        }else{
            loginbtn.setEnabled(false);
        }
    }

    public void forgot_pass(View view){
        Intent mainIntent = new Intent(login.this, ForgotPassword.class);
        startActivity(mainIntent);
        login.this.finish();
    }

    private void check_Email_Password(){
        if(email.getText().toString().matches(emailPattern)){
            if(pass.length()>=4){

                progressBar.setVisibility(View.VISIBLE);
                loginbtn.setEnabled(false);
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent mainIntent = new Intent(login.this, Home.class);
                                    startActivity(mainIntent);
                                    login.this.finish();

                                }
                                else {
                                    progressBar.setVisibility(View.INVISIBLE);
                                    loginbtn.setEnabled(true);
                                    String error = task.getException().getMessage();
                                    Toast.makeText(login.this,error,Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
            else{
                Toast.makeText(login.this,"Incorrect Password!",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(login.this,"Incorrect Email!",Toast.LENGTH_LONG).show();
        }

    }

}

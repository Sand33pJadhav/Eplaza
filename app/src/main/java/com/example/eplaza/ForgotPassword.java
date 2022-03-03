package com.example.eplaza;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private EditText reg_email;
    private Button resetbtn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        reg_email = findViewById(R.id.reg_email);
        resetbtn = findViewById(R.id.pass_reset);
        firebaseAuth = FirebaseAuth.getInstance();

        reg_email.addTextChangedListener(new TextWatcher() {
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


        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetbtn.setEnabled(false);

                firebaseAuth.sendPasswordResetEmail(reg_email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ForgotPassword.this,"Reset password link is sent successfully... Check inbox!",Toast.LENGTH_LONG).show();

                                }
                                else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(ForgotPassword.this,error,Toast.LENGTH_LONG).show();
                                }
                                resetbtn.setEnabled(true);
                            }
                        });
            }
        });

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(ForgotPassword.this, login.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }
    public void go_to_login(View view)
    {
        Intent intent = new Intent(ForgotPassword.this, login.class);
        startActivity(intent);
    }

    private void checkinput() {
        if (TextUtils.isEmpty(reg_email.getText())) {
            resetbtn.setEnabled(false);

        } else {
            resetbtn.setEnabled(true);
        }
    }


}

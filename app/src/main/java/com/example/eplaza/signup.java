package com.example.eplaza;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private EditText name;
    private EditText email;
    private EditText password;

    private Button signupbtn;
    private ProgressBar progressBar;

    private TextView logintext;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+.[a-z]+";

    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore firebaseFirestore;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        email = findViewById(R.id.signup_email);
        name = findViewById(R.id.signup_name);
        password = findViewById(R.id.signup_password);
        signupbtn = findViewById(R.id.login_button);
        logintext = findViewById(R.id.go_login);
        progressBar = findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        firebaseFirestore = FirebaseFirestore.getInstance();


//        startActivityForResult(AuthUI.getInstance()
//                .createSignInIntentBuilder()
//                .setProviders(
//                        AuthUI.GOOGLE_PROVIDER)
//                        .build(),1);

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
        name.addTextChangedListener(new TextWatcher() {
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
        password.addTextChangedListener(new TextWatcher() {
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

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
                check_Email_Password();
            }
        });


    }

    public void go_to_login(View view)
    {
        Intent intent = new Intent(signup.this, login.class);
        startActivity(intent);
    }

    private void checkinput(){
        if(!TextUtils.isEmpty((name.getText()))){
            if(!TextUtils.isEmpty((email.getText()))){
                if(!TextUtils.isEmpty((password.getText())) && password.length()>=4){
                    signupbtn.setEnabled(true);

                }
                else{
                    signupbtn.setEnabled(false);
                }
            }else{
                signupbtn.setEnabled(false);
            }
        }
        else{
            signupbtn.setEnabled(false);
        }
    }

    private void check_Email_Password(){
        if(email.getText().toString().matches(emailPattern)){

            progressBar.setVisibility(View.VISIBLE);
            signupbtn.setEnabled(false);

            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                String userid = firebaseUser.getUid();

                                HashMap<String,String> userdata = new HashMap<>();
                                userdata.put("name",name.getText().toString());
                                userdata.put("userId",userid);
                                userdata.put("email",email.getText().toString());
                                userdata.put("password",password.getText().toString());

//                                reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
//
//                                reference.setValue(userdata, new DatabaseReference.CompletionListener() {
//                                    @Override
//                                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//                                        if(databaseError!=null){
//                                            Log.d("Database Error",databaseError.getMessage());
//                                        }
//                                        else {
//                                            progressBar.setVisibility(View.INVISIBLE);
//                                            startActivity(new Intent(signup.this,Home.class));
//                                            finish();
//                                        }
//                                    }
//                                });

                                firebaseFirestore.collection("Users")
                                        .add(userdata)
                                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                if(task.isSuccessful()){
                                                    Intent mainIntent = new Intent(signup.this, Home.class);
                                                    startActivity(mainIntent);
                                                    signup.this.finish();

                                                }
                                                else {
                                                    progressBar.setVisibility(View.INVISIBLE);
                                                    signupbtn.setEnabled(true);
                                                    String error = task.getException().getMessage();
                                                    Toast.makeText(signup.this,error, Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
//                                Intent mainIntent = new Intent(signup.this, Home.class);
//                                startActivity(mainIntent);
//                                signup.this.finish();

                            }
                            else {
                                progressBar.setVisibility(View.INVISIBLE);
                                signupbtn.setEnabled(true);
                                String error = task.getException().getMessage();
                                Toast.makeText(signup.this,error, Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }
        else{
                email.setError("Invalid email");
        }

    }
}


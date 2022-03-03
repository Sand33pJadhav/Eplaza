package com.example.eplaza;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

//    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        firebaseAuth = FirebaseAuth.getInstance();

//        super.onStart();
//        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
//        if(currentUser==null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent homeIntent = new Intent(MainActivity.this, login.class);
                    startActivity(homeIntent);
                    finish();

                }
            },SPLASH_TIME_OUT);
//        }
//        else {
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent homeIntent = new Intent(MainActivity.this, Home.class);
//                    startActivity(homeIntent);
//                    finish();
//
//                }
//            },SPLASH_TIME_OUT);
//        }

    }

}

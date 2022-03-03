package com.example.eplaza;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class MyProfile extends AppCompatActivity {

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;

    TextView name,email;
    EditText phoneno, location;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        name = findViewById(R.id.product_name);
        email = findViewById(R.id.profile_mail);
        phoneno = findViewById(R.id.phone);
        location = findViewById(R.id.location);
        save = findViewById(R.id.profile_save);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        email.setText(firebaseUser.getEmail());
        String id = firebaseUser.getUid();

//        db.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
//            @Override
//            public void onEvent(QuerySnapshot querySnapshot, FirebaseFirestoreException e) {
//                if (e != null) {
//                    Log.d("ERROR", e.getMessage());
//                    return;
//                }
//                for(DocumentChange documentChange : querySnapshot.getDocumentChanges()) {
//                    //Toast.makeText(Home.this, "Current data:" + documentSnapshot.getData(), Toast.LENGTH_SHORT).show();
////                    Map<String, Object> userData = documentSnapshot.getData();
//                    String username = documentChange.getDocument().getData().get("name").toString();
//                    name.setText(username);
//                }
//            }
//        });

        

    }
}

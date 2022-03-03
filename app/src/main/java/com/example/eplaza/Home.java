package com.example.eplaza;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import com.example.eplaza.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ViewFlipper vflipper;
    GridView gridView;
    ImageButton chair,table,sofa,chandelier;
    TextView email,name;
    ImageView image;

    int [] grid_images = {R.drawable.sofa3,R.drawable.table1,R.drawable.c2,R.drawable.chair3,R.drawable.sofa1,R.drawable.table2};
    String [] product_names = {"Black Sofa", "Wooden Table","Chandelier","Chair","Sofa","Table2"};
    int [] product_index = {1,2,3,4,5,6};
    int [] product_new_price = {7500,5300,13000,1200,8300,4500};
    int [] product_old_price = {8200,7000,15500,1500,10000,5300};

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        //slider code
        int images[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5};

        vflipper = findViewById(R.id.flipper);
        chair = findViewById(R.id.chair);
        table = findViewById(R.id.table);
        sofa = findViewById(R.id.sofa);
        chandelier = findViewById(R.id.chandelier);

        chair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,chairs.class);
                startActivity(intent);
            }
        });

        sofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,sofa.class);
                startActivity(intent);
            }
        });

        table.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,table.class);
                startActivity(intent);
            }
        });

        chandelier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this,chandelier.class);
                startActivity(intent);
            }
        });

        for(int i=0;i<images.length;i++){
            flipperImage(images[i]);
        }

        //Grid view code

        gridView = findViewById(R.id.gridd_view);

        MainAdapter adapter = new MainAdapter(Home.this,grid_images,product_names,product_new_price,product_old_price);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Home.this, ProductDetails.class);
                intent.putExtra("product_name",product_names[position]);
                intent.putExtra("product_index",product_index[position]);
                intent.putExtra("product_image",grid_images[position]);
                intent.putExtra("product_price",product_new_price[position]);
                startActivity(intent);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);

        email = headerView.findViewById(R.id.nav_email);
        name = headerView.findViewById(R.id.nav_name);
        image = headerView.findViewById(R.id.profilepic);


        email.setText(firebaseUser.getEmail());
        String id = firebaseUser.getUid();

        db.collection("Users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot querySnapshot, FirebaseFirestoreException e) {
                Log.d("logs", "inside");
                if (e != null) {
                    Log.d("ERROR", e.getMessage());
                    return;
                }
                for(DocumentChange documentChange : querySnapshot.getDocumentChanges()) {
                    //Toast.makeText(Home.this, "Current data:" + documentSnapshot.getData(), Toast.LENGTH_SHORT).show();
//                    Map<String, Object> userData = documentSnapshot.getData();
                    String username = documentChange.getDocument().getData().get("name").toString();
                    Log.e("logs", username);
                    name.setText(username);
                }
            }
        });

        navigationView.getMenu().getItem(0).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_icon) {
            return true;
        }
        else if(id == R.id.bell_icon){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_orders) {
            startActivity(new Intent(Home.this, MyOrders.class));
        } else if (id == R.id.nav_cart) {
            startActivity(new Intent(Home.this, Cart.class));

        } else if (id == R.id.nav_pay) {
            startActivity(new Intent(Home.this, EplazaPay.class));

        } else if (id == R.id.nav_account) {
            startActivity(new Intent(Home.this, MyProfile.class));

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = new Intent(Home.this, login.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void flipperImage(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        vflipper.addView(imageView);
        vflipper.setFlipInterval(3000);
        vflipper.setAutoStart(true);

        vflipper.setInAnimation(this,  android.R.anim.slide_in_left);
        vflipper.setOutAnimation(this, android.R.anim.slide_out_right);
    }


}

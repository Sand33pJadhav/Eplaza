package com.example.eplaza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

public class chandelier extends AppCompatActivity {

    RelativeLayout one,two,three,four,five,six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chandelier);

        int product_images[] = {R.drawable.c1, R.drawable.c2, R.drawable.chan3, R.drawable.chan4,R.drawable.chan5 ,R.drawable.chan6};
        String [] product_names = {"Wooden Chair","Lounge Chair","Side Chair","Boden Chair","Hayden Chair","Cello Chair"};
        int [] product_index = {3,33,6,6,6,6};
        int [] product_price = {1800,1500,1200,900,1150,1500};

        one = findViewById(R.id.cone);
        two = findViewById(R.id.ctwo);
        three = findViewById(R.id.cthree);
        four = findViewById(R.id.cfour);
        five = findViewById(R.id.cfive);
        six = findViewById(R.id.csix);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chandelier.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[0]);
                intent.putExtra("product_index",product_index[0]);
                intent.putExtra("product_image",product_images[0]);
                intent.putExtra("product_price",product_price[0]);
                startActivity(intent);
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chandelier.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[1]);
                intent.putExtra("product_index",product_index[1]);
                intent.putExtra("product_image",product_images[1]);
                intent.putExtra("product_price",product_price[1]);
                startActivity(intent);
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chandelier.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[2]);
                intent.putExtra("product_index",product_index[2]);
                intent.putExtra("product_image",product_images[2]);
                intent.putExtra("product_price",product_price[2]);
                startActivity(intent);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chandelier.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[3]);
                intent.putExtra("product_index",product_index[3]);
                intent.putExtra("product_image",product_images[3]);
                intent.putExtra("product_price",product_price[3]);
                startActivity(intent);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chandelier.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[4]);
                intent.putExtra("product_index",product_index[4]);
                intent.putExtra("product_image",product_images[4]);
                intent.putExtra("product_price",product_price[4]);
                startActivity(intent);
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chandelier.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[5]);
                intent.putExtra("product_index",product_index[5]);
                intent.putExtra("product_image",product_images[5]);
                intent.putExtra("product_price",product_price[5]);
                startActivity(intent);
            }
        });





    }
}

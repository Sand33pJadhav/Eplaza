package com.example.eplaza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

public class chairs extends AppCompatActivity {

    RelativeLayout one,two,three,four,five,six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chairs);

        int product_images[] = {R.drawable.chair1, R.drawable.chair2, R.drawable.chair3, R.drawable.chair4,R.drawable.chair5 ,R.drawable.chair6};
        String [] product_names = {"Wooden Chair","Lounge Chair","Side Chair","Boden Chair","Hayden Chair","Cello Chair"};
        int [] product_index = {4,44,6,6,6,6};
        int [] product_price = {1800,1500,1200,900,1150,1500};

        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chairs.this,ProductDetails.class);
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
                Intent intent = new Intent(chairs.this,ProductDetails.class);
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
                Intent intent = new Intent(chairs.this,ProductDetails.class);
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
                Intent intent = new Intent(chairs.this,ProductDetails.class);
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
                Intent intent = new Intent(chairs.this,ProductDetails.class);
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
                Intent intent = new Intent(chairs.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[5]);
                intent.putExtra("product_index",product_index[5]);
                intent.putExtra("product_image",product_images[5]);
                intent.putExtra("product_price",product_price[5]);
                startActivity(intent);
            }
        });





    }
}

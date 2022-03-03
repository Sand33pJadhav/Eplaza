package com.example.eplaza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

public class table extends AppCompatActivity {

    RelativeLayout one,two,three,four,five,six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        int product_images[] = {R.drawable.table1, R.drawable.table2, R.drawable.table3, R.drawable.table4,R.drawable.table5 ,R.drawable.table6};
        String [] product_names = {"Wooden Table","Study Table","Work Table","Console Table","Work Table2","Tea Table"};
        int [] product_index = {2,22,6,6,6,6};
        int [] product_price = {2300,1900,1750,1500,2100,1200};

        one = findViewById(R.id.tone);
        two = findViewById(R.id.ttwo);
        three = findViewById(R.id.tthree);
        four = findViewById(R.id.tfour);
        five = findViewById(R.id.tfive);
        six = findViewById(R.id.tsix);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(table.this,ProductDetails.class);
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
                Intent intent = new Intent(table.this,ProductDetails.class);
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
                Intent intent = new Intent(table.this,ProductDetails.class);
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
                Intent intent = new Intent(table.this,ProductDetails.class);
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
                Intent intent = new Intent(table.this,ProductDetails.class);
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
                Intent intent = new Intent(table.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[5]);
                intent.putExtra("product_index",product_index[5]);
                intent.putExtra("product_image",product_images[5]);
                intent.putExtra("product_price",product_price[5]);
                startActivity(intent);
            }
        });





    }
}

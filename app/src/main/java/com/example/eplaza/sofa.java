package com.example.eplaza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

public class sofa extends AppCompatActivity {

    RelativeLayout one,two,three,four,five,six;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofa);

        int product_images[] = {R.drawable.sofa3, R.drawable.sofa2, R.drawable.sofa1, R.drawable.sofa4,R.drawable.sofa5 ,R.drawable.sofa6};
        String [] product_names = {"2 Seater Gray Sofa","L Shape Sofa","Fabric SOfa","lack Leather Sofa","Classic Sofa","Traditional Sofa"};
        int [] product_index = {1,11,6,6,6,6};
        int [] product_price = {6000,15000,10000,12000,8500,13500};

        one = findViewById(R.id.sone);
        two = findViewById(R.id.stwo);
        three = findViewById(R.id.sthree);
        four = findViewById(R.id.sfour);
        five = findViewById(R.id.sfive);
        six = findViewById(R.id.ssix);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sofa.this,ProductDetails.class);
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
                Intent intent = new Intent(sofa.this,ProductDetails.class);
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
                Intent intent = new Intent(sofa.this,ProductDetails.class);
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
                Intent intent = new Intent(sofa.this,ProductDetails.class);
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
                Intent intent = new Intent(sofa.this,ProductDetails.class);
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
                Intent intent = new Intent(sofa.this,ProductDetails.class);
                intent.putExtra("product_name",product_names[5]);
                intent.putExtra("product_index",product_index[5]);
                intent.putExtra("product_image",product_images[5]);
                intent.putExtra("product_price",product_price[5]);
                startActivity(intent);
            }
        });
    }
}

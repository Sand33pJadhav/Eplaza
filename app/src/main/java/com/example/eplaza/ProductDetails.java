package com.example.eplaza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity {

    TextView product_name;
    TextView product_price;
    ImageView productimage;

    int product_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);


        productimage = findViewById(R.id.product_image);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            int product_image = bundle.getInt("product_image");
            productimage.setImageResource(product_image);
        }

        product_name = findViewById(R.id.product_name);
        product_price = findViewById(R.id.product_price);


        Intent intent = getIntent();
        String str = intent.getStringExtra("product_name");
        int str2 = intent.getIntExtra("product_price",0);
        product_index = intent.getIntExtra("product_index",0);

        product_name.setText(str);
        product_price.setText("Rs."+str2+"/-");
    }

    public void opencam(View view){

        if(product_index ==  1)
        {
            Intent intent = new Intent(ProductDetails.this, SceneformActivity.class);
            startActivity(intent);
        }
        else if(product_index ==  11)
        {
            Intent intent = new Intent(ProductDetails.this, SceneformActivity2.class);
            startActivity(intent);
        }
        else if(product_index ==  2)
        {
            Intent intent = new Intent(ProductDetails.this, Sceneform_table_activity.class);
            startActivity(intent);
        }
        else if(product_index ==  22)
        {
            Intent intent = new Intent(ProductDetails.this, Sceneform_table2_activity.class);
            startActivity(intent);
        }
        else if(product_index ==  3)
        {
            Intent intent = new Intent(ProductDetails.this, Sceneform_chandelier_activity.class);
            startActivity(intent);
        }
        else if(product_index ==  33)
        {
            Intent intent = new Intent(ProductDetails.this, Sceneform_chandelier2_activity.class);
            startActivity(intent);
        }
        else if(product_index ==  4)
        {
            Intent intent = new Intent(ProductDetails.this, Sceneform_chair_activity.class);
            startActivity(intent);
        }
        else if(product_index ==  44)
        {
            Intent intent = new Intent(ProductDetails.this, Sceneform_chair2_activity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(ProductDetails.this,"No virtual model is available for this item.",Toast.LENGTH_LONG).show();
        }
    }
}

package com.example.eplaza;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private int[] grid_images;
    private String [] product_names;
    private int [] product_new_price;
    private int [] product_old_price;

    public MainAdapter(Context c, int[] grid_images, String[] product_names, int[] product_new_price, int[] product_old_price) {
        context = c;
        this.grid_images = grid_images;
        this.product_names = product_names;
        this.product_new_price =product_new_price;
        this.product_old_price = product_old_price;


    }


    @Override
    public int getCount() {
       return grid_images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(inflater==null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        if(convertView==null){
            convertView = inflater.inflate(R.layout.row_item,null);
        }

        ImageView imageView = convertView.findViewById(R.id.image_view);
        TextView product_name = convertView.findViewById(R.id.product_name);
        TextView prod_new_price = convertView.findViewById(R.id.new_price);
        TextView prod_old_price = convertView.findViewById(R.id.old_price);

        imageView.setImageResource(grid_images[position]);
        product_name.setText(product_names[position]);
        prod_new_price.setText("Rs. "+product_new_price[position]+"/-");
        prod_old_price.setText(("Rs."+product_old_price[position])+"/-");
        prod_old_price.setPaintFlags(prod_old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        return convertView ;
    }
}

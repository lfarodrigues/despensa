package com.example.despensa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.despensa.R;
import com.example.despensa.objects.Product;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {
    private Context context;
    private int resource;
    public ProductListAdapter(@NonNull Context context, int resource, @NonNull List<Product> products) {
        super(context, resource, products);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        Product product = getItem(position);

        if (product != null) {
            ImageView productImage = itemView.findViewById(R.id.productImage);
            TextView productName = itemView.findViewById(R.id.productName);

            productImage.setImageResource(product.getImageResourceId());
            productName.setText(product.getName());
        }

        return itemView;
    }
}

package com.example.administrator.mymapapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GridViewAdapter extends ArrayAdapter<Seller> {
    public GridViewAdapter(Context context, int resource, List<Seller> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (null == v) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.grid_item, null);
            Seller product = getItem(position);
            //ImageView img = (ImageView) v.findViewById(R.id.imageView);
            TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
            TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);

            //img.setImageResource(product.getImageId());
            txtTitle.setText(txtTitle.getText() + product.getName());
            txtDescription.setText(txtDescription.getText() +product.getAddress());

            return v;
        }
        return convertView;

    }
}
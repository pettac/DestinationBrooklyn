package com.csthack.beinnovative.destination_brooklyn;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.csthack.beinnovative.destination_brooklyn.shopClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berta on 4/9/2016.
 */
public class CustomListAdapter extends ArrayAdapter<shopClass> {
    ArrayList<shopClass> shopObjects;
    private final int resource;

    public CustomListAdapter(Context context, ArrayList<shopClass> PshopObjects, int resource) {
        super(context, R.layout.list_row, PshopObjects);
        shopObjects = PshopObjects;
        this.resource = resource;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // We need to get the best view (re-used if possible) and then
        // retrieve its corresponding ViewHolder, which optimizes lookup efficiency
        View view = getWorkingView(convertView);
        ViewHolder viewHolder = getViewHolder(view);
        shopClass shop = getItem(position);
        System.out.println("Item Position: "+getItem(position));

//        if (view == null) {
//            view = getLayoutInflater().inflate(R.layout.list_row, parent, false);
//        }

        if (shopObjects == null) {
            System.out.println("it is null on CustomListarrya");
        }

        // Setting the title view is straightforward
        viewHolder.titleView.setText(shop.getName());
        viewHolder.subTitleView.setText(shop.getDescription());
        viewHolder.imageView.setBackground(shop.image);

        return view;
    }


    private View getWorkingView(final View convertView) {
        // The workingView is basically just the convertView re-used if possible
        // or inflated new if not possible
        View workingView = null;

        if(null == convertView) {
            final Context context = getContext();
            final LayoutInflater inflater = (LayoutInflater)context.getSystemService
                    (Context.LAYOUT_INFLATER_SERVICE);

            workingView = inflater.inflate(resource, null);
        } else {
            workingView = convertView;
        }

        return workingView;
    }

    private ViewHolder getViewHolder(final View workingView) {
        // The viewHolder allows us to avoid re-looking up view references
        // Since views are recycled, these references will never change
        final Object tag = workingView.getTag();
        ViewHolder viewHolder = null;


        if(null == tag || !(tag instanceof ViewHolder)) {
            viewHolder = new ViewHolder();

            viewHolder.titleView = (TextView) workingView.findViewById(R.id.title);
            viewHolder.subTitleView = (TextView) workingView.findViewById(R.id.description);
            viewHolder.imageView = (LinearLayout) workingView.findViewById(R.id.bkgImage);

            workingView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) tag;
        }

        return viewHolder;
    }

    /**
     * ViewHolder allows us to avoid re-looking up view references
     * Since views are recycled, these references will never change
     */
    private static class ViewHolder {
        public TextView titleView;
        public TextView subTitleView;
        public LinearLayout imageView;
    }

}
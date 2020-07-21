package com.sample.ui;

import android.widget.Filter;

import java.util.ArrayList;

public class CustomFilter extends Filter {
    Adapter adapter;
    private ArrayList<Item> filterList;
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results= new FilterResults();

        if(constraint != null && constraint.length()>0)
        {
            constraint = constraint.toString().toUpperCase();
            ArrayList<Item> filterImages = new ArrayList<>();
            for(Item i : filterList)
            {
                if(i.getImageUrl().contains(constraint))
                {
                    filterImages.add(i);
                }

            }
        }
        return null;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

    }
}

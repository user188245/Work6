package com.example.user.work5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017-04-13.
 */

public class MyAdapter extends BaseAdapter implements Filterable {
    Context context;
    List<Restaurant> restaurantList;
    List<Restaurant> filteredList;
    int[] picture = {R.drawable.chicken,R.drawable.pizza,R.drawable.hamburger};
    static boolean isDeletionMode = false;
    Filter filter;

    public MyAdapter(Context context, final List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
        this.filteredList = restaurantList;
        filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults fr = new FilterResults();
                if(constraint == null || constraint.length() == 0) {
                    fr.values = restaurantList;
                    fr.count = restaurantList.size();
                }
                else{
                    List<Restaurant> list = filtering(constraint);
                    fr.values = list;
                    fr.count = list.size();
                }
                return fr;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (List<Restaurant>) results.values;
                if(results.count > 0)
                    notifyDataSetChanged();
                else
                    notifyDataSetInvalidated();
            }
        };
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return filteredList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view,null);
        Restaurant r = filteredList.get(position);
        ((ImageView)convertView.findViewById(R.id.imageView_listView)).setImageResource(picture[r.getCategory()]);
        ((TextView)convertView.findViewById(R.id.textViewName_listView)).setText(r.getName());
        ((TextView)convertView.findViewById(R.id.textViewTel_listView)).setText(r.getTel());
        CheckBox checkBox = ((CheckBox)convertView.findViewById(R.id.checkbox));
        if(isDeletionMode)
            checkBox.setVisibility(CheckBox.VISIBLE);
        else
            checkBox.setVisibility(CheckBox.INVISIBLE);
        r.isDeletionTarget = checkBox.isChecked()?true:false;
        return convertView;
    }


    @Override
    public Filter getFilter() {
        return this.filter;
    }

    private ArrayList<Restaurant> filtering(CharSequence s){
        ArrayList<Restaurant> list = new ArrayList<>();
        for (Restaurant r : restaurantList) {
            if (r.getName().contains(s))
                list.add(r);
        }
        return list;
    }
}

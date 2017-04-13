package com.example.user.work5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by user on 2017-04-13.
 */

public class MyAdapter extends BaseAdapter{
    Context context;
    List<Restaurant> restaurantList;
    int[] picture = {R.drawable.chicken,R.drawable.pizza,R.drawable.hamburger};
    static boolean isDeletionMode = false;

    public MyAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view,null);
        Restaurant r = restaurantList.get(position);
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


}

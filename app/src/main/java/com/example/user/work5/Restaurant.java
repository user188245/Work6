package com.example.user.work5;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;


public class Restaurant implements Parcelable{
    private int index;
    private int category;
    private String name;
    private String tel;
    private Menu[] menu;
    private String homepage;
    private String date;

    public int getIndex() {
        return index;
    }

    public int getCategory() {
        return category;
    }

    public Restaurant(int index, String name, String tel, String homepage, Menu menu[], int category) {
        this.index = index;
        this.name = name;
        this.tel = tel;
        this.homepage = homepage;
        this.category = category;
        Calendar c = Calendar.getInstance();
        this.date = c.get(Calendar.YEAR)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.DATE);
        this.menu = menu;
    }

    protected Restaurant(Parcel in){
        index = in.readInt();
        name = in.readString();
        tel = in.readString();
        homepage = in.readString();
        category = in.readInt();
        date = in.readString();
        menu = (Menu[])in.readParcelableArray(Menu.class.getClassLoader());
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    @Override
    public String toString() {
        return "#" + index + ":" + name;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public Menu[] getMenu() {
        return menu;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getDate() {
        return date;
    }


    public int getCategoryImage() {
        switch (this.category) {
            case 0:
                return R.drawable.chicken;
            case 1:
                return R.drawable.pizza;
            case 2:
                return R.drawable.hamburger;
        }
        return 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(index);
        dest.writeString(name);
        dest.writeString(tel);
        dest.writeString(homepage);
        dest.writeInt(category);
        dest.writeString(date);
        dest.writeParcelableArray(menu,flags);
    }
}

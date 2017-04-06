package com.example.user.work5;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * Created by user on 2017-04-06.
 */

public class Restaurant implements Parcelable {
    private int index;
    private Food category;
    private String name;
    private String tel;
    private Menu[] menu;
    private String homepage;
    private Calendar date;

    public int getIndex() {
        return index;
    }

    public Food getCategory() {
        return category;
    }

    public Restaurant(int index, String name, String tel, String homepage, Menu menus[], Food category) {
        this.index = index;
        this.name = name;
        this.tel = tel;
        this.homepage = homepage;
        this.category = category;
        date = Calendar.getInstance();
        menu = menus;
    }

    protected Restaurant(Parcel in) {
        index = in.readInt();
        name = in.readString();
        tel = in.readString();
        homepage = in.readString();
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
        return name;
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

    public Calendar getDate() {
        return date;
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
    }

    public static enum Food {
        Chicken,
        Pizza,
        Hamburger;

        public int getFoodImage() {
            switch (ordinal()) {
                case 0:
                    return R.drawable.chicken;
                case 1:
                    return R.drawable.pizza;
                case 2:
                    return R.drawable.hamburger;
            }
            return 0;
        }
    }

}

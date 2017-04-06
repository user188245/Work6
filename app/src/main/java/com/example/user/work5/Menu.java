package com.example.user.work5;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 2017-04-06.
 */

public class Menu implements Parcelable{
    private String name;
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    protected Menu(Parcel in){
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    @Override
    public String toString() {
        return name+"("+price+")";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }
}

package com.project.coffeeapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Category implements Parcelable {
    private int id;
    private String name;
    private String image;

    public Category() {
    }

    public Category(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    protected Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(image);
    }
}

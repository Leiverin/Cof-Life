package com.project.coffeeapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Coffee implements Parcelable {
    private int id;
    private String name;
    private String image;
    private String description;
    private double price;
    private int total;
    private double discount;
    private int outstanding;

    public Coffee(int id, String name, String image, String description, double price, int total, double discount, int outstanding) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.total = total;
        this.discount = discount;
        this.outstanding = outstanding;
    }

    protected Coffee(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        description = in.readString();
        price = in.readDouble();
        total = in.readInt();
        discount = in.readDouble();
        outstanding = in.readInt();

    }

    public static final Creator<Coffee> CREATOR = new Creator<Coffee>() {
        @Override
        public Coffee createFromParcel(Parcel in) {
            return new Coffee(in);
        }

        @Override
        public Coffee[] newArray(int size) {
            return new Coffee[size];
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String content) {
        this.description = content;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(int outstanding) {
        this.outstanding = outstanding;
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
        parcel.writeString(description);
        parcel.writeDouble(price);
        parcel.writeInt(total);
        parcel.writeDouble(discount);
        parcel.writeInt(outstanding);
    }
}

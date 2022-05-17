package com.project.coffeeapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private int id;
    private int idProduct;
    private String name;
    private String image;
    private String description;
    private double price;
    private int quantity;
    private int totalQuantity;
    private String orderDate;
    private String username;

    public Cart(int id, int idProduct, String name, String image, String description, double price, int quantity, int totalQuantity) {
        this.id = id;
        this.idProduct = idProduct;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.totalQuantity = totalQuantity;
    }

    public Cart(int quantity, String orderDate, String username, int idProduct) {
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.username = username;
        this.idProduct = idProduct;
    }

    protected Cart(Parcel in) {
        id = in.readInt();
        idProduct = in.readInt();
        name = in.readString();
        image = in.readString();
        description = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
        totalQuantity = in.readInt();
        orderDate = in.readString();
        username = in.readString();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(idProduct);
        parcel.writeString(name);
        parcel.writeString(image);
        parcel.writeString(description);
        parcel.writeDouble(price);
        parcel.writeInt(quantity);
        parcel.writeInt(totalQuantity);
        parcel.writeString(orderDate);
        parcel.writeString(username);
    }
}

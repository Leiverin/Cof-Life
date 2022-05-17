package com.project.coffeeapp.models;

import java.util.List;

public class ItemHome {
    private int id;
    private String title;
    private List<Coffee> mListCoffee;

    public ItemHome(int id, String title,List<Coffee> mListCoffee) {
        this.id = id;
        this.title = title;
        this.mListCoffee = mListCoffee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Coffee> getListCoffee() {
        return mListCoffee;
    }

    public void setListCoffee(List<Coffee> mListCoffee) {
        this.mListCoffee = mListCoffee;
    }
}

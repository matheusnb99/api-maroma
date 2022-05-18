package com.example.apimaroma.products;

import com.example.apimaroma.categories.CategoryBean;

import java.util.List;

public class ProductBean {
    private String id = "";
    private String name = "";
    private String description = "";


    private List<CategoryBean> categories;
    private int stock = 0;
    private double price = 0;
    private float grade = 0;
    private int basket = 0;
    // @Exclude


    public ProductBean() {
    }

    public ProductBean(String name, String description, List<CategoryBean> categories, int stock, double price, float grade, int basket) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.grade = grade;
        this.basket = basket;
    }

    public ProductBean(String id, String name, String description, List<CategoryBean> categories, int stock, double price, float grade, int basket) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.grade = grade;
        this.basket = basket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<CategoryBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryBean> categories) {
        this.categories = categories;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public int getBasket() {
        return basket;
    }

    public void setBasket(int basket) {
        this.basket = basket;
    }
}

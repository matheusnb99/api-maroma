package com.example.apimaroma.products;

import com.example.apimaroma.categories.CategoryBean;
import com.example.apimaroma.categories.CategoryService;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProductBean {
    private String id = "";
    private String name = "";
    private String description = "";


    private List<DocumentReference> categories;
    private int stock = 0;
    private double price = 0;
    private float grade = 0;
    private int basket = 0;
    // @Exclude


    public ProductBean() {
    }

    public ProductBean(String name, String description, List<DocumentReference> categories, int stock, double price, float grade, int basket) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.grade = grade;
        this.basket = basket;
    }

    public ProductBean(String id, String name, String description, List<DocumentReference> categories, int stock, double price, float grade, int basket) {
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
        List<CategoryBean> listCategories = new ArrayList<>();
        for (DocumentReference category : categories) {
            try {
                CategoryBean cat = (new CategoryService()).getCategory(category.getId());
                listCategories.add(cat);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return listCategories;
    }



    public void setCategories(List<DocumentReference> categories) {
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

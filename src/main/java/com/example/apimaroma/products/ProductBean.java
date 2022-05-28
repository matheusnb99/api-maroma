package com.example.apimaroma.products;

import com.example.apimaroma.categories.CategoryBean;
import com.example.apimaroma.categories.CategoryService;
import com.example.apimaroma.ratings.RatingBean;
import com.example.apimaroma.ratings.RatingService;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class ProductBean {
    private String id = "";
    private String name = "";
    private String description = "";


    private List<DocumentReference> categories;

    public List<RatingBean> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingBean> ratings) {
        this.ratings = ratings;
    }

    private List<RatingBean> ratings;
    private int stock = 0;
    private double price = 0;
    private float grade = 0;
    private int basket = 0;


    // > JAVA SE 9
    private static final Set<String> DATABASE_KEYS = Set.of(
            "color", "description", "grade", "id", "inBasket", "name", "price", "stock"
    );

    public static Set<String> getDatabaseKeys() {
        return DATABASE_KEYS;
    }

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

    public ProductBean(String id, String name, String description, List<DocumentReference> categories, int stock, double price, float grade, int basket, List<RatingBean> ratings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.grade = grade;
        this.basket = basket;
        this.ratings = ratings;
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
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return listCategories;
    }

//    public List<RatingBean> getRatings() {
//        List<RatingBean> listRating = new ArrayList<>();
//        for (DocumentReference rating : ratings) {
//            try {
//                RatingBean rat = (new RatingService()).getRating(rating.getId());
//                listRating.add(rat);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return listRating;
//    }



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

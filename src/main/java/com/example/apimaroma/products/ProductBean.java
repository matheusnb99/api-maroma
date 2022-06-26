package com.example.apimaroma.products;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import com.example.apimaroma.categories.CategoryBean;
import com.example.apimaroma.categories.CategoryService;
import com.example.apimaroma.ratings.RatingBean;
import com.google.cloud.firestore.DocumentReference;

public class ProductBean {
    private String id = "";
    private List<String> images;
    private String name = "";
    private String description = "";
    private List<RatingBean> ratings;
    private Integer stock = 0;
    private Double price = 0.0;
    private Float grade = 0F;
    private Integer basket = 0;
    private List<DocumentReference> categories;

    public List<RatingBean> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingBean> ratings) {
        this.ratings = ratings;
    }

    // > JAVA SE 9
    private static final Set<String> DATABASE_KEYS = Set.of(
            "color", "description", "grade", "id", "inBasket", "name", "price", "stock");

    public static Set<String> getDatabaseKeys() {
        return DATABASE_KEYS;
    }

    // @Exclude

    public ProductBean() {
    }
    public ProductBean(String id) {
        this.id=id;
    }

    public ProductBean(String name, String description, List<DocumentReference> categories, Integer stock, Double price,
            List<String> images,
            Float grade, Integer basket) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.grade = grade;
        this.basket = basket;
        this.images = images;
    }

    public ProductBean(String id, String name, String description, List<DocumentReference> categories, Integer stock,
            List<String> images,
            Double price, Float grade, Integer basket, List<RatingBean> ratings) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.stock = stock;
        this.price = price;
        this.grade = grade;
        this.basket = basket;
        this.ratings = ratings;
        this.images = images;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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

    // public List<RatingBean> getRatings() {
    // List<RatingBean> listRating = new ArrayList<>();
    // for (DocumentReference rating : ratings) {
    // try {
    // RatingBean rat = (new RatingService()).getRating(rating.getId());
    // listRating.add(rat);
    // } catch (Exception e) {
    // e.prIntegerStackTrace();
    // }
    // }
    // return listRating;
    // }

    public void setCategories(List<DocumentReference> categories) {
        this.categories = categories;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }

    public Integer getBasket() {
        return basket;
    }

    public void setBasket(Integer basket) {
        this.basket = basket;
    }
}

package com.example.apimaroma.ratings;

import java.util.HashMap;
import java.util.Map;

public class RatingBean {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public RatingBean(String userId, Float rating) {
        this.userId = userId;
        this.rating = rating;
    }
    public RatingBean(String userId) {
        this.userId = userId;
    }
    public RatingBean() {
    }

    private String userId = "";
    private Float rating = 0f;

    public Map<String, Object> map (){
        Map<String, Object> updates = new HashMap<>();
        updates.put("userId", userId);
        updates.put("rating", rating);
        return  updates;


    }
}

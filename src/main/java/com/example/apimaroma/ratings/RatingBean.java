package com.example.apimaroma.ratings;

public class RatingBean {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public RatingBean(String userId, Integer rating) {
        this.userId = userId;
        this.rating = rating;
    }
    public RatingBean(String userId) {
        this.userId = userId;
    }
    public RatingBean() {
    }

    private String userId = "";
    private Integer rating = 0;
}

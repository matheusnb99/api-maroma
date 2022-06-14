package com.example.apimaroma.basket;

public class BasketBean {
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public BasketBean(String userId, String productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public BasketBean() {
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    private String userId;
    private String productId;
}

package com.example.apimaroma.orders;

import com.example.apimaroma.products.ProductBean;

import java.util.Date;
import java.util.List;

public class OrderBean {
    private String id;
    private String buyer;
    private Date creationDate;
    private Date modifitionDate;
    private String paymentMode;
    private List<ProductBean> products;

    public OrderBean(String id) {
        this.id = id;
    }

    public OrderBean(String buyer, Date creationDate, Date modifitionDate, String paymentMode, List<ProductBean> products) {
        this.buyer = buyer;
        this.creationDate = creationDate;
        this.modifitionDate = modifitionDate;
        this.paymentMode = paymentMode;
        this.products = products;
    }

    public OrderBean(String id, String buyer, Date creationDate, Date modifitionDate, String paymentMode, List<ProductBean> products) {
        this.id = id;
        this.buyer = buyer;
        this.creationDate = creationDate;
        this.modifitionDate = modifitionDate;
        this.paymentMode = paymentMode;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModifitionDate() {
        return modifitionDate;
    }

    public void setModifitionDate(Date modifitionDate) {
        this.modifitionDate = modifitionDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public List<ProductBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBean> products) {
        this.products = products;
    }

}

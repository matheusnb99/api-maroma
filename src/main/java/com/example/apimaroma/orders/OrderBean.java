package com.example.apimaroma.orders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.apimaroma.address.AddressBean;
import com.example.apimaroma.address.AddressService;
import com.example.apimaroma.products.ProductBean;
import com.example.apimaroma.products.ProductService;
import com.google.cloud.firestore.DocumentReference;

public class OrderBean {
    private String id = "";
    private DocumentReference buyer;
    private Date creationDate;
    private DocumentReference paymentMode;
    private DocumentReference deliveryAddress;
    private Double total = 0.0;
    private List<DocumentReference> products;

    public OrderBean(String id) {
        this.id = id;
    }

    public OrderBean() {}

    public OrderBean(DocumentReference buyer, Double total, DocumentReference deliveryAddress,
    Date creationDate, DocumentReference paymentMode, List<DocumentReference> products) {
        this.buyer = buyer;
        this.deliveryAddress = deliveryAddress;
        this.total = total;
        this.creationDate = creationDate;
        this.paymentMode = paymentMode;
        this.products = products;
    }

    public OrderBean(String id, DocumentReference buyer, Double total, DocumentReference deliveryAddress,
     Date creationDate, DocumentReference paymentMode, List<DocumentReference> products) {
        this.id = id;
        this.buyer = buyer;
        this.deliveryAddress = deliveryAddress;
        this.total = total;
        this.creationDate = creationDate;
        this.paymentMode = paymentMode;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DocumentReference getBuyer() {
        return buyer;
    }

    public void setBuyer(DocumentReference buyer) {
        this.buyer = buyer;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public DocumentReference getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(DocumentReference paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public AddressBean getDeliveryAddress() {
        AddressBean addr = null;
        try {
            addr = (new AddressService()).getAddress(deliveryAddress);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return addr;
    }

    public void setDeliveryAddress(DocumentReference deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<ProductBean> getProducts() {
        List<ProductBean> listProducts = new ArrayList<>();
        for (DocumentReference product : products) {
            try {
                ProductBean prod = (new ProductService()).getProduct(product.getId());
                listProducts.add(prod);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return listProducts;
    }

    public void setProducts(List<DocumentReference> products) {
        this.products = products;
    }

}

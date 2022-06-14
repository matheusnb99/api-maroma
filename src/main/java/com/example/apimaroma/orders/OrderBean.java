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
    private Date creationDate;
    private DocumentReference deliveryAddress;
    private Double total = 0.0;
    private List<DocumentReference> products;

    public OrderBean(String id) {
        this.id = id;
    }

    public OrderBean() {
    }

    public OrderBean(Double total, DocumentReference deliveryAddress,
            Date creationDate, List<DocumentReference> products) {
        this.deliveryAddress = deliveryAddress;
        this.total = total;
        this.creationDate = creationDate;
        this.products = products;
    }

    public OrderBean(String id, Double total, DocumentReference deliveryAddress,
            Date creationDate, List<DocumentReference> products) {
        this.id = id;
        this.deliveryAddress = deliveryAddress;
        this.total = total;
        this.creationDate = creationDate;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

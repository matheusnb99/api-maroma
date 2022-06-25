package com.example.apimaroma.orders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.apimaroma.user.UserBean;
import com.google.cloud.Timestamp;

@Service
public class OrderService {

    OrderModel orderModel = new OrderModel();


    public List<OrderBean> getAllOrders(UserBean user) throws ExecutionException, InterruptedException {
        return (List<OrderBean>) orderModel.findAllByUser(user);
    }

    public OrderBean getOrdersById(String id) throws ExecutionException, InterruptedException {
        return orderModel.findById(id).get();
    }

    public Timestamp deleteOrderById(String id) throws ExecutionException, InterruptedException {
        return orderModel.delete(new OrderBean(id));
    }

}

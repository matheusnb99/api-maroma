package com.example.apimaroma.orders;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apimaroma.user.UserBean;
import com.google.cloud.Timestamp;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "api/v1/orders")
@Api(value = "Orders Resource", description = "Endpoint of Orders route")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/getUserOrders")
    public List<OrderBean> getOrdersWithPostByUser(@RequestBody UserBean user)
            throws ExecutionException, InterruptedException {
        return orderService.getAllOrders(user);
    }

    @GetMapping("/getOrderById")
    public OrderBean getOrderById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return orderService.getOrderById(id);
    }

    @PostMapping("deleteOrderById")
    public Timestamp deleteOrderById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return orderService.deleteOrderById(id);
    }
}

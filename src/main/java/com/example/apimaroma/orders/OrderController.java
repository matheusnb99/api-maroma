package com.example.apimaroma.orders;

import com.example.apimaroma.user.UserBean;
import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path="api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getOrdersByUserId")
    public List<OrderBean> getOrdersByUser(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return orderService.getAllOrders(new UserBean(id));
    }
    @GetMapping("/getOrderById")
    public OrderBean getOrderById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return orderService.getOrdersById(id);
    }
    @PostMapping("deleteOrderById")
    public Timestamp deleteOrderById(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return orderService.deleteOrderById(id);
    }
}

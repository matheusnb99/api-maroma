package com.example.apimaroma.orders;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.example.apimaroma.address.AddressBean;
import com.example.apimaroma.utils.wrappers.AddressWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.apimaroma.user.UserBean;
import com.google.cloud.Timestamp;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "api/v2/orders")
@Api(value = "Orders Resource V2", description = "Endpoint of Orders route v2")
public class OrderControllerV2 {
    private final OrderService orderService;

    @Autowired
    public OrderControllerV2(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("buyer/{userId}/")
    public List<OrderBean> getOrdersByBuyer(@PathVariable("userId") String userId)
            throws ExecutionException, InterruptedException {
        return orderService.getAllOrders(new UserBean(userId));
    }

    @GetMapping("/{orderId}/")
    public OrderBean getOrderById(@PathVariable("orderId") String id) throws ExecutionException, InterruptedException {
        return orderService.getOrderById(id);
    }

    @DeleteMapping
    public Timestamp deleteOrderById(@RequestBody String id) throws ExecutionException, InterruptedException {
        return orderService.deleteOrderById(id);
    }


    @PostMapping
    public OrderBean addAddress(@RequestBody OrderBean order) throws ExecutionException, InterruptedException {
        return orderService.addOrder(order);
    }

}

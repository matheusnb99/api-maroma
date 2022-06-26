package com.example.apimaroma.basket;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apimaroma.user.UserBean;

@RestController
@RequestMapping(path = "api/v1/user")
public class BasketController {

    private final BasketService basketService;

    @Autowired
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping("/basket")
    public UserBean addToBasket(@RequestBody Map<String, Object> bodyMap)
            throws ExecutionException, InterruptedException {
        return basketService.addItemToBasket((String) bodyMap.get("userId"), (String) bodyMap.get("productId"),
                (Integer) bodyMap.get("quantity"));
    }

    @PostMapping("/removeBasket")
    public UserBean removeItemFromBasket(@RequestBody Map<String, Object> bodyMap)
            throws ExecutionException, InterruptedException {
        return basketService.removeItemFromBasket((String) bodyMap.get("userId"), (String) bodyMap.get("productId"),
                (Integer) bodyMap.get("quantity"));
    }

    @DeleteMapping("/removeBasket")
    public UserBean deleteItemFromBasket_(@RequestBody Map<String, Object> bodyMap)
            throws ExecutionException, InterruptedException {
        return basketService.removeItemFromBasket((String) bodyMap.get("userId"), (String) bodyMap.get("productId"),
                (Integer) bodyMap.get("quantity"));
    }

    @DeleteMapping("/clearBasket")
    public UserBean clearBasket(@RequestBody Map<String, Object> bodyMap)
            throws ExecutionException, InterruptedException {
        return basketService.clearBasket((String) bodyMap.get("userId"));
    }

}

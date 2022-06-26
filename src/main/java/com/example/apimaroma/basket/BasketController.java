package com.example.apimaroma.basket;

import com.example.apimaroma.ApiMaromaApplication;
import com.example.apimaroma.user.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(path="api/v1/user")
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
    public UserBean removeItemFromBasket(@RequestBody Map<String, Object> bodyMap) throws ExecutionException, InterruptedException {
        return basketService.removeItemFromBasket((String) bodyMap.get("userId"), (String) bodyMap.get("productId"), (Integer) bodyMap.get("quantity"));
    }
    @DeleteMapping("/removeBasket")
    public UserBean deleteItemFromBasket_(@RequestBody Map<String, Object> bodyMap) throws ExecutionException, InterruptedException {
        return basketService.removeItemFromBasket((String) bodyMap.get("userId"), (String) bodyMap.get("productId"), (Integer) bodyMap.get("quantity"));
    }
    @DeleteMapping("/clearBasket")
    public UserBean clearBasket(@RequestBody Map<String, Object> bodyMap) throws ExecutionException, InterruptedException {
        return basketService.clearBasket((String) bodyMap.get("userId"));
    }

}

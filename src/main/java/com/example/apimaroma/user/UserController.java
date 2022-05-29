package com.example.apimaroma.user;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.auth.FirebaseAuthException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserBean getUser(@PathVariable("id") String id) throws ExecutionException, InterruptedException {
        return userService.getUser(id);
    }

    @PostMapping("/getUser")
    public UserBean getUserWithPost(@RequestBody UserBean user) throws ExecutionException, InterruptedException {
        return userService.getUser(user.getId());
    }

    @PostMapping("/basket")
    public UserBean addToBasket(@RequestBody Map<String, Object> bodyMap)
            throws ExecutionException, InterruptedException {
        return userService.addItemToBasket((String) bodyMap.get("userId"), (String) bodyMap.get("productId"),
                (Integer) bodyMap.get("quantity"));
    }
    
    
    @PostMapping("/removeBasket")
    public UserBean removeFromBasket(@RequestBody Map<String, Object> bodyMap) throws ExecutionException, InterruptedException {
        return userService.removeItemFromBasket((String) bodyMap.get("userId"), (String) bodyMap.get("productId"), (Integer) bodyMap.get("quantity"));
    }
    
    @PostMapping
    public String createUser(@RequestBody UserBean user) throws FirebaseAuthException {
        return userService.createUser(user);
    }

    @GetMapping("/getUsersByName")
    public List<UserBean> getUserByName(@RequestHeader() String name) throws ExecutionException, InterruptedException {
        return userService.searchUserByName(name);
    }
}

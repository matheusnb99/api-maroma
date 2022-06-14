package com.example.apimaroma.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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

    @PostMapping("/createUser")
    public UserBean createUser(@RequestBody HashMap<String, Object> bodyMap) throws ExecutionException, InterruptedException {
        return userService.createUser(bodyMap);
    }

    @GetMapping("/getUsersByName")
    public List<UserBean> getUserByName(@RequestHeader() String name) throws ExecutionException, InterruptedException {
        return userService.searchUserByName(name);
    }
}

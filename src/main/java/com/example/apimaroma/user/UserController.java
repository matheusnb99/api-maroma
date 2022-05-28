package com.example.apimaroma.user;

import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/getUsersByName")
    public List<UserBean> getUserByName(@RequestHeader() String name) throws ExecutionException, InterruptedException {
        return userService.searchUserByName(name);
    }


    @PostMapping
    public String createUser(@RequestBody UserBean user) throws FirebaseAuthException {
        return userService.createUser(user);
    }


}

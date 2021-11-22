package com.restapi.restapispring.controller;

import com.restapi.restapispring.model.User;
import com.restapi.restapispring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class UserController {


    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User createUser(@Validated @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users/{id}")
    public List<User> getUserById(@PathVariable(value = "id") Long userId){
        return userRepository.findAll().stream().filter(a -> a.getId() == userId).collect(Collectors.toList());

    }
}

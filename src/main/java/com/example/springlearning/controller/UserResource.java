package com.example.springlearning.controller;

import com.example.springlearning.exception.UserNotFoundException;
import com.example.springlearning.model.User;
import com.example.springlearning.service.UserDaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserResource {
    private UserDaoService userDaoService;

    @GetMapping("/OpuMoni")
    public String welcomeOpu() {
        return "Opu moni\nHow are you?";
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userDaoService.getAll();
    }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Integer id) throws UserNotFoundException {
        User user = userDaoService.getOne(id);
        if(user == null) throw new UserNotFoundException("Id: " + id);
        return user;
    }

    @PostMapping("")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.saveUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}

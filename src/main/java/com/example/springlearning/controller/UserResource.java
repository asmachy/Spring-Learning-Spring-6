package com.example.springlearning.controller;

import com.example.springlearning.exception.UserNotFoundException;
import com.example.springlearning.model.User;
import com.example.springlearning.service.UserDaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserResource {
    private UserDaoService userDaoService;
    private MessageSource messageSource;

    @GetMapping("/greetings")
    public String greetings() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("how.are.you.message", null, "How Are", locale);
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

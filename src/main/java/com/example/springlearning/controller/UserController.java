package com.example.springlearning.controller;

import com.example.springlearning.dto.LoginUserRequest;
import com.example.springlearning.dto.SignUpUserRequest;
import com.example.springlearning.exception.UserAlreadyExistException;
import com.example.springlearning.exception.UserNotFoundException;
import com.example.springlearning.model.User;
import com.example.springlearning.service.AuthService;
import com.example.springlearning.service.UserDaoService;
import com.example.springlearning.utils.AuthUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private UserDaoService userDaoService;
    private MessageSource messageSource;
    private AuthService authService;

    @GetMapping("/greetings")
    public String greetings() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("how.are.you.message", null, "How Are", locale);
    }

    @GetMapping
    public List<User> getAllUser() {
        return userDaoService.getAll();
    }

    @GetMapping("/{id}")
    public EntityModel<User> getOne(@PathVariable Integer id) throws UserNotFoundException {
        User user = userDaoService.findUser(id);
        EntityModel<User> userModel = EntityModel.of(user);
        WebMvcLinkBuilder linkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUser());
        Link link = linkBuilder.withRel("all_user");
        userModel.add(link);
        return userModel;
    }

    @PostMapping
    public ResponseEntity<Object> registration(@Valid @RequestBody SignUpUserRequest userReq) throws UserAlreadyExistException {
        User user = User.of(userReq);
        authService.register(user);
        return new ResponseEntity<>("User Created Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest loginRequest) {
        String token = authService.login(loginRequest);
        return new ResponseEntity <>(token, HttpStatus.OK);
    }
}

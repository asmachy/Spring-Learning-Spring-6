package com.example.springlearning.service;

import com.example.springlearning.model.User;
import com.example.springlearning.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserDaoService {
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }
}

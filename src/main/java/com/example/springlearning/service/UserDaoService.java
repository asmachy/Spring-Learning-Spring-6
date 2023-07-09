package com.example.springlearning.service;

import com.example.springlearning.exception.UserNotFoundException;
import com.example.springlearning.model.User;
import com.example.springlearning.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDaoService {
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User findUser(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) throw new UserNotFoundException("No user found with ID: " + id);
        return user.get();
    }

    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }
}

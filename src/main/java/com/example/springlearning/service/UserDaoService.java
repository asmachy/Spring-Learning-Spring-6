package com.example.springlearning.service;

import com.example.springlearning.model.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    static {
        users.add(new User(10, "Asma", LocalDate.now().minusYears(28)));
    }

    public List<User> getAll() {
        return users;
    }

    public User getOne(Integer id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User saveUser(User user) {
        users.add(user);
        return user;
    }
}

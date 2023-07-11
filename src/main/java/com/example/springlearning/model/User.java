package com.example.springlearning.model;

import com.example.springlearning.dto.SignUpUserRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user_details")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Email(message = "Email is not valid")
    private String email;
    @Size(min = 6, message = "password length should be at least 6")
    private String password;
    public static User of(SignUpUserRequest userRequest) {
        User user = new User();
        user.email = userRequest.getEmail();
        user.password = userRequest.getPassword();
        return user;
    }

    public static User of(String email, String password) {
        User user = new User();
        user.email = email;
        user.password = password;
        return user;
    }
}

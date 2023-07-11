package com.example.springlearning.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUserRequest {
    @Email(message = "Email is not valid")
    private String email;
//    @Size(min = 6, message = "password length should be at least 5")
    private String password;
}

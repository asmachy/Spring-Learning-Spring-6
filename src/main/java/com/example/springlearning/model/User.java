package com.example.springlearning.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user_details")
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Email(message = "Email is not valid")
    private String email;
    @Min(value = 5, message = "password length should be at least 5")
    private String password;
}

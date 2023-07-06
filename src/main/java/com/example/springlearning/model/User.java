package com.example.springlearning.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@JsonFilter("UserFilter")
public class User {
    private Integer id;
    @Size(min = 2, message = "Name should have at least 2 character")
    private String name;
    @Past(message = "Birthdate should be past")
    private LocalDate birthDate;
}

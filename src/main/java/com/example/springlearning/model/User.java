package com.example.springlearning.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private LocalDate birthDate;
}

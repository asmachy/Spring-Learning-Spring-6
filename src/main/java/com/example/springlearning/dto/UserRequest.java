package com.example.springlearning.dto;

import com.example.springlearning.model.User;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
    @Size(min = 2, message = "Name should have at least 2 character")
    private String name;
    @Past(message = "Birthdate should be past")
    private LocalDate birthDate;
}

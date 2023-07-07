package com.example.springlearning.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CustomizedErrorResponse {
    private LocalDateTime timestamp;
    private String message;
}

package com.example.timetracker.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private Boolean success;
    private int status;
    private T data;
}

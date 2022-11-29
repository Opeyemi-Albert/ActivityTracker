package com.example.timetracker.dto.userDto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String gender;
    private LocalDateTime signUpDate;
    private LocalDateTime updatedDate;

}


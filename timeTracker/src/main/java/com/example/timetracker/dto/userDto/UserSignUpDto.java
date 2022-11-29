package com.example.timetracker.dto.userDto;

import com.example.timetracker.enums.Gender;
import lombok.Data;

@Data
public class UserSignUpDto {
    private String name;
    private String email;
    private String password;
    private String gender;
}

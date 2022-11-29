package com.example.timetracker.controllers;

import com.example.timetracker.dto.ApiResponse;
import com.example.timetracker.dto.userDto.UserLoginDto;
import com.example.timetracker.dto.userDto.UserResponseDto;
import com.example.timetracker.dto.userDto.UserResponseDto1;
import com.example.timetracker.exceptions.EmailNotValidException;
import com.example.timetracker.exceptions.InvalidRequestException;
import com.example.timetracker.exceptions.UserNotFoundException;
import com.example.timetracker.services.serviceImpl.UserServiceImpl;
import com.example.timetracker.util.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.valueOf;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserServiceImpl userServiceImpl;
    private final ResponseManager responseManager;


    @PostMapping("/login")
    public ApiResponse<String> login(@RequestBody UserLoginDto userLoginDto) throws EmailNotValidException, UserNotFoundException, InvalidRequestException {
            UserResponseDto1 newUser = new UserResponseDto1();

            UserResponseDto user = userServiceImpl.userLogin(userLoginDto);
            BeanUtils.copyProperties(user, newUser);
            newUser.setGender(valueOf(user.getGender()));

            return responseManager.success(newUser, HttpStatus.ACCEPTED.value());
    }
}
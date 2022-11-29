package com.example.timetracker.controllers;


import com.example.timetracker.dto.ApiResponse;
import com.example.timetracker.dto.userDto.UserResponseDto1;
import com.example.timetracker.dto.userDto.UserSignUpDto;
import com.example.timetracker.entities.User;
import com.example.timetracker.exceptions.EmailNotValidException;
import com.example.timetracker.exceptions.MissingDetailsException;
import com.example.timetracker.exceptions.UserAlreadyExistException;
import com.example.timetracker.exceptions.UserNotFoundException;
import com.example.timetracker.services.serviceImpl.UserServiceImpl;
import com.example.timetracker.util.ResponseManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.valueOf;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User Account API", description = "For User account creation")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    private final ResponseManager responseManager;


    @Operation(summary = "Create an account")
    @PostMapping("/signup")
    public ApiResponse signup(@RequestBody UserSignUpDto userSignUpDto) throws MissingDetailsException, UserAlreadyExistException, EmailNotValidException {
            UserResponseDto1 newUser = new UserResponseDto1();

            User user=userServiceImpl.userSignUp(userSignUpDto);

            BeanUtils.copyProperties(user, newUser);
            newUser.setGender(valueOf(user.getGender()));

        return responseManager.success(newUser, HttpStatus.CREATED.value());
    }

}

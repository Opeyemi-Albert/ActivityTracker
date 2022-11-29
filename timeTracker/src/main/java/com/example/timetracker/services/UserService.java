package com.example.timetracker.services;

import com.example.timetracker.dto.userDto.UserLoginDto;
import com.example.timetracker.dto.userDto.UserResponseDto;
import com.example.timetracker.dto.userDto.UserSignUpDto;
import com.example.timetracker.entities.User;
import com.example.timetracker.exceptions.*;
import com.sun.istack.NotNull;

public interface UserService {
    User userSignUp(UserSignUpDto userDto) throws UserAlreadyExistException, EmailNotValidException, MissingDetailsException;

    Boolean isEmailValid(@NotNull String email);

    UserResponseDto userLogin(UserLoginDto userLoginDto) throws EmailNotValidException, UserNotFoundException, InvalidRequestException;


}

package com.example.timetracker.services.serviceImpl;

import com.example.timetracker.dto.userDto.UserLoginDto;
import com.example.timetracker.dto.userDto.UserResponseDto;
import com.example.timetracker.dto.userDto.UserSignUpDto;
import com.example.timetracker.entities.User;
import com.example.timetracker.enums.Gender;
import com.example.timetracker.exceptions.*;
import com.example.timetracker.repositories.UserRepository;
import com.example.timetracker.services.UserService;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpSession;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final HttpSession httpSession;

    @Override
    public Boolean isEmailValid(@NotNull String email){
        String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailRegex);
        if (pattern.matcher(email).matches()) {
            return true;
        } else
            return false;
    }


    @Override
    public User userSignUp(UserSignUpDto userSignUpDto) throws UserAlreadyExistException, EmailNotValidException, MissingDetailsException {
        Boolean validEmailStatus = isEmailValid(userSignUpDto.getEmail());
        Boolean userExistStatus = userRepository.existsByEmail(userSignUpDto.getEmail());

        if (validEmailStatus)
            if (!userExistStatus)
                if (!userSignUpDto.getName().equals("") && !userSignUpDto.getPassword().equals("")) {
                    User user = new User();

                    user.setGender(Gender.valueOf(userSignUpDto.getGender()));
                    BeanUtils.copyProperties(userSignUpDto, user);

                    return  userRepository.save(user);
                } else {
                    throw new MissingDetailsException("Complete Missing Field");
                }
            else {
                throw new UserAlreadyExistException("User Already Exist");
            }
        else {
            throw new EmailNotValidException("Invalid Email account");
        }
    }

    @Override
    public UserResponseDto userLogin(UserLoginDto userLoginDto) throws EmailNotValidException, UserNotFoundException, InvalidRequestException {
        User user = userRepository.findUserByEmail(userLoginDto.getEmail()).orElseThrow(
                () -> new UserNotFoundException("User Not Found!"));


        if (!user.getPassword().equals(userLoginDto.getPassword())) {
            throw new InvalidRequestException("Invalid email or password");
        } else {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);
            userResponseDto.setGender(valueOf(user.getGender()));

            httpSession.setAttribute("userId", userResponseDto.getId());


            return userResponseDto;

        }
    }
}

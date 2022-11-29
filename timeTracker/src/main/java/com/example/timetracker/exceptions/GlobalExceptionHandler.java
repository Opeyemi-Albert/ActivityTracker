package com.example.timetracker.exceptions;


import com.example.timetracker.dto.ApiResponse;
import com.example.timetracker.util.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EmailNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleNotFoundException(EmailNotValidException ex){
        return new ApiResponse<>(ex.getMessage(), false, 404, null );
    }

    @ExceptionHandler(MissingDetailsException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public ApiResponse<String> handleMissingDetailsException(MissingDetailsException ex){
        return new ApiResponse<>(ex.getMessage(), false, 404, null );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse<String> handleNotFoundException(NotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, 404, null );
    }

    @ExceptionHandler(InvalidRequestException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse<String> handleInvalidRequestException(InvalidRequestException ex){
        return new ApiResponse<>(ex.getMessage(), false, 404, null );
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse<String> handleUserAlreadyExistException(UserAlreadyExistException ex){
        return new ApiResponse<>(ex.getMessage(), false, 404, null );
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ApiResponse<String> handleUserNotFoundException(UserNotFoundException ex){
        return new ApiResponse<>(ex.getMessage(), false, 404, null );
    }

}

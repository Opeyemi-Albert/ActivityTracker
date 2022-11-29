package com.example.timetracker.util;

import com.example.timetracker.dto.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Service
public class ResponseManager<T> {

    private final HttpSession httpSession;
    public ApiResponse<T> success(T data, int status){

        return new ApiResponse<T>("Request Successful",true, status, data);
    }

    public ApiResponse<T> error(String errorMessage, int status){
        return new ApiResponse<T>(errorMessage,false, status, null);
    }
}

package com.example.timetracker.dto.taskDto;


import com.example.timetracker.enums.Status;
import lombok.Data;



@Data
public class TaskDto {
    private Long taskId;
    private Long userId;
    private String title;
    private String description;
    private Status status;

}
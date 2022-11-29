package com.example.timetracker.services;

import com.example.timetracker.dto.taskDto.TaskDto;
import com.example.timetracker.enums.Status;
import com.example.timetracker.exceptions.MissingDetailsException;


import java.util.List;


public interface TaskService {
    TaskDto createTask(TaskDto taskDto) throws MissingDetailsException;


    TaskDto viewTaskById(Long userId, Long taskId);

    List<TaskDto> viewTasksByStatus(Status status);

    List<TaskDto> viewAllTasks(Long userId);


    TaskDto editTask(TaskDto taskDto);

    void deleteTask(Long taskId);



}


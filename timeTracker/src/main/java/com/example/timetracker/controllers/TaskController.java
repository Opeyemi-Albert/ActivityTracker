package com.example.timetracker.controllers;


import com.example.timetracker.dto.ApiResponse;
import com.example.timetracker.dto.taskDto.TaskDto;
import com.example.timetracker.entities.Task;
import com.example.timetracker.enums.Status;
import com.example.timetracker.exceptions.MissingDetailsException;
import com.example.timetracker.exceptions.UserNotFoundException;
import com.example.timetracker.services.serviceImpl.TaskServiceImpl;
import com.example.timetracker.util.ResponseManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final ResponseManager responseManager;

    private final TaskServiceImpl taskServiceImpl;

    @PostMapping("/create")
    public ApiResponse createTask(@RequestBody TaskDto taskDto) throws MissingDetailsException {

            TaskDto task = taskServiceImpl.createTask(taskDto);
            return responseManager.success(task, HttpStatus.CREATED.value());
            }


    @GetMapping("/view-all/{userId}")
    public ApiResponse<List<Task>> viewAllTasks(@PathVariable("userId") Long userId){

            List<TaskDto> task = taskServiceImpl.viewAllTasks(userId);
            return responseManager.success(task, HttpStatus.FOUND.value());
    }


    @GetMapping("/view-task")
    public ResponseEntity<TaskDto> viewTaskById(@RequestParam Long task_id, @RequestParam Long user_id){

            TaskDto task = taskServiceImpl.viewTaskById(user_id,task_id);
            return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @GetMapping("/view-task-by-status")
    public ApiResponse<List<TaskDto>> viewAllPendingTasks(@RequestParam Status status) throws UserNotFoundException {

            List<TaskDto> task = taskServiceImpl.viewTasksByStatus(status);
            return responseManager.success(task, HttpStatus.FOUND.value());
    }

    @PutMapping("/editTask")
    public ApiResponse editTask(@RequestBody TaskDto taskDto) {

             TaskDto task = taskServiceImpl.editTask(taskDto);
             return responseManager.success(task, HttpStatus.CREATED.value() );
    }

    @DeleteMapping("/deleteTask/{id}")
    public ApiResponse deleteTask(@PathVariable("id") long taskId) {

            taskServiceImpl.deleteTask(taskId);
            return responseManager.success("Successfully deleted!", HttpStatus.OK.value());

    }
}
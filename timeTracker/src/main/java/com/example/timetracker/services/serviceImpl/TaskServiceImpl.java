package com.example.timetracker.services.serviceImpl;

import com.example.timetracker.dto.taskDto.TaskDto;
import com.example.timetracker.entities.Task;
import com.example.timetracker.entities.User;
import com.example.timetracker.enums.Status;
import com.example.timetracker.exceptions.MissingDetailsException;
import com.example.timetracker.exceptions.NotFoundException;
import com.example.timetracker.repositories.TaskRepository;
import com.example.timetracker.repositories.UserRepository;
import com.example.timetracker.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final HttpSession httpSession;


    public User findLoggedInUser(){
            Long userId = (Long) httpSession.getAttribute("userId");
            return userRepository.findById(userId).get();
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) throws MissingDetailsException {

            if (taskDto.getDescription().equals("") || taskDto.getTitle().equals("")) {
                throw new MissingDetailsException("Fill the Required Details!");
            }
            Task task = new Task();

            User user = new User();
            user.setUserId(taskDto.getUserId());

            //User user = findLoggedInUser();


            BeanUtils.copyProperties(taskDto, task);
            task.setStatus(Status.Pending);
            task.setCreatedAt(LocalDateTime.now());
            task.setUpdatedAt(LocalDateTime.now());
            task.setUser(user);

            task =  taskRepository.save(task);

            BeanUtils.copyProperties(task,taskDto);
            return taskDto;
    }


    @Override
    public List<TaskDto> viewAllTasks(Long userId) {
       //   User user = findLoggedInUser();

            User user = new User();
            user.setUserId(userId);
            List<Task> tasks =taskRepository.findAllByUser(user);

            List<TaskDto> taskDtos = new ArrayList<>();
            tasks.forEach(task -> {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(task, taskDto);
            taskDtos.add(taskDto);
                });
            return taskDtos;
    }


    @Override
    public TaskDto viewTaskById(Long userId, Long taskId) {
            User user = new User();
            user.setUserId(userId);

            Task task = taskRepository.findTaskByUserAndTaskId(user, taskId)
                    .orElseThrow(
                    () ->new NotFoundException("Not Found"));
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(task, taskDto);

             return  taskDto;
    }


    @Override
    public List<TaskDto> viewTasksByStatus(Status status) {
            Task task = new Task();
            task.setStatus(status);
            List<Task> tasks = taskRepository.findTaskByStatus(status);

            List<TaskDto> taskDtos = new ArrayList<>();
            tasks.forEach(task1 -> {
                    TaskDto taskDto = new TaskDto();
                    BeanUtils.copyProperties(task1, taskDto);
                    taskDtos.add(taskDto);
                });

            return taskDtos;
    }

    @Override
    public TaskDto editTask(TaskDto taskDto) {
             User user = new User();
             user.setUserId(taskDto.getUserId());
             Task task = taskRepository.findById(taskDto.getTaskId()).orElseThrow(
                () ->new NotFoundException("Not Found"));

        if (task != null) {
            task.setTitle(taskDto.getTitle());
            task.setDescription(taskDto.getDescription());
            task.setStatus(taskDto.getStatus());
            task.setUpdatedAt(LocalDateTime.now());
        }
        if (task.getStatus() == Status.Done){
            task.setCompletedAt(LocalDateTime.now());
        }
        taskRepository.save(task);
        BeanUtils.copyProperties(task, taskDto);
        return taskDto;
    }

    @Override
    public void deleteTask(Long taskId) {

            Task task = taskRepository.findTaskByTaskId(taskId);
            taskRepository.delete(task);
    }

}


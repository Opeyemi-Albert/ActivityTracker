package com.example.timetracker.repositories;

import com.example.timetracker.entities.Task;
import com.example.timetracker.entities.User;
import com.example.timetracker.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    void deleteByTaskId(Long taskId);

    List<Task> findTasksByUser(Long userId);

    Task findTaskByTaskId(Long taskId);

    Optional<Task> findTaskByUserAndTaskId(User user, Long taskId);

    //List<Task> findTasksByUser(User user);

    List<Task> findAllByUser(User user);
    List<Task> findTaskByStatus(Status status);
    void deleteTasksByUser(User user);

    List<Task> findTasksByUserAndStatus(User user, Status status);


}
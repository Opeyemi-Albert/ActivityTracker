package com.example.timetracker.dto.taskDto;

import com.example.timetracker.entities.User;
import com.example.timetracker.enums.Status;
;import java.time.LocalDateTime;

public class TaskResponseDto {

    private Long taskId;

    private Long userId;

    private String title;

    private String description;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime completedAt;
}

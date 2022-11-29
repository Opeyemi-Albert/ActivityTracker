package com.example.timetracker.entities;

import com.example.timetracker.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "taskTable")
public class Task extends Base implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(name = "title",nullable = false, length = 250)
    private String title;

    @Column(name = "description",nullable = false, length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false, length = 25)
    private Status status;

    private LocalDateTime completedAt;
    @ManyToOne
    @JoinColumn(name ="userId")
    private User user;
}
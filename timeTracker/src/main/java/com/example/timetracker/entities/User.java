package com.example.timetracker.entities;

import com.example.timetracker.enums.Gender;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_table")
public class User extends Base implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name= "name", nullable = false, length = 25)
    private String name;

    @Column(name= "email", nullable = false, length = 25, unique = true)
    private String email;

    @Column(name = "password",nullable = false, length = 25)
    private String password;


    @Enumerated
    @Column(name="gender")
    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Task> taskList;
}

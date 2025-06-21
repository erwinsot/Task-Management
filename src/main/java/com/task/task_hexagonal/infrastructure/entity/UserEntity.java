package com.task.task_hexagonal.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.task.task_hexagonal.domain.model.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Getter
@Setter
@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@ToString(exclude = "tasks")
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role rol;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;


}

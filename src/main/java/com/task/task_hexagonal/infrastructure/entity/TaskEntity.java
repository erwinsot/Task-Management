package com.task.task_hexagonal.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.task.task_hexagonal.domain.model.StatusTask;
import com.task.task_hexagonal.domain.model.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name = "tareas")
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"user", "status"})
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity user;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_id", nullable = false)
    private StatusTaskEntity status;


    @Column(nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private LocalDateTime fechaActualizacion;


}

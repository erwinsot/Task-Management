package com.task.task_hexagonal.domain.model;

import com.task.task_hexagonal.domain.input.TaskUseCase;

import java.time.LocalDateTime;

public record Task(Long id, String title, String description, User user, StatusTask statusTask, LocalDateTime dateCreation, LocalDateTime dateUpdate) {
}

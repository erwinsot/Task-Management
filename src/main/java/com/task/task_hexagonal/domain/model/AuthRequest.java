package com.task.task_hexagonal.domain.model;

import com.task.task_hexagonal.domain.input.TaskUseCase;

public record AuthRequest(String username, String password)  {
}

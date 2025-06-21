package com.task.task_hexagonal.domain.input;

import com.task.task_hexagonal.domain.model.AuthRequest;
import com.task.task_hexagonal.domain.model.AuthResponse;
import com.task.task_hexagonal.domain.model.User;

public interface AuthUseCase {
    AuthResponse authenticateUser(AuthRequest authRequest);
    User register(User user);
}

package com.task.task_hexagonal;

import com.task.task_hexagonal.application.service.AuthService;
import com.task.task_hexagonal.application.service.TaskService;
import com.task.task_hexagonal.domain.input.AuthUseCase;
import com.task.task_hexagonal.domain.input.TaskUseCase;
import com.task.task_hexagonal.domain.output.StatusTaskRepository;
import com.task.task_hexagonal.domain.output.TaskRepository;
import com.task.task_hexagonal.domain.output.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskHexagonalApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskHexagonalApplication.class, args);
	}





}

package com.task.task_hexagonal.infrastructure.input.rest.mapper;

import com.task.task_hexagonal.domain.model.Task;
import com.task.task_hexagonal.infrastructure.input.rest.dto.TaskResponse;
import org.springframework.stereotype.Component;


@Component
public class TaskResponseMapper {
    public TaskResponse toDtoResponse(Task task) {
        return new TaskResponse(
                task.id(),
                task.title(),
                task.description(),
                task.statusTask().name(),
                task.user().name()
        );
    }
}

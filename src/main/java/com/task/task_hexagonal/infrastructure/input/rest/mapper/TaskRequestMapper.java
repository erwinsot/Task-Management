package com.task.task_hexagonal.infrastructure.input.rest.mapper;

import com.task.task_hexagonal.domain.model.StatusTask;
import com.task.task_hexagonal.domain.model.Task;
import com.task.task_hexagonal.infrastructure.input.rest.dto.TaskRequest;
import org.springframework.stereotype.Component;

@Component
public class TaskRequestMapper {

    public Task toDomainModelTask(TaskRequest dto ) {
        return new Task(
                null,
                dto.title(),
                dto.description(),
                null,
                new StatusTask(
                        dto.statusId(),
                        null,
                        null,
                        null
                ),
                null,
                null

        );
    }


}

package com.task.task_hexagonal.infrastructure.mapper;

import com.task.task_hexagonal.domain.model.Task;
import com.task.task_hexagonal.infrastructure.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TaskMapper {

    private final StatusTaskMapper statusTaskMapper;
    private final UserMapper userMapper;

    public Task toDomainTask (TaskEntity taskEntity) {
        return new Task(
                taskEntity.getId(),
                taskEntity.getTitle(),
                taskEntity.getDescription(),
                taskEntity.getUser() != null ? userMapper.toDomainUser(taskEntity.getUser()) : null,
                taskEntity.getStatus() != null ? statusTaskMapper.toDomainStatus(taskEntity.getStatus()) : null,
                taskEntity.getFechaCreacion(),
                taskEntity.getFechaActualizacion()

        );
    }

    public TaskEntity toEntityTask(Task task) {
        return new TaskEntity(
                task.id(),
                task.title(),
                task.description(),
                task.user() != null ? userMapper.toEntityUser(task.user()) : null,
                task.statusTask() != null ? statusTaskMapper.toEntityStatus(task.statusTask()) : null,
                task.dateCreation(),
                task.dateUpdate()
        );
    }
}

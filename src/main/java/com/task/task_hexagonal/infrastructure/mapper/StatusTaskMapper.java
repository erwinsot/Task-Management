package com.task.task_hexagonal.infrastructure.mapper;

import com.task.task_hexagonal.domain.model.StatusTask;
import com.task.task_hexagonal.infrastructure.entity.StatusTaskEntity;
import org.springframework.stereotype.Component;


@Component
public class StatusTaskMapper {

    public StatusTask toDomainStatus (StatusTaskEntity statusTaskEntity) {
        return new StatusTask(
                statusTaskEntity.getId(),
                statusTaskEntity.getName(),
                statusTaskEntity.getDescription(),
                null

        );
    }
    public StatusTaskEntity toEntityStatus(StatusTask statusTask) {
        return new StatusTaskEntity(
                statusTask.id(),
                statusTask.name(),
                statusTask.description(),
                null // Assuming tasks are not needed in the entity for this mapping
        );
    }

}

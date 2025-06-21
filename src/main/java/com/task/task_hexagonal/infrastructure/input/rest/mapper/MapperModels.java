package com.task.task_hexagonal.infrastructure.input.rest.mapper;

import com.task.task_hexagonal.domain.model.*;
import com.task.task_hexagonal.infrastructure.entity.StatusTaskEntity;
import com.task.task_hexagonal.infrastructure.entity.TaskEntity;
import com.task.task_hexagonal.infrastructure.entity.UserEntity;
import com.task.task_hexagonal.infrastructure.input.rest.dto.*;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperModels {
    Task toDomainModel(TaskRequest dto);
    TaskResponse toDto(Task dto);
    AuthResponseDto toDtoAuth(AuthResponse dto);
    AuthRequest toDomainModelAuth(AuthRequestDto dto);
    User toDomainModelUser(RegisterRequest user);
    UserDto toDtoUser(User user);
    StatusTask toDomainModelStatus(StatusTaskEntity entity);
    StatusTaskEntity toEntityStatus(StatusTask statusTask);
    Task toDomainModelTask(TaskEntity entity);
    TaskEntity toEntityTask(Task task);
    User toDomainModelUser(UserEntity entity);
    UserEntity toEntityUser(User user);
}

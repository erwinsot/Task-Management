package com.task.task_hexagonal.infrastructure.mapper;

import com.task.task_hexagonal.domain.model.User;
import com.task.task_hexagonal.infrastructure.entity.UserEntity;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User toDomainUser (UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getEmail(),
                userEntity.getRol()
        );
    }

    public UserEntity toEntityUser(User user) {
        return new UserEntity(
                user.id(),
                user.username(),
                user.password(),
                user.name(),
                user.email(),
                user.rol(),
                null // Assuming 'null' for 'tasks' as it's not part of the User model in this context

        );
    }
}

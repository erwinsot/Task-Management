package com.task.task_hexagonal.infrastructure.Impl;

import com.task.task_hexagonal.domain.model.User;
import com.task.task_hexagonal.domain.output.UserRepository;
import com.task.task_hexagonal.infrastructure.input.rest.mapper.MapperModels;
import com.task.task_hexagonal.infrastructure.mapper.UserMapper;
import com.task.task_hexagonal.infrastructure.output.persistence.UserJpaRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRespository userJpaRespository;
    private final MapperModels mapperModels;
    private final UserMapper userMapper;


    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRespository.findByUsername(username)
                .map(userMapper::toDomainUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRespository.findByEmail(email)
                .map(mapperModels::toDomainModelUser);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userJpaRespository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRespository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        return mapperModels.toDomainModelUser(
                userJpaRespository.save(mapperModels.toEntityUser(user))
        );
    }
}

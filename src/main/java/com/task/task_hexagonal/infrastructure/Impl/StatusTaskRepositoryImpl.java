package com.task.task_hexagonal.infrastructure.Impl;

import com.task.task_hexagonal.domain.model.StatusTask;
import com.task.task_hexagonal.domain.output.StatusTaskRepository;
import com.task.task_hexagonal.infrastructure.input.rest.mapper.MapperModels;
import com.task.task_hexagonal.infrastructure.output.persistence.StatusTaskJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class StatusTaskRepositoryImpl implements StatusTaskRepository {

    private final StatusTaskJpaRepository statusTaskJpaRepository;
    private final MapperModels mapperModels;

    @Override
    public Optional<StatusTask> findByName(String name) {
        return statusTaskJpaRepository.findByName(name).map(
                mapperModels::toDomainModelStatus
        );
    }

    public StatusTask findById(Long id) {
        return statusTaskJpaRepository.findById(id)
                .map(mapperModels::toDomainModelStatus)
                .orElseThrow(() -> new IllegalArgumentException("StatusTask with id " + id + " not found"));
    }
}

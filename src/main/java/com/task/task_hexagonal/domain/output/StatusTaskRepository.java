package com.task.task_hexagonal.domain.output;

import com.task.task_hexagonal.domain.model.StatusTask;

import java.util.Optional;

public interface StatusTaskRepository {
    Optional<StatusTask> findByName(String name);
    StatusTask findById(Long id);

}

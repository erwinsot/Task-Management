package com.task.task_hexagonal.domain.output;

import com.task.task_hexagonal.domain.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findByUser_username(String username);
    List<Task> findByUserId(Long userId);
    Optional<Task> findByIdAndUserId(Long id, Long userId);
    Task save(Task task);
    Task findById(Long id);
    void deleteById(Long id);

}

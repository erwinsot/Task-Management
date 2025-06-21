package com.task.task_hexagonal.domain.input;

import com.task.task_hexagonal.domain.model.Task;

import java.util.List;


public interface TaskUseCase {
    List<Task> getAll(String name);
    Task create(Task dto,Long statusId, String username);
    Task update(Task taskRequest,Long id);
    void delete(Long id);
    Task getById(Long id, String username);

}

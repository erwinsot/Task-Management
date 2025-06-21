package com.task.task_hexagonal.infrastructure.Impl;


import com.task.task_hexagonal.domain.exception.TaskNotFoundException;
import com.task.task_hexagonal.domain.model.Task;
import com.task.task_hexagonal.domain.output.TaskRepository;
import com.task.task_hexagonal.infrastructure.mapper.TaskMapper;
import com.task.task_hexagonal.infrastructure.output.persistence.TaskJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TaskRepositoryImpl implements TaskRepository {

    private final TaskJpaRepository taskJpaRepository;
    private final TaskMapper taskMapper;


    @Override
    public List<Task> findByUser_username(String username) {
        return taskJpaRepository.findByUser_username(username)
                .stream().map(
                taskMapper::toDomainTask
        ).toList();
    }

    @Override
    public List<Task> findByUserId(Long userId) {
        return taskJpaRepository.findByUserId(userId)
                .stream().map(
                taskMapper::toDomainTask
        ).toList();

    }

    @Override
    public Optional<Task> findByIdAndUserId(Long id, Long userId) {
        return taskJpaRepository.findByIdAndUserId(id, userId)
                .map(taskMapper::toDomainTask);
    }

    @Override
    public Task save(Task task) {
        Task otro = taskMapper.toDomainTask(taskJpaRepository.save(taskMapper.toEntityTask(task)));
        System.out.println("otro = " + otro);
        return  otro;
    }

    @Override
    public Task findById(Long id) {
        return taskMapper.toDomainTask(taskJpaRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id)));
    }

    @Override
    public void deleteById(Long id) {
        if (!taskJpaRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id: " + id);
        }
        taskJpaRepository.deleteById(id);

    }


}

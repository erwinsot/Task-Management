package com.task.task_hexagonal.application.service;


import com.task.task_hexagonal.domain.input.TaskUseCase;
import com.task.task_hexagonal.domain.model.StatusTask;
import com.task.task_hexagonal.domain.model.Task;
import com.task.task_hexagonal.domain.model.User;
import com.task.task_hexagonal.domain.output.StatusTaskRepository;
import com.task.task_hexagonal.domain.output.TaskRepository;
import com.task.task_hexagonal.domain.output.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class TaskService implements TaskUseCase {

    private final TaskRepository taskRepository;
    private final StatusTaskRepository statusTaskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, StatusTaskRepository statusTaskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.statusTaskRepository = statusTaskRepository;
        this.userRepository = userRepository;
    }




    @Override
    public List<Task> getAll(String name) {
        return taskRepository.findByUser_username(name);
    }

    @Override
    public Task create(Task dto,  Long statusId,  String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        StatusTask statusTask = statusTaskRepository.findById(statusId);
        Task task = new Task(null, dto.title(), dto.description(), user, statusTask, LocalDateTime.now(), LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task update(Task taskRequest, Long id) {
        Task task = taskRepository.findById(id);
        System.out.println("Task to update: " + task);
        Task taskToUpdate = new Task(
                task.id(),
                taskRequest.title(),
                taskRequest.description(),
                task.user(),
                statusTaskRepository.findById(taskRequest.statusTask().id()),
                task.dateCreation(),
                LocalDateTime.now());
        return  taskRepository.save(taskToUpdate);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task getById(Long id, String username) {
        return taskRepository.findById(id);
    }
}

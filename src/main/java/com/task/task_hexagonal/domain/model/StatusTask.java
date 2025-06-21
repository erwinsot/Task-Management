package com.task.task_hexagonal.domain.model;

import java.util.List;

public record StatusTask(Long id, String name, String description, List<Task> task)   {
}

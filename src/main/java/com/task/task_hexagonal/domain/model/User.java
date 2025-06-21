package com.task.task_hexagonal.domain.model;


public record User(Long id, String username, String password, String name, String email, Role rol )  {
}

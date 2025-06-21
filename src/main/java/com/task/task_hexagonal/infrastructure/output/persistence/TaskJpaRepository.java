package com.task.task_hexagonal.infrastructure.output.persistence;

import com.task.task_hexagonal.infrastructure.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TaskJpaRepository extends JpaRepository<TaskEntity, Long> {
    List<TaskEntity> findByUser_username(String username);
    List<TaskEntity> findByUserId(Long userId);
    Optional<TaskEntity> findByIdAndUserId(Long id, Long userId);
}

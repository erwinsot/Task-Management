package com.task.task_hexagonal.infrastructure.output.persistence;

import com.task.task_hexagonal.infrastructure.entity.StatusTaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Repository
public interface StatusTaskJpaRepository extends JpaRepository<StatusTaskEntity,Long> {
    Optional<StatusTaskEntity> findByName(String name);
}

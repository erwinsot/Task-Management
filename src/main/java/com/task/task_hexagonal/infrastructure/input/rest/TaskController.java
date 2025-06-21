package com.task.task_hexagonal.infrastructure.input.rest;


import com.task.task_hexagonal.domain.input.TaskUseCase;
import com.task.task_hexagonal.domain.model.Task;
import com.task.task_hexagonal.infrastructure.input.rest.dto.TaskRequest;
import com.task.task_hexagonal.infrastructure.input.rest.dto.TaskResponse;
import com.task.task_hexagonal.infrastructure.input.rest.mapper.MapperModels;
import com.task.task_hexagonal.infrastructure.input.rest.mapper.TaskRequestMapper;
import com.task.task_hexagonal.infrastructure.input.rest.mapper.TaskResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
@Tag(name = "Tareas", description = "Operaciones relacionadas con tareas de los usuarios")
public class TaskController {

    private final TaskUseCase taskUseCase;
    private final MapperModels mapperModels;
    private final TaskResponseMapper taskResponseMapper;
    private final TaskRequestMapper taskRequestMapper;

    @Operation(summary = "Obtener todas las tareas del usuario autenticado")
    @ApiResponse(responseCode = "200", description = "Lista de tareas obtenida correctamente")
    @GetMapping
    public List<TaskResponse> getAll(Authentication auth) {
        return (taskUseCase.getAll(auth.getName()))
                .stream()
                .map(taskResponseMapper::toDtoResponse)
                .toList();
    }


    @Operation(summary = "Obtener una tarea por su ID")
    @ApiResponse(responseCode = "200", description = "Tarea obtenida correctamente")
    @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> byId(@PathVariable Long id, Authentication auth) {
        return new ResponseEntity<>(taskResponseMapper.toDtoResponse( taskUseCase.getById(id, auth.getName())), HttpStatus.OK);
    }

    @Operation(summary = "Crear una nueva tarea")
    @ApiResponse(responseCode = "201", description = "Tarea creada exitosamente")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody  @Valid TaskRequest dto, Authentication auth) {
        Task task = mapperModels.toDomainModel(dto);
        TaskResponse response = taskResponseMapper.toDtoResponse(taskUseCase.create(task, dto.statusId(), auth.getName()));
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar una tarea existente")
    @ApiResponse(responseCode = "200", description = "Tarea actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Tarea no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id, @RequestBody @Valid TaskRequest dto, Authentication auth) {
        return new ResponseEntity<>(taskResponseMapper.toDtoResponse( taskUseCase.update(taskRequestMapper.toDomainModelTask(dto),id )), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar una tarea")
    @ApiResponse(responseCode = "200", description = "Tarea eliminada exitosamente")
    @ApiResponse(responseCode = "404", description = "Tarea no encontrada")

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id, Authentication auth) {
        taskUseCase.delete(id);
        return ResponseEntity.ok("Tarea eliminada exitosamente");
    }
}

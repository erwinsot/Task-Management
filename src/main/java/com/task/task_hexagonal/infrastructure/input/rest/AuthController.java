package com.task.task_hexagonal.infrastructure.input.rest;

import com.task.task_hexagonal.domain.input.AuthUseCase;
import com.task.task_hexagonal.infrastructure.input.rest.dto.AuthRequestDto;
import com.task.task_hexagonal.infrastructure.input.rest.dto.AuthResponseDto;
import com.task.task_hexagonal.infrastructure.input.rest.dto.RegisterRequest;
import com.task.task_hexagonal.infrastructure.input.rest.dto.UserDto;
import com.task.task_hexagonal.infrastructure.input.rest.mapper.MapperModels;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Operaciones relacionadas con el inicio de sesión y registro de usuarios")
public class AuthController {

    private final AuthUseCase authUseCase;
    private final MapperModels mapperModels;


    @Operation(
            summary = "Autenticación de usuario",
            description = "Permite a un usuario autenticarse y obtener un token JWT válido si las credenciales son correctas"
    )
    @PostMapping("/session")
    public ResponseEntity<AuthResponseDto> authenticateUser(@Valid @RequestBody AuthRequestDto request) throws Exception {
        AuthResponseDto response = mapperModels.toDtoAuth(authUseCase.authenticateUser(mapperModels.toDomainModelAuth(request)));
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Registro de nuevo usuario",
            description = "Registra un nuevo usuario en el sistema con un rol predeterminado"
    )
    @PostMapping("/users")
    public ResponseEntity<UserDto> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok( mapperModels.toDtoUser(authUseCase.register(mapperModels.toDomainModelUser(request))));
    }
}

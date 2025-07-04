package com.task.task_hexagonal.infrastructure.input.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;


@Schema(
        name = "AuthRequest",
        title = "Solicitud de Autenticación",
        description = "Contiene las credenciales del usuario para iniciar sesión"
)
public record AuthRequestDto(
    @Schema(description = "Nombre de usuario", example = "admin")
    @NotBlank(message = "El usuario es obligatorio")
    String username,

    @Schema(description = "Contraseña del usuario", example = "admin123")
    @NotBlank(message = "La contraseña es obligatoria")
    String password)
        {
}

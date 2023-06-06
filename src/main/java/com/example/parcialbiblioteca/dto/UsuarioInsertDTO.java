package com.example.parcialbiblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioInsertDTO {
    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    @NotNull(message = "El apellido no puede ser nulo")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String apellido;
    @NotNull(message = "El DUI no puede ser nulo")
    @Pattern(regexp = "^[0-9]{8}-[0-9]{1}$", message = "El formato del DUI es incorrecto")
    private String dui;
    @Size(min = 3, max = 50, message = "La direccion debe tener entre 3 y 50 caracteres")
    private String direccion;
    @Pattern(regexp = "^[0-9]{4}-[0-9]{4}$", message = "El formato del telefono es incorrecto")
    private String telefono;
    @Email(message = "El formato del correo es incorrecto")
    private String email;
    @Size(min = 3, max = 50, message = "La contraseña debe tener entre 3 y 50 caracteres")
    private String password;
    @Size(min = 3, max = 50, message = "El rol debe tener entre 3 y 50 caracteres")
    private String rol;
}

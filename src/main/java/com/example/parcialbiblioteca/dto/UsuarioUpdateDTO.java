package com.example.parcialbiblioteca.dto;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioUpdateDTO {
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String nombre;
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String apellido;
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

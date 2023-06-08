package com.example.parcialbiblioteca.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibroInsertDTO {
    @NotNull(message = "El titulo no puede ser nulo")
    @Size(min = 3, max = 50, message = "El titulo debe tener entre 3 y 50 caracteres")
    private String titulo;
    @Size(min = 3, max = 50, message = "El slug debe tener entre 3 y 50 caracteres")
    private String slug;
    @NotNull(message = "El autor no puede ser nulo")
    @Size(min = 3, max = 50, message = "El autor debe tener entre 3 y 50 caracteres")
    private String autor;
    @NotNull(message = "La editorial no puede ser nulo")
    @Size(min = 3, max = 50, message = "La editorial debe tener entre 3 y 50 caracteres")
    private String editorial;
    @NotNull(message = "El año no puede ser nulo")
    @Min(value = 1000, message = "El año debe ser mayor a 1000")
    @Max(value = 2023, message = "El año debe ser menor a 2023")
    private Integer anio;
    @NotNull(message = "El genero no puede ser nulo")
    @Size(min = 3, max = 50, message = "El genero debe tener entre 3 y 50 caracteres")
    private String genero;
    @NotNull(message = "El idioma no puede ser nulo")
    @Size(min = 3, max = 50, message = "El idioma debe tener entre 3 y 50 caracteres")
    private String idioma;
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 1, message = "El stock debe ser mayor o igual a 1")
    private Integer stock;

}

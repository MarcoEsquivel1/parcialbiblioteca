package com.example.parcialbiblioteca.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LibroDTO {
    @Size(min = 3, max = 50, message = "El titulo debe tener entre 3 y 50 caracteres")
    private String titulo;
    @Size(min = 3, max = 50, message = "El slug debe tener entre 3 y 50 caracteres")
    private String slug;
    @Size(min = 3, max = 50, message = "El autor debe tener entre 3 y 50 caracteres")
    private String autor;
    @Size(min = 3, max = 50, message = "La editorial debe tener entre 3 y 50 caracteres")
    private String editorial;
    @Min(value = 1000, message = "El año debe ser mayor a 1000")
    @Max(value = 2023, message = "El año debe ser menor a 2023")
    private Integer anio;
    @Size(min = 3, max = 50, message = "El genero debe tener entre 3 y 50 caracteres")
    private String genero;
    @Size(min = 3, max = 50, message = "El idioma debe tener entre 3 y 50 caracteres")
    private String idioma;
    @Size(min = 3, max = 50, message = "El estado debe tener entre 3 y 50 caracteres")
    private String estado;

}

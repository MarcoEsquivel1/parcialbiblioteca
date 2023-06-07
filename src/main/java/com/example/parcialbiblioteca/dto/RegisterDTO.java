package com.example.parcialbiblioteca.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String nombre;
    private String apellido;
    private String dui;
    private String direccion;
    private String telefono;
    private String email;
    private String password;
}

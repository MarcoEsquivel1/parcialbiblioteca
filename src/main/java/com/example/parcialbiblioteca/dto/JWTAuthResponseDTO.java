package com.example.parcialbiblioteca.dto;

import com.example.parcialbiblioteca.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data

public class JWTAuthResponseDTO {
    private String token;
    private String type = "Bearer";
    private Usuario usuario;

    public JWTAuthResponseDTO(String token, Usuario usuario) {
        super();
        this.token = token;
        this.usuario = usuario;
    }
}

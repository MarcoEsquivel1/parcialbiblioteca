package com.example.parcialbiblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data

public class JWTAuthResponseDTO {
    private String token;
    private String type = "Bearer";
    private List<String> rol;

    public JWTAuthResponseDTO(String token, List<String> rol) {
        super();
        this.token = token;
        this.rol = rol;
    }
}

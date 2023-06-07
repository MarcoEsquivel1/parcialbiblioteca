package com.example.parcialbiblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class JWTAuthResponseDTO {
    private String token;
    private String type = "Bearer";

    public JWTAuthResponseDTO(String token) {
        super();
        this.token = token;
    }
}

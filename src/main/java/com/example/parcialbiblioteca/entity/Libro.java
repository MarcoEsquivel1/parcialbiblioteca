package com.example.parcialbiblioteca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_libro", nullable = false)
    private Long idLibro;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "slug", unique = true)
    private String slug;
    @Column(name = "autor")
    private String autor;
    @Column(name = "editorial")
    private String editorial;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "genero")
    private String genero;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "estado")
    private String estado;
    @Column(name = "stock")
    private Integer stock;

    @PrePersist
    public void prePersist() {
        this.estado = "DISPONIBLE";
        if (this.slug == null) {
            this.slug = this.titulo.toLowerCase().replace(" ", "_").replace("'", "");
        }
        this.slug = this.slug.toLowerCase().replace(" ", "_").replace("'", "");
    }

    @PreUpdate
    public void preUpdate() {
        if (this.slug != null) {
            this.slug = this.slug.toLowerCase().replace(" ", "_").replace("'", "");
        }
        if (this.titulo != null) {
            this.slug = this.titulo.toLowerCase().replace(" ", "_").replace("'", "");
        }
    }

}
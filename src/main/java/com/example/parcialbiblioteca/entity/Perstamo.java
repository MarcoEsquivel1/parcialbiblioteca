package com.example.parcialbiblioteca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "perstamos")
public class Perstamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "libro_id", referencedColumnName = "id")
    private Libro libro;
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
    @Column(name = "fecha_inscripcion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaInscripcion;
    @Column(name = "fecha_devolucion")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaDevolucion;
    @Column(name = "estado")
    private String estado;

    @PrePersist
    public void prePersist() {
        fechaInscripcion = LocalDate.now();
    }

}
package com.example.parcialbiblioteca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo", nullable = false)
    private Long idPrestamo;
    @Column(name = "id_libro")
    private Long idLibro;
    @Column(name = "id_usuario")
    private Long idUsuario;
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

    @ManyToOne
    @JoinColumn(name = "id_libro", insertable = false, updatable = false)
    private Libro libro;

    @ManyToOne
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @PrePersist

    public void prePersist() {
        estado = "SIN DEVOLVER";
        fechaInscripcion = LocalDate.now();
    }

}
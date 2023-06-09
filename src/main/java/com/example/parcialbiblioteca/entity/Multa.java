package com.example.parcialbiblioteca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "multas")
public class Multa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_multa", nullable = false)
    private Long idMulta;
    @Column(name = "id_prestamo", nullable = false)
    private Long idPrestamo;
    @Column(name = "monto")
    private Double monto;
    @Column(name = "estado")
    private String estado;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_pago")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate fechaPago;

    @OneToOne
    @JoinColumn(name = "id_prestamo", insertable = false, updatable = false)
    private Prestamo prestamo;

    @PrePersist
    public void prePersist() {
        this.estado = "PENDIENTE";
    }

    public void pagarMulta() {
        this.estado = "PAGADA";
        this.fechaPago = LocalDate.now();
    }

    public void revertirPago() {
        this.estado = "PENDIENTE";
        this.fechaPago = null;
    }

}
package com.example.parcialbiblioteca.repository;

import com.example.parcialbiblioteca.entity.Multa;
import com.example.parcialbiblioteca.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("multaRepository")
public interface MultaRepository extends JpaRepository<Multa, Long> {
    @Query("SELECT m FROM Multa m WHERE m.prestamo.idPrestamo = :idPrestamo")
    Optional<Multa> findByPrestamo(Long idPrestamo);
}

package com.example.parcialbiblioteca.repository;
import com.example.parcialbiblioteca.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}

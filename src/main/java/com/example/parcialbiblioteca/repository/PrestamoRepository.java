package com.example.parcialbiblioteca.repository;
import com.example.parcialbiblioteca.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("prestamoRepository")
public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}

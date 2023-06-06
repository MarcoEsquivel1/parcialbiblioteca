package com.example.parcialbiblioteca.repository;

import com.example.parcialbiblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("libroRepository")
public interface LibroRepository extends JpaRepository<Libro, Long> {
}

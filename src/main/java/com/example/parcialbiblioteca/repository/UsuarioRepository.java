package com.example.parcialbiblioteca.repository;

import com.example.parcialbiblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository("usuarioRepository")
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("SELECT u FROM Usuario u WHERE u.dui = :dui")
    Optional<Usuario> findByDui(String dui);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> findByEmail(String email);

    Boolean existsByEmail(String email);
}

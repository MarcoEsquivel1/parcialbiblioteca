package com.example.parcialbiblioteca.controller;

import com.example.parcialbiblioteca.Security.JwtTokenProvider;
import com.example.parcialbiblioteca.dto.JWTAuthResponseDTO;
import com.example.parcialbiblioteca.dto.LoginDTO;
import com.example.parcialbiblioteca.dto.RegisterDTO;
import com.example.parcialbiblioteca.entity.Rol;
import com.example.parcialbiblioteca.entity.Usuario;
import com.example.parcialbiblioteca.repository.RolRepository;
import com.example.parcialbiblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterDTO registerDTO) {

        if (usuarioRepository.existsByEmail(registerDTO.getEmail())) {
            return new ResponseEntity<>("Este email ya tiene una cuenta registrada", HttpStatus.BAD_REQUEST);
        }

        Usuario user = new Usuario();
        user.setNombre(registerDTO.getNombre());
        user.setApellido(registerDTO.getApellido());
        user.setDui(registerDTO.getDui());
        user.setTelefono(registerDTO.getTelefono());
        user.setDireccion(registerDTO.getDireccion());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Optional<Rol> rolesOptional = rolRepository.findByNombre("USER");
        if (rolesOptional.isPresent()) {
            user.setRoles(new ArrayList<>(Collections.singleton(rolesOptional.get())));
            user.setRol(String.valueOf(rolesOptional.get().getNombre()));
        } else {
            return new ResponseEntity<>("No se pudo asignar el rol", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        usuarioRepository.save(user);

        return new ResponseEntity<>("Usuario registrado con exito", HttpStatus.OK);
    }

}

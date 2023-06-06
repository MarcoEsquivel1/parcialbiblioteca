package com.example.parcialbiblioteca.controller;

import com.example.parcialbiblioteca.dto.UsuarioDTO;
import com.example.parcialbiblioteca.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public Object getAll(){
        return usuarioService.getAll();
    }
    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id) {
        return usuarioService.getById(id);
    }
    @PostMapping(consumes = {"application/json", "application/x-www-form-urlencoded"})
    public Object post(@Validated @RequestBody UsuarioDTO usuario){
        return usuarioService.post(usuario);
    }
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public Object update(@PathVariable("id") Long id, @Validated @RequestBody UsuarioDTO usuario){
        return usuarioService.update(id, usuario);
    }
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id){
        return usuarioService.delete(id);
    }

}

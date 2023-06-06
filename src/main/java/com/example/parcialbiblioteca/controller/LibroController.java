package com.example.parcialbiblioteca.controller;

import com.example.parcialbiblioteca.dto.LibroDTO;
import com.example.parcialbiblioteca.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    LibroService libroService;

    @GetMapping
    public Object getAll(){
        return libroService.getAll();
    }
    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id) {
        return libroService.getById(id);
    }
    @PostMapping(consumes = {"application/json", "application/x-www-form-urlencoded"})
    public Object post(@Validated @RequestBody LibroDTO libro){
        return libroService.post(libro);
    }
}

package com.example.parcialbiblioteca.controller;

import com.example.parcialbiblioteca.entity.Prestamo;
import com.example.parcialbiblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/prestamos")
public class PrestamoController {
    @Autowired
    PrestamoService prestamoService;

    @GetMapping
    public Object getAll(){
        return prestamoService.getAll();
    }
    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id) {
        return prestamoService.getById(id);
    }
    @PostMapping(consumes = {"application/json", "application/x-www-form-urlencoded"})
    public Object post(@Validated @RequestBody Prestamo prestamo){
        return prestamoService.post(prestamo);
    }
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public Object update(@PathVariable("id") Long id, @Validated @RequestBody Prestamo prestamo){
        return prestamoService.update(id, prestamo);
    }
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id){
        return prestamoService.delete(id);
    }
}

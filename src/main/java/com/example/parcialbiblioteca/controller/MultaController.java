package com.example.parcialbiblioteca.controller;

import com.example.parcialbiblioteca.entity.Multa;
import com.example.parcialbiblioteca.service.MultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/multas")
public class MultaController {
    @Autowired
    MultaService multaService;

    @GetMapping
    public Object getAll(){
        return multaService.getAll();
    }
    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") Long id) {
        return multaService.getById(id);
    }
    @PostMapping(consumes = {"application/json", "application/x-www-form-urlencoded"})
    public Object post(@Validated @RequestBody Multa multa){
        return multaService.post(multa);
    }
    @PutMapping(value = "/{id}", consumes = {"application/json", "application/x-www-form-urlencoded"})
    public Object update(@PathVariable("id") Long id, @Validated @RequestBody Multa multa){
        return multaService.update(id, multa);
    }
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") Long id){
        return multaService.delete(id);
    }
}

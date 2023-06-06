package com.example.parcialbiblioteca.service;

import com.example.parcialbiblioteca.dto.LibroDTO;
import com.example.parcialbiblioteca.entity.Libro;
import com.example.parcialbiblioteca.exception.ResourceNotFoundException;
import com.example.parcialbiblioteca.repository.LibroRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("libroService")
public class LibroService {
    @Autowired
    LibroRepository libroRepository;
    @Autowired
    ModelMapper modelMapper;
    public List<Libro> getAll(){
        List<Libro> libroList = libroRepository.findAll();
        return libroList;
    }

    public Libro getById(Long id){
        Optional<Libro> libro = libroRepository.findById(id);
        if(!libro.isPresent()){
            throw new ResourceNotFoundException("No existe el libro con el id: " + id);
        }
        return libro.get();
    }

    public Libro post(LibroDTO libroDTO){
        Libro libro = modelMapper.map(libroDTO, Libro.class);
        return libroRepository.save(libro);
    }

    public Libro update(Long id, LibroDTO libroDTO){
        Optional<Libro> libroInDB = libroRepository.findById(id);
        if(!libroInDB.isPresent()){
            throw new ResourceNotFoundException("No existe el libro con el id: " + id);
        }
        Libro libro = modelMapper.map(libroDTO, Libro.class);
        libro.setId(id);
        libroRepository.save(libro);
        return libro;
    }

    public void delete(Long id){
        Optional<Libro> libroInDB = libroRepository.findById(id);
        if(!libroInDB.isPresent()){
            throw new ResourceNotFoundException("No existe el libro con el id: " + id);
        }
        libroRepository.deleteById(id);
    }

}

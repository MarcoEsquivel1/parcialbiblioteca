package com.example.parcialbiblioteca.service;

import com.example.parcialbiblioteca.dto.LibroInsertDTO;
import com.example.parcialbiblioteca.dto.LibroUpdateDTO;
import com.example.parcialbiblioteca.entity.Libro;
import com.example.parcialbiblioteca.exception.AttributeNotValidException;
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

    public Libro post(LibroInsertDTO libroInsertDTO){
        Libro libro = modelMapper.map(libroInsertDTO, Libro.class);
        return libroRepository.save(libro);
    }

    public Libro update(Long id, LibroUpdateDTO libroUpdateDTO){
        Optional<Libro> libroInDB = libroRepository.findById(id);
        if(!libroInDB.isPresent()){
            throw new ResourceNotFoundException("No existe el libro con el id: " + id);
        }
        Libro libro = modelMapper.map(libroUpdateDTO, Libro.class);
        libro.setIdLibro(id);
        //update only the fields that are not null in libro
        if(libro.getTitulo() == null){
            libro.setTitulo(libroInDB.get().getTitulo());
        }
        if(libro.getSlug() == null){
            libro.setSlug(libroInDB.get().getSlug());
        }
        if(libro.getAutor() == null){
            libro.setAutor(libroInDB.get().getAutor());
        }
        if(libro.getEditorial() == null){
            libro.setEditorial(libroInDB.get().getEditorial());
        }
        if(libro.getAnio() == null){
            libro.setAnio(libroInDB.get().getAnio());
        }
        if(libro.getGenero() == null){
            libro.setGenero(libroInDB.get().getGenero());
        }
        if(libro.getIdioma() == null){
            libro.setIdioma(libroInDB.get().getIdioma());
        }
        if(libro.getEstado() == null){
            libro.setEstado(libroInDB.get().getEstado());
        } else if (libro.getEstado().toUpperCase().equals("PRESTADO")){
            libro.setEstado("PRESTADO");
        } else if (libro.getEstado().toUpperCase().equals("DISPONIBLE")) {
            libro.setEstado("DISPONIBLE");
        } else {
            throw new AttributeNotValidException("El estado del libro debe ser PRESTADO o DISPONIBLE");
        }

        return libroRepository.save(libro);
    }

    public Libro delete(Long id){
        Optional<Libro> libroInDB = libroRepository.findById(id);
        if(!libroInDB.isPresent()){
            throw new ResourceNotFoundException("No existe el libro con el id: " + id);
        }
        libroRepository.deleteById(id);
        return libroInDB.get();
    }

}

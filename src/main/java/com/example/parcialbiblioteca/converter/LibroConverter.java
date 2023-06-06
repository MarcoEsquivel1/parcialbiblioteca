package com.example.parcialbiblioteca.converter;

import com.example.parcialbiblioteca.dto.LibroUpdateDTO;
import com.example.parcialbiblioteca.entity.Libro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LibroConverter {

    public LibroUpdateDTO entityToDto(Libro libro) {
        ModelMapper modelMapper = new ModelMapper();
        LibroUpdateDTO libroUpdateDTO = modelMapper.map(libro, LibroUpdateDTO.class);
        return libroUpdateDTO;
    }

    public Libro dtoToEntity(LibroUpdateDTO libroUpdateDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Libro libro = modelMapper.map(libroUpdateDTO, Libro.class);
        return libro;
    }
}

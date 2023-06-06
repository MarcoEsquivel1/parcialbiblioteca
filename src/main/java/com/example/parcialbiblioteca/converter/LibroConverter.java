package com.example.parcialbiblioteca.converter;

import com.example.parcialbiblioteca.dto.LibroDTO;
import com.example.parcialbiblioteca.entity.Libro;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LibroConverter {

    public LibroDTO entityToDto(Libro libro) {
        ModelMapper modelMapper = new ModelMapper();
        LibroDTO libroDTO = modelMapper.map(libro, LibroDTO.class);
        return libroDTO;
    }

    public Libro dtoToEntity(LibroDTO libroDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Libro libro = modelMapper.map(libroDTO, Libro.class);
        return libro;
    }
}

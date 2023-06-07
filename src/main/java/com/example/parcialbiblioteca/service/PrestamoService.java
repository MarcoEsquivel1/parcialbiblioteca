package com.example.parcialbiblioteca.service;

import com.example.parcialbiblioteca.entity.Libro;
import com.example.parcialbiblioteca.entity.Prestamo;
import com.example.parcialbiblioteca.entity.Usuario;
import com.example.parcialbiblioteca.exception.AttributeNotValidException;
import com.example.parcialbiblioteca.exception.ResourceNotFoundException;
import com.example.parcialbiblioteca.repository.LibroRepository;
import com.example.parcialbiblioteca.repository.PrestamoRepository;
import com.example.parcialbiblioteca.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("prestamoService")
public class PrestamoService {
    @Autowired
    PrestamoRepository prestamoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    LibroRepository libroRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<Prestamo> getAll(){
        return prestamoRepository.findAll();
    }

    public Prestamo getById(Long id){
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if(!prestamo.isPresent()){
            throw new ResourceNotFoundException("No existe el prestamo con el id: " + id);
        }
        return prestamo.get();
    }

    public Prestamo post(Prestamo prestamo){
        validateIfUsuarioExists(prestamo);
        validateIfLibroExists(prestamo);
        return prestamoRepository.save(prestamo);
    }

    public Prestamo update(Long id, Prestamo prestamo){
        Optional<Prestamo> prestamoInDB = prestamoRepository.findById(id);
        if(!prestamoInDB.isPresent()){
            throw new ResourceNotFoundException("No existe el prestamo con el id: " + id);
        }
        prestamo.setIdPrestamo(id);
        //update only the fields that are not null in prestamo
        if(prestamo.getFechaInscripcion() == null){
            prestamo.setFechaInscripcion(prestamoInDB.get().getFechaInscripcion());
        }
        if(prestamo.getFechaDevolucion() == null){
            prestamo.setFechaDevolucion(prestamoInDB.get().getFechaDevolucion());
        }
        if(prestamo.getIdUsuario() == null){
            prestamo.setIdUsuario(prestamoInDB.get().getIdUsuario());
        }
        if(prestamo.getIdLibro() == null){
            prestamo.setIdLibro(prestamoInDB.get().getIdLibro());
        }
        validateEstado(prestamo, prestamoInDB);
        return prestamoRepository.save(prestamo);
    }

    public Prestamo delete(Long id){
        Optional<Prestamo> prestamo = prestamoRepository.findById(id);
        if(!prestamo.isPresent()){
            throw new ResourceNotFoundException("No existe el prestamo con el id: " + id);
        }
        prestamoRepository.deleteById(id);
        return prestamo.get();
    }

    public void validateIfUsuarioExists(Prestamo prestamo){
        Optional<Usuario> usuario = usuarioRepository.findById(prestamo.getIdUsuario());
        if(!usuario.isPresent()){
            throw new AttributeNotValidException("No existe el usuario con el id: " + prestamo.getUsuario().getIdUsuario());
        }
    }

    public void validateIfLibroExists(Prestamo prestamo){
        Optional<Libro> libro = libroRepository.findById(prestamo.getIdLibro());
        if(!libro.isPresent()){
            throw new AttributeNotValidException("No existe el libro con el id: " + prestamo.getLibro().getIdLibro());
        }
    }

    public void validateFechaInscripcion(Prestamo prestamo, Optional<Prestamo> prestamoInDB){
        if(prestamo.getFechaInscripcion() != null && prestamoInDB.isPresent()){
            throw new AttributeNotValidException("No se puede modificar la fecha de inscripcion");
        }
    }

    public void  validateEstado(Prestamo prestamo, Optional<Prestamo> prestamoInDB){
        if(prestamo.getEstado() == null){
            prestamo.setEstado(prestamoInDB.get().getEstado());
        } else if (prestamo.getEstado().toUpperCase().equals("SIN VERIFICAR")){
            prestamo.setEstado("SIN VERIFICAR");
        } else if (prestamo.getEstado().toUpperCase().equals("VERIFICADO")){
            prestamo.setEstado("VERIFICADO");
        } else {
            throw new AttributeNotValidException("El estado del prestamo debe ser SIN VERIFICAR o VERIFICADO");
        }
    }

    public void validateEstado(Prestamo prestamo){
        if(prestamo.getEstado() != null && !prestamo.getEstado().toUpperCase().equals("SIN VERIFICAR") && !prestamo.getEstado().toUpperCase().equals("VERIFICADO")){
            throw new AttributeNotValidException("El estado del prestamo debe ser SIN VERIFICAR o VERIFICADO");
        }
    }
}

package com.example.parcialbiblioteca.service;

import com.example.parcialbiblioteca.entity.Multa;
import com.example.parcialbiblioteca.entity.Prestamo;
import com.example.parcialbiblioteca.exception.AttributeNotValidException;
import com.example.parcialbiblioteca.exception.ResourceNotFoundException;
import com.example.parcialbiblioteca.repository.MultaRepository;
import com.example.parcialbiblioteca.repository.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("multaService")
public class MultaService {
    @Autowired
    MultaRepository multaRepository;
    @Autowired
    PrestamoRepository prestamoRepository;

    public List<Multa> getAll() {
        return multaRepository.findAll();
    }

    public Multa getById(Long id) {
        Optional<Multa> multa = multaRepository.findById(id);
        if (!multa.isPresent()) {
            throw new ResourceNotFoundException("No existe la multa con el id: " + id);
        }
        return multa.get();
    }

    public Multa post(Multa multa) {
        validateIfExistMultaofPrestamo(multa);
        return multaRepository.save(multa);
    }

    public Multa update(Long id, Multa multa) {
        Optional<Multa> multaInDB = multaRepository.findById(id);
        if (!multaInDB.isPresent()) {
            throw new ResourceNotFoundException("No existe la multa con el id: " + id);
        }
        multa.setIdMulta(id);
        //update only the fields that are not null in multa
        if (multa.getMonto() == null) {
            multa.setMonto(multaInDB.get().getMonto());
        }
        if (multa.getDescripcion() == null) {
            multa.setDescripcion(multaInDB.get().getDescripcion());
        }
        if (multa.getIdPrestamo() == null) {
            multa.setIdPrestamo(multaInDB.get().getIdPrestamo());
        }
        if (multa.getFechaPago() == null) {
            multa.setFechaPago(multaInDB.get().getFechaPago());
        }
        validateEstado(multa, multaInDB);
        multa.setEstado(multa.getEstado().toUpperCase());
        return multaRepository.save(multa);
    }

    public Multa delete(Long id) {
        Optional<Multa> multaInDB = multaRepository.findById(id);
        if (!multaInDB.isPresent()) {
            throw new ResourceNotFoundException("No existe la multa con el id: " + id);
        }
        multaRepository.deleteById(id);
        return multaInDB.get();
    }

    public void validateIfExistMultaofPrestamo(Multa multa) {
        Optional<Multa> multaInDB = multaRepository.findByPrestamo(multa.getIdPrestamo());
        if (multaInDB.isPresent()) {
            throw new AttributeNotValidException("Ya existe una multa para el prestamo con id: " + multa.getPrestamo().getIdPrestamo());
        }
    }

    public void validateEstado (Multa multa, Optional<Multa> multaInDB){
        if (multa.getEstado() == null) {
            multa.setEstado(multaInDB.get().getEstado());
        } else if (multa.getEstado().toUpperCase().equals("PAGADO")) {
            multa.setEstado("PAGADO");
        } else if (multa.getEstado().toUpperCase().equals("PENDIENTE")) {
            multa.setEstado("PENDIENTE");
        }else {
            throw new AttributeNotValidException("El estado de la multa debe ser PAGADO o PENDIENTE");
        }
    }

    public void validateIfPrestamoExist(Multa multa) {
        Optional< Prestamo > prestamo = prestamoRepository.findById(multa.getPrestamo().getIdPrestamo());
        if (!prestamo.isPresent()) {
            throw new ResourceNotFoundException("No existe el prestamo con el id: " + multa.getPrestamo().getIdPrestamo());
        }
    }


}

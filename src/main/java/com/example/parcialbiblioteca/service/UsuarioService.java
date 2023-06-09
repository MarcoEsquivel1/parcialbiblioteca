package com.example.parcialbiblioteca.service;

import com.example.parcialbiblioteca.dto.UsuarioInsertDTO;
import com.example.parcialbiblioteca.dto.UsuarioUpdateDTO;
import com.example.parcialbiblioteca.entity.Usuario;
import com.example.parcialbiblioteca.exception.AttributeNotValidException;
import com.example.parcialbiblioteca.exception.ResourceNotFoundException;
import com.example.parcialbiblioteca.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("usuarioService")
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    ModelMapper modelMapper;

    public List<Usuario> getAll(){
        List<Usuario> usuarioList = usuarioRepository.findAll();
        //replace password with null
        for(Usuario usuario : usuarioList){
            usuario.setPassword(null);
        }
        return usuarioList;
    }

    public Usuario getById(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()){
            throw new ResourceNotFoundException("No existe el usuario con el id: " + id);
        }
        //replace password with null
        usuario.get().setPassword(null);
        return usuario.get();
    }

    public Usuario post(UsuarioInsertDTO usuarioInsertDTO){
        Usuario usuario = modelMapper.map(usuarioInsertDTO, Usuario.class);
        validateIfDuiExists(usuario);
        validateIfCorreoExists(usuario);
        validateRol(usuario);
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, UsuarioUpdateDTO usuarioUpdateDTO){
        Optional<Usuario> usuarioInDB = usuarioRepository.findById(id);
        if(!usuarioInDB.isPresent()){
            throw new ResourceNotFoundException("No existe el usuario con el id: " + id);
        }
        Usuario usuario = modelMapper.map(usuarioUpdateDTO, Usuario.class);
        usuario.setIdUsuario(id);
        //update only the fields that are not null in usuario
        if(usuario.getNombre() == null){
            usuario.setNombre(usuarioInDB.get().getNombre());
        }
        if(usuario.getApellido() == null){
            usuario.setApellido(usuarioInDB.get().getApellido());
        }
        if(usuario.getDui() == null){
            usuario.setDui(usuarioInDB.get().getDui());
        }
        if(usuario.getDireccion() == null){
            usuario.setDireccion(usuarioInDB.get().getDireccion());
        }
        if(usuario.getTelefono() == null){
            usuario.setTelefono(usuarioInDB.get().getTelefono());
        }
        if(usuario.getEmail() == null){
            usuario.setEmail(usuarioInDB.get().getEmail());
        }
        if(usuario.getPassword() == null){
            usuario.setPassword(usuarioInDB.get().getPassword());
        }
        if(usuario.getFechaInscripcion() == null){
            usuario.setFechaInscripcion(usuarioInDB.get().getFechaInscripcion());
        }
        validateIfDuiExists(usuario);
        validateIfCorreoExists(usuario);
        validateRol(usuario, usuarioInDB);
        usuario.setRol(usuario.getRol().toUpperCase());
        Usuario usuarioUpdated = usuarioRepository.save(usuario);
        //replace password with null
        usuarioUpdated.setPassword(null);
        return usuarioUpdated;
    }

    public Usuario delete(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()){
            throw new ResourceNotFoundException("No existe el usuario con el id: " + id);
        }
        usuarioRepository.deleteById(id);
        return usuario.get();
    }

    public void validateRol(Usuario usuario, Optional<Usuario> usuarioInDB){
        if(usuario.getRol() == null){
            usuario.setRol(usuarioInDB.get().getRol());
        }else if(usuario.getRol().toUpperCase().equals("ADMIN")){
            usuario.setRol("ADMIN");
        } else if (usuario.getRol().toUpperCase().equals("USUARIO")) {
            usuario.setRol("USUARIO");
        }else{
            throw new AttributeNotValidException("El rol debe ser ADMIN o USUARIO");
        }
    }

    public void validateRol(Usuario usuario){
        if (usuario.getRol() != null && !usuario.getRol().toUpperCase().equals("USUARIO") && !usuario.getRol().toUpperCase().equals("ADMIN")) {
            throw new AttributeNotValidException("El rol debe ser ADMIN o USUARIO");
        }
    }

    public void validateIfDuiExists(Usuario usuario){
        if(usuarioRepository.findByDui(usuario.getDui()).isPresent() && usuarioRepository.findByDui(usuario.getDui()).get().getIdUsuario() != usuario.getIdUsuario()){
            throw new AttributeNotValidException("Ya existe un usuario con el DUI: " + usuario.getDui());
        }
    }

    public void validateIfCorreoExists(Usuario usuario){
        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent() && usuarioRepository.findByEmail(usuario.getEmail()).get().getIdUsuario() != usuario.getIdUsuario()){
            throw new AttributeNotValidException("Ya existe un usuario con el correo: " + usuario.getEmail());
        }
    }
}

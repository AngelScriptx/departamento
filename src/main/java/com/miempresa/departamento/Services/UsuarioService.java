package com.miempresa.departamento.Services;

import com.miempresa.departamento.Models.Usuario;
import com.miempresa.departamento.Repository.UsuarioRepository;

import java.util.List;

import org.springframework.stereotype.Service;
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    
}

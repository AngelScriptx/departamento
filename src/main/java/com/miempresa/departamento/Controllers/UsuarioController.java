package com.miempresa.departamento.Controllers;

import com.miempresa.departamento.Models.Usuario;
import com.miempresa.departamento.Services.UsuarioService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return service.obtenerTodosLosUsuarios();
    }
    @PostMapping
    public Usuario crear(@RequestBody Usuario usuario) {
        return service.crearUsuario(usuario);
    }
}

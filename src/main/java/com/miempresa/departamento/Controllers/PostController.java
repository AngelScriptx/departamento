package com.miempresa.departamento.Controllers;

import com.miempresa.departamento.Dto.*;
import com.miempresa.departamento.Models.Post;
import com.miempresa.departamento.Models.Usuario;
import com.miempresa.departamento.Services.PostService;

import lombok.AllArgsConstructor;

import com.miempresa.departamento.Repository.PostRepository;
import com.miempresa.departamento.Repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService service;
    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;


    @GetMapping
    public List<PostGetDTO> getAll() {
        return service.obtenerPostsSinIdPorQueryNativa();
    }

    @PostMapping
    public PostGetDTO crearPost(@RequestBody PostCreateDTO postCreateDTO) {
        return service.crearPostContrato(postCreateDTO);
       /*
       FORMA 2
       Usuario usuario = usuarioRepository.findById(postCreateDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

    Post post = Post.builder()
            .titulo(postCreateDTO.getTitulo())
            .contenido(postCreateDTO.getContenido())
            .usuario(usuario)
            .fechaCreacion(LocalDateTime.now())
            .build();
 

    return postRepository.save(post);
    */
    
    /*
    FORMA 3

    return service.CrearPostsConUsuarioPreliminarPorJPA(postCreateDTO);
    */
        
    }
}
package com.miempresa.departamento.Services;

import com.miempresa.departamento.Dto.PostCreateDTO;
import com.miempresa.departamento.Dto.PostGetDTO;
import com.miempresa.departamento.Models.Post;
import com.miempresa.departamento.Models.Usuario;
import com.miempresa.departamento.Repository.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UsuarioRepository usuarioRepository;

    
    public Post guardarAsignaciones( PostCreateDTO post ) {
        return postRepository.save(post);
    }
    public PostGetDTO crearPostContrato(PostCreateDTO post) {
        
        Post postNuevo = new Post();
        postNuevo.setTitulo(post.getTitulo());
        postNuevo.setContenido(post.getContenido());
        postNuevo.setUsuario(usuarioRepository.findById(post.getUsuarioId()).get());
        postNuevo.setFechaCreacion(LocalDateTime.now());

        Post postGuardado = postRepository.save(postNuevo);

        return new PostGetDTO(
            postGuardado.getId(),
            postGuardado.getTitulo(),
            postGuardado.getContenido(),
            postGuardado.getUsuario().getId(),
            postGuardado.getUsuario().getNombre()
        );
    }

    public List<PostGetDTO> obtenerTodosLosPostsPorJPA() {

        return postRepository
           .findAll()
           .stream()
            .map(post -> new PostGetDTO(
                post.getId(),
                post.getTitulo(),
                post.getContenido(),
                post.getUsuario().getId(),
                post.getUsuario().getNombre()
            )).toList();            
            
        /*
        return postRepository.findAll().stream().map(post -> {
        
            PostGetDTO dto = new PostGetDTO();
            dto.setId(post.getId());
            dto.setTitulo(post.getTitulo());
            dto.setContenido(post.getContenido());

            // Mapear usuario
            dto.setUsuarioId(post.getUsuario().getId());
            dto.setUsuarioNombre(post.getUsuario().getNombre());

            return dto;
        }).toList();
        */
    }

        public List<PostGetDTO> obtenerPostsSinIdPorQueryNativa() {
        List<Object[]> resultados = postRepository.obtenerPostPaseadoSinId();

        return resultados.stream().map(obj ->
            new PostGetDTO(
                null, // id no incluido
                (String) obj[0], // titulo
                (String) obj[1], // contenido
                ((Number) obj[2]).longValue(), // usuarioId
                (String) obj[3] // usuarioNombre
            )
        ).collect(Collectors.toList());
        }

    // OBTENIDO POR QUERY NATIVE
    public List<PostGetDTO> obtenerTodosLosPostsPorQueryNativa() {

        List<Object[]> resultados = postRepository.obtenerPostsParseadosPorQueryNativa();

        return resultados.stream().map(obj -> new PostGetDTO(
                ((Number) obj[0]).longValue(), // id
                (String) obj[1], // titulo
                (String) obj[2], // contenido
                ((Number) obj[3]).longValue(), // usuarioId
                (String) obj[4] // usuarioNombre
        )).collect(Collectors.toList());
    }

    public Post CrearPostsConUsuarioPreliminarPorJPA(PostCreateDTO data) {
        /*        PostCreateDTO post = PostCreateDTO.builder()
                .titulo(data.getTitulo())
                .contenido(data.getContenido())
                .usuarioId(1L)
                .build();
     */
   
    // Usuario por defecto (ID = 2)
    Usuario usuario = usuarioRepository.findById(2L)
            .orElseThrow(() -> new RuntimeException("Usuario por defecto no encontrado"));

    Post post = new Post();
    post.setTitulo(data.getTitulo());
    post.setContenido(data.getContenido());
    post.setUsuario(usuario);       
    
    return     postRepository.save(post);

}
}

package com.miempresa.departamento.Controllers;

import com.miempresa.departamento.Dto.*;
import com.miempresa.departamento.Models.Post;
import com.miempresa.departamento.Models.Usuario;
import com.miempresa.departamento.Services.PostService;

import lombok.AllArgsConstructor;

import com.miempresa.departamento.Repository.PostRepository;
import com.miempresa.departamento.Repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType; //IMPORTANTE AGREGAR 
import com.fasterxml.jackson.databind.ObjectMapper; //IMPORTANTE AGREGAR
import com.fasterxml.jackson.core.JsonProcessingException; //IMPORTANTE AGREGAR
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;





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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/crear-asignacion", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearAsignacion(
            @RequestParam("data") String data,
            @RequestParam("imagenes") List<MultipartFile> imagenes
    ) {
        System.out.println("data: " + data);
        System.out.println("Número de imágenes recibidas: " + imagenes.size());
        ObjectMapper mapper = new ObjectMapper();
        List<AsignacionDTO> asignaciones = new ArrayList<>(); //NO EXISTE ALGUN METODO DIRECTO PARA CONVERTIR

        try {
            asignaciones = mapper.readValue(data, new TypeReference<List<AsignacionDTO>>() {});

            System.out.println("Asignaciones parseadas: " + asignaciones);

        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Error al parsear JSON");
        }

        for (MultipartFile imagen : imagenes) {
            if (!imagen.isEmpty()) {
                try {
                    String ruta = "uploads/epp/" + imagen.getOriginalFilename();
                    File dest = new File(ruta);
                    dest.getParentFile().mkdirs();
                    imagen.transferTo(dest);
                } catch (IOException e) {
                    return ResponseEntity.status(500).body("Error al guardar imagen: " + imagen.getOriginalFilename());
                }
            }
        }

        // Guardar en base de datos usando servicio

        List<Post> posts = asignaciones.stream()
    .map(dto -> {
        Post post = new Post();
        post.setTitulo(dto.getUsuarioId()); // ejemplo, según tus campos
        post.setContenido("Contenido de ejemplo");
        post.setFechaCreacion(LocalDateTime.now());
        return post;
    })
    .toList();

postRepository.saveAll(posts);
        // service.guardarAsignaciones( asignaciones);

        return ResponseEntity.ok("Asignación creada correctamente");
    }
 
}
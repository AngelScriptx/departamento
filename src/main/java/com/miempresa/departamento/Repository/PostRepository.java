package com.miempresa.departamento.Repository;



import com.miempresa.departamento.Dto.PostGetDTO;
import com.miempresa.departamento.Models.Post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface PostRepository extends JpaRepository<Post, Long> {

@Query(
        value = "SELECT p.id AS id, p.titulo AS titulo, p.contenido AS contenido, " +
                "u.id AS usuarioId, u.nombre AS usuarioNombre " +
                "FROM post p " +
                "JOIN usuario u ON p.usuario_id = u.id",
        nativeQuery = true
    )
    List<Object[]> obtenerPostsParseadosPorQueryNativa();
  
        @Query(
                value = "SELECT p.titulo AS titulo, p.contenido AS contenido, " +
                        "u.id AS usuarioId, u.nombre AS usuarioNombre " +
                        "FROM post p " +
                        "JOIN usuario u ON p.usuario_id = u.id",
                nativeQuery = true
        )
    List<Object[]> obtenerPostPaseadoSinId();
}

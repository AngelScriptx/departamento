package com.miempresa.departamento.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "contenido")
    private String contenido;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    // Relación ManyToOne con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id") // columna FK
    private Usuario usuario;
}

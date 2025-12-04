package com.miempresa.departamento.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

@NoArgsConstructor
@AllArgsConstructor 
@Builder
public class PostGetDTO {
    private Long id;
    private String titulo;
    private String contenido;
    private Long usuarioId;
    private String usuarioNombre;
}

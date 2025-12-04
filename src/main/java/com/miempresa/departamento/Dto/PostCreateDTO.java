package com.miempresa.departamento.Dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateDTO {
    private String titulo;
    private String contenido;
    private Long usuarioId;
}

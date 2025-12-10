package com.miempresa.departamento.Dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data

@NoArgsConstructor
@AllArgsConstructor 
@Builder
public class AsignacionDTO {
private String usuarioId;
private Long postId;
}

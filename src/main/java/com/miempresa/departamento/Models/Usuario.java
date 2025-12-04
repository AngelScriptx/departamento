package com.miempresa.departamento.Models;
/*Paquete de JPA (Jakarta Persistence API) que permite mapear clases de Java a tablas de bases de datos. 

@Entity: Indica que la clase es una entidad que se mapeará a una tabla de base de datos.
@Id: Indica el campo que será la clave primaria de la entidad.
@GeneratedValue: Especifica cómo se generará el valor de la clave primaria.
@Column: Permite personalizar el mapeo de un campo a una columna de la tabla.
@Table: Define el nombre de la tabla si quieres que sea distinto al de la clase
@ManyToOne, @OneToMany: Anotaciones para definir relaciones entre entidades.
*/

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

/*Lombok es una librería que reduce el código repetitivo en Java.
Con unas pocas anotaciones puedes evitar escribir manualmente getters, setters, constructores, toString, equals, hashCode, etc. 

@Data: Genera getters, setters, toString, equals, hashCode
@NoArgsConstructor: Genera un constructor sin argumentos.
@AllArgsConstructor: Genera un constructor con todos los argumentos.
@Builder: Proporciona un patrón de diseño Builder para crear instancias de la clase. ejemplo:
Departamento dept = Departamento.builder()
                              .id(1L)
                              .departamento("Ventas")
                              .build();
*/
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;


//   @OneToMany(mappedBy = "usuario")
// private List<Post> posts;
}

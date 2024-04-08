package com.jpa.tarea_274.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Socio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    private String direccion;

    @NotBlank
    private String telefono;

    // @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    // private List<Barco> barcos;
}

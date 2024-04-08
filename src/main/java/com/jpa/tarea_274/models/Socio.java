package com.jpa.tarea_274.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Socio extends Persona {

    @NotBlank
    private String direccion;

    @NotBlank
    private String telefono;

    // @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL)
    // private List<Barco> barcos;
}

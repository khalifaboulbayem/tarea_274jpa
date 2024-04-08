package com.jpa.tarea_274.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocioDto {

    private String nombre;

    private String direccion;

    private String telefono;
}

package com.jpa.tarea_274.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpa.tarea_274.Exceptions.FieldInvalidException;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TripDto {

    @JsonFormat(pattern = ("yyyy/MM/dd HH:mm:ss"))
    private LocalDateTime fechaTrip;

    private String destino;

    private Long patronId;

    private Long boatId;

    public void setFechaTrip(LocalDateTime date) {
        if (date.isBefore(LocalDateTime.now())) {
            throw new FieldInvalidException("La fecha de trip no debe ser menor a la de hoy!");
        }
        this.fechaTrip = date;
    }
}
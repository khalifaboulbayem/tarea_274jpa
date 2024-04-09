package com.jpa.tarea_274.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.*;
import com.jpa.tarea_274.Exceptions.FieldInvalidException;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = ("yyyy/MM/dd HH:mm:ss"))
    private LocalDateTime fechaTrip;

    private String destino;

    @OneToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @ManyToOne
    @JoinColumn(name = "boat_id")
    @JsonIgnore
    private Boat boat;

    public void setFechaTrip(LocalDateTime date) {
        if (date.isBefore(LocalDateTime.now())) {
            throw new FieldInvalidException("La fecha de trip no debe ser menor a la de hoy!");
        }
        this.fechaTrip = date;
    }
}

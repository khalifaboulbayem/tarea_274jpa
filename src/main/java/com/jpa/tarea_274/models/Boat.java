package com.jpa.tarea_274.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer license;

    @Column(unique = true)
    private String name;

    private Integer mooringNumber;

    @Digits(integer = 10, fraction = 2, message = "Formato invalido!")
    @DecimalMin(value = "0.01", message = "El valor minimo debe ser 0,01")
    @DecimalMax(value = "9999999.99", message = "El valor maximo debe ser 9999999.99")
    private BigDecimal fee;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @OneToMany(mappedBy = "boat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Trip> trips;

}

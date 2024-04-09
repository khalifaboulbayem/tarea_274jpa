package com.jpa.tarea_274.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank(message = "La dirección no debe ser vacio!")
    private String address;

    @NotBlank(message = "El nombre no debe ser vacio")
    @Column(unique = true)
    private String phone;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Boat> boats;
}

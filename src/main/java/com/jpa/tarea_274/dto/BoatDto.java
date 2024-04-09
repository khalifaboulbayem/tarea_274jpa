package com.jpa.tarea_274.dto;

import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoatDto {

    private Integer license;
    private String name;
    private Integer mooringNumber;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal fee;
    private Long memberId;

}

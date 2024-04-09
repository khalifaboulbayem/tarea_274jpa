package com.jpa.tarea_274.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {

    private String name;

    private String address;

    private String phone;
}

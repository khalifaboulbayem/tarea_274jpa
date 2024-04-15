package com.jpa.tarea_274.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long Id;
    private String email;
    private String username;
    private String password;
}

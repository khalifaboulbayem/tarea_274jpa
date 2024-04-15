package com.jpa.tarea_274.dto.responses;

import com.jpa.tarea_274.emuns.Role;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String username;
    private Role role;

}

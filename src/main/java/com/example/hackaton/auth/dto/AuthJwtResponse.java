package com.example.hackaton.auth.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AuthJwtResponse {
    public String token;
    public Long id;
<<<<<<< HEAD

=======
    public String name;
    public String email;
    public ZonedDateTime fechaDeRegistro;
>>>>>>> mateoAcabado
}
package com.example.hackaton.auth.dto;

import lombok.Data;

@Data
public class AuthJwtResponse {
    public String token;
    public Long id;

}
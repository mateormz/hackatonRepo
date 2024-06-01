package com.example.hackaton.auth.dto;

import lombok.Data;

@Data
public class AuthLoginRequest {
    public String username;
    public String password;
}
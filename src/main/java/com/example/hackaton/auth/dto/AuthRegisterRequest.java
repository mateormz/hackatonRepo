package com.example.hackaton.auth.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AuthRegisterRequest {
    String name;
    String email;
    String password;
}
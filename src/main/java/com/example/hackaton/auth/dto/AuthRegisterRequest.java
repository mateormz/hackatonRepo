package com.example.hackaton.auth.dto;

import lombok.Data;

@Data
public class AuthRegisterRequest {
    String firstName;
    String lastName;
    String email;
    String password;
    Boolean isOwner = false;
    String phoneNumber;
}
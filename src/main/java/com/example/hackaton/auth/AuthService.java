package com.example.hackaton.auth;

import com.example.hackaton.auth.dto.AuthJwtResponse;
import com.example.hackaton.auth.dto.AuthLoginRequest;
import com.example.hackaton.auth.dto.AuthRegisterRequest;
import com.example.hackaton.configuration.JwtService;
import com.example.hackaton.user.domain.User;
import com.example.hackaton.user.infrastructure.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class AuthService {

    final UserRepository<User> baseUserRepository;
    final JwtService jwtService;
    final PasswordEncoder passwordEncoder;
    final ModelMapper modelMapper;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.baseUserRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    public AuthJwtResponse login(AuthLoginRequest req) {
        Optional<User> user = baseUserRepository.findByEmail(req.getUsername());

        if (user.isEmpty()) throw new UsernameNotFoundException("Email is not registered");

        if (!passwordEncoder.matches(req.getPassword(), user.get().getPassword()))
            throw new IllegalArgumentException("Password is incorrect");

        AuthJwtResponse response = new AuthJwtResponse();

        response.setToken(jwtService.generateToken(user.get()));
        response.setId(user.get().getId());
        response.setName(user.get().getName());
        response.setEmail(user.get().getEmail());
        response.setFechaDeRegistro(user.get().getFechaDeRegistro());
        return response;
    }

    public AuthJwtResponse register(AuthRegisterRequest req) {
        Optional<User> existingUser = baseUserRepository.findByEmail(req.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = new User();
        user.setFechaDeRegistro(ZonedDateTime.now());
        user.setName(req.getName());
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        baseUserRepository.save(user);

        AuthJwtResponse response = new AuthJwtResponse();
        response.setToken(jwtService.generateToken(user));
        response.setId(user.getId());
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setFechaDeRegistro(user.getFechaDeRegistro());
        return response;
    }
}
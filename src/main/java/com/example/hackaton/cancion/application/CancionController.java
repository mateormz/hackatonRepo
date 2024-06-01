package com.example.hackaton.cancion.application;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cancion")
public class CancionController {
    private final CancionService cancionService;

}

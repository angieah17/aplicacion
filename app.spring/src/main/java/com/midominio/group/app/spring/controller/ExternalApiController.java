package com.midominio.group.app.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midominio.group.app.spring.dto.ExternalActivityDTO;
import com.midominio.group.app.spring.service.ExternalApiService;

@RestController
@RequestMapping("/api/external")
public class ExternalApiController {

    private final ExternalApiService externalApiService;

    public ExternalApiController(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    @GetMapping("/activity")
    public ResponseEntity<ExternalActivityDTO> obtenerActividad() {
        ExternalActivityDTO activity = externalApiService.obtenerActividadAleatoria();
        return ResponseEntity.ok(activity);
    }
}
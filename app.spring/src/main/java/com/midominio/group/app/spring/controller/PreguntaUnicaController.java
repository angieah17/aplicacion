package com.midominio.group.app.spring.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.midominio.group.app.spring.entity.PreguntaUnica;
import com.midominio.group.app.spring.service.PreguntaUnicaService;

import jakarta.validation.Valid;

/* Controlador específico para preguntas de opción única.
   Reusa AbstractPreguntaService para operaciones comunes.
*/
@Deprecated(since = "2026-02", forRemoval = false)
@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/preguntas/unica")
public class PreguntaUnicaController {

    private final PreguntaUnicaService service;

    public PreguntaUnicaController(PreguntaUnicaService service) {
        this.service = service;
    }

    @GetMapping
    public Page<PreguntaUnica> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreguntaUnica> obtener(@PathVariable Long id) {
        Optional<PreguntaUnica> pregunta = service.findById(id);
        return pregunta.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PreguntaUnica crear(@Valid @RequestBody PreguntaUnica pregunta) {
        return service.crear(pregunta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PreguntaUnica> desactivar(@PathVariable Long id) {
        PreguntaUnica pregunta = service.desactivar(id);
        return ResponseEntity.ok(pregunta);
    }

    @PutMapping("/activar/{id}")
    public ResponseEntity<PreguntaUnica> activar(@PathVariable Long id) {
        PreguntaUnica pregunta = service.activar(id);
        return ResponseEntity.ok(pregunta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreguntaUnica> actualizar(@PathVariable Long id, @Valid @RequestBody PreguntaUnica datos) {
        return service.findById(id)
                .map(p -> {
                    p.setEnunciado(datos.getEnunciado());
                    p.setTematica(datos.getTematica());
                 // ← LIMPIA LA LISTA ANTES DE ACTUALIZAR
                    p.getOpciones().clear();
                    p.getOpciones().addAll(datos.getOpciones());
                    p.setRespuestaCorrecta(datos.getRespuestaCorrecta());
                    p.setExplicacion(datos.getExplicacion());
                    return ResponseEntity.ok(service.save(p));
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
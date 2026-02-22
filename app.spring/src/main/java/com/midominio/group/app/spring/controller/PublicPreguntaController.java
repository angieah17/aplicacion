package com.midominio.group.app.spring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midominio.group.app.spring.dto.PublicPreguntaDTO;
import com.midominio.group.app.spring.entity.Pregunta;
import com.midominio.group.app.spring.repository.PreguntaRepository;
import com.midominio.group.app.spring.repository.PreguntaSpecifications;

@RestController
@RequestMapping("/api/public")
public class PublicPreguntaController {

    private final PreguntaRepository preguntaRepository;

    public PublicPreguntaController(PreguntaRepository preguntaRepository) {
        this.preguntaRepository = preguntaRepository;
    }

    @GetMapping("/preguntas")
    public List<PublicPreguntaDTO> listarPreguntasPublicas() {
        List<Pregunta> preguntas = preguntaRepository.findAll(PreguntaSpecifications.activas());

        return preguntas.stream()
            .map(p -> new PublicPreguntaDTO(
                p.getId(),
                p.getEnunciado(),
                p.getTematica(),
                p.getTipoPregunta()
            ))
            .collect(Collectors.toList());
    }
}

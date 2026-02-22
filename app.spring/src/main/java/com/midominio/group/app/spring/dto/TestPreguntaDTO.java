package com.midominio.group.app.spring.dto;

import java.util.List;

/**
 * DTO que representa una pregunta individual dentro de un test.
 * No incluye respuestas correctas ni explicaciones para evitar spoilers.
 */
public class TestPreguntaDTO {
    
    private Long id;
    private String enunciado;
    private String tipoPregunta; // VERDADERO_FALSO, UNICA, MULTIPLE
    private List<String> opciones; // Solo para UNICA y MULTIPLE
    
    
    // CONSTRUCTORES
    
    public TestPreguntaDTO() {
    }
    
    public TestPreguntaDTO(Long id, String enunciado, String tipoPregunta, List<String> opciones) {
        this.id = id;
        this.enunciado = enunciado;
        this.tipoPregunta = tipoPregunta;
        this.opciones = opciones;
    }
    
    
    // GETTERS Y SETTERS
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }
}

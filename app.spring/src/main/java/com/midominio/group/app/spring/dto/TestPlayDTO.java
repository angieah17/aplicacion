package com.midominio.group.app.spring.dto;

import java.util.List;

/**
 * DTO que contiene las preguntas generadas para un test.
 * No incluye respuestas correctas para evitar spoilers.
 */
public class TestPlayDTO {
    
    private List<TestPreguntaDTO> preguntas;
    private String tematica;
    private String tipoPregunta;
    private Integer totalPreguntas;
    
    
    // CONSTRUCTORES
    
    public TestPlayDTO() {
    }
    
    public TestPlayDTO(List<TestPreguntaDTO> preguntas, String tematica, String tipoPregunta, Integer totalPreguntas) {
        this.preguntas = preguntas;
        this.tematica = tematica;
        this.tipoPregunta = tipoPregunta;
        this.totalPreguntas = totalPreguntas;
    }
    
    
    // GETTERS Y SETTERS
    
    public List<TestPreguntaDTO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<TestPreguntaDTO> preguntas) {
        this.preguntas = preguntas;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(String tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public Integer getTotalPreguntas() {
        return totalPreguntas;
    }

    public void setTotalPreguntas(Integer totalPreguntas) {
        this.totalPreguntas = totalPreguntas;
    }
}

package com.midominio.group.app.spring.dto;

import java.util.List;

/**
 * DTO que contiene el resultado de la corrección de un test.
 * Incluye puntuación, revisión de cada pregunta con explicaciones.
 */
public class TestResultDTO {
    
    private Double puntuacion;
    private Integer totalPreguntas;
    private Integer preguntasCorrectas;
    private Integer preguntasIncorrectas;
    private Double porcentajeAcierto;
    private List<RevisionPreguntaDTO> revision;
    
    
    // CONSTRUCTORES
    
    public TestResultDTO() {
    }
    
    public TestResultDTO(Double puntuacion, Integer totalPreguntas, Integer preguntasCorrectas,
                        Integer preguntasIncorrectas, Double porcentajeAcierto, List<RevisionPreguntaDTO> revision) {
        this.puntuacion = puntuacion;
        this.totalPreguntas = totalPreguntas;
        this.preguntasCorrectas = preguntasCorrectas;
        this.preguntasIncorrectas = preguntasIncorrectas;
        this.porcentajeAcierto = porcentajeAcierto;
        this.revision = revision;
    }
    
    
    // GETTERS Y SETTERS
    
    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Integer getTotalPreguntas() {
        return totalPreguntas;
    }

    public void setTotalPreguntas(Integer totalPreguntas) {
        this.totalPreguntas = totalPreguntas;
    }

    public Integer getPreguntasCorrectas() {
        return preguntasCorrectas;
    }

    public void setPreguntasCorrectas(Integer preguntasCorrectas) {
        this.preguntasCorrectas = preguntasCorrectas;
    }

    public Integer getPreguntasIncorrectas() {
        return preguntasIncorrectas;
    }

    public void setPreguntasIncorrectas(Integer preguntasIncorrectas) {
        this.preguntasIncorrectas = preguntasIncorrectas;
    }

    public Double getPorcentajeAcierto() {
        return porcentajeAcierto;
    }

    public void setPorcentajeAcierto(Double porcentajeAcierto) {
        this.porcentajeAcierto = porcentajeAcierto;
    }

    public List<RevisionPreguntaDTO> getRevision() {
        return revision;
    }

    public void setRevision(List<RevisionPreguntaDTO> revision) {
        this.revision = revision;
    }
}

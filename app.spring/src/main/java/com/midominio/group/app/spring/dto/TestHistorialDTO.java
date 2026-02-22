package com.midominio.group.app.spring.dto;

import java.time.LocalDateTime;

/**
 * DTO que representa un registro del historial de tests realizados por un usuario.
 * Contiene información sobre la puntuación, temática y fecha de realización.
 */
public class TestHistorialDTO {
    
    private Long id;
    private Double puntuacion;
    private String tematica;
    private LocalDateTime fechaRealizacion;
    
    
    // CONSTRUCTORES
    
    public TestHistorialDTO() {
    }
    
    public TestHistorialDTO(Long id, Double puntuacion, String tematica, LocalDateTime fechaRealizacion) {
        this.id = id;
        this.puntuacion = puntuacion;
        this.tematica = tematica;
        this.fechaRealizacion = fechaRealizacion;
    }
    
    
    // GETTERS Y SETTERS
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public LocalDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
}

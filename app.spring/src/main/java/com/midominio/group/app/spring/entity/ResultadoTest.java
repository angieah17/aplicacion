package com.midominio.group.app.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "resultados_test")
public class ResultadoTest {
    
    // 1. ATRIBUTOS
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY) // Relación muchos a uno con Usuario de tipo LAZY para cargar solo cuando se necesite
    @JoinColumn(name = "usuario_id", nullable = false) // Relación con la entidad Usuario
    private Usuario usuario;
    
    @NotNull(message = "La fecha de realización es obligatoria")
    @Column(name = "fecha_realizacion", nullable = false)
    private LocalDateTime fechaRealizacion;
    
    @NotNull(message = "La puntuación es obligatoria")
    @Column(nullable = false)
    private Double puntuacion;
    
    @Column(length = 100)
    private String tematica;
    
    @PrePersist
    protected void onCreate() {
        if (fechaRealizacion == null) {
            fechaRealizacion = LocalDateTime.now();
        }
    }
    
    
    // 2. CONSTRUCTORES
    
    public ResultadoTest() {
    }
    
    public ResultadoTest(Usuario usuario, Double puntuacion, String tematica) {
        this.usuario = usuario;
        this.puntuacion = puntuacion;
        this.tematica = tematica;
    }
    
    
    // 3. GETTERS Y SETTERS
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
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
}

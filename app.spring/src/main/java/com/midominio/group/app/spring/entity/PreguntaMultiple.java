package com.midominio.group.app.spring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "preguntas_multiple")
@DiscriminatorValue("MULTIPLE")
public class PreguntaMultiple extends Pregunta {
    
    // 1. ATRIBUTOS
    
    @NotNull(message = "Las opciones son obligatorias")
    @ElementCollection
    @CollectionTable(
        name = "pregunta_multiple_opciones",
        joinColumns = @JoinColumn(name = "pregunta_id")
    )
    @Column(name = "opcion", length = 500)
    @NotEmpty(message = "Debe haber al menos una opción")
    @OrderColumn(name = "orden")
    @Size(min = 3, message = "Debe haber al menos 3 opciones")
    private List<String> opciones = new ArrayList<>();
    
    @NotNull(message = "Las respuestas correctas son obligatorias")
    @ElementCollection
    @CollectionTable(
        name = "pregunta_multiple_respuestas",
        joinColumns = @JoinColumn(name = "pregunta_id")
    )
    @Column(name = "respuesta_correcta")
    @NotEmpty(message = "Debe haber al menos una respuesta correcta")
    @OrderColumn(name = "orden")
    private List<Integer> respuestasCorrectas = new ArrayList<>(); // Índices de las opciones correctas (0-based)
    
    
    // 2. CONSTRUCTORES
    
    public PreguntaMultiple() {
        
    }
    
    public PreguntaMultiple(List<String> opciones, List<Integer> respuestasCorrectas) {
        this.opciones = opciones;
        this.respuestasCorrectas = respuestasCorrectas;
    }
    
    
    // 3. METODOS
    
    @Override
    public String getTipoPregunta() {
        return "MULTIPLE";
    }
    
    public boolean validarRespuesta(List<Integer> respuestas) {
        if (respuestas == null || respuestas.isEmpty()) {
            return false;
        }
        
        // Validar que ambas listas tengan el mismo tamaño y contengan los mismos elementos
        if (respuestas.size() != this.respuestasCorrectas.size()) {
            return false;
        }
        
        return respuestas.containsAll(this.respuestasCorrectas) && 
               this.respuestasCorrectas.containsAll(respuestas);
    }
    
    
    // 4. GETTERS Y SETTERS
    
    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public List<Integer> getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    public void setRespuestasCorrectas(List<Integer> respuestasCorrectas) {
        this.respuestasCorrectas = respuestasCorrectas;
    }

}
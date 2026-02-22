package com.midominio.group.app.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

/**
 * DTO que contiene las respuestas del usuario para un test.
 * Mapea cada ID de pregunta a su respuesta correspondiente.
 */
public class TestSubmitDTO {
    
    @NotNull(message = "Las respuestas son obligatorias")
    @NotEmpty(message = "Debe responder al menos una pregunta")
    private Map<Long, RespuestaDTO> respuestas; // Key: ID de pregunta, Value: respuesta
    
    
    // CONSTRUCTORES
    
    public TestSubmitDTO() {
    }
    
    public TestSubmitDTO(Map<Long, RespuestaDTO> respuestas) {
        this.respuestas = respuestas;
    }
    
    
    // GETTERS Y SETTERS
    
    public Map<Long, RespuestaDTO> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(Map<Long, RespuestaDTO> respuestas) {
        this.respuestas = respuestas;
    }
}

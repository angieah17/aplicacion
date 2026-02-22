package com.midominio.group.app.spring.dto;

import java.util.List;

/**
 * DTO que representa una respuesta individual del usuario.
 * Puede ser Boolean (VF), Integer (Unica) o List<Integer> (Multiple).
 */
public class RespuestaDTO {
    
    private Boolean respuestaVF; // Para preguntas Verdadero/Falso
    private Integer respuestaUnica; // Para preguntas de opción única
    private List<Integer> respuestaMultiple; // Para preguntas de opción múltiple
    
    
    // CONSTRUCTORES
    
    public RespuestaDTO() {
    }
    
    public RespuestaDTO(Boolean respuestaVF) {
        this.respuestaVF = respuestaVF;
    }
    
    public RespuestaDTO(Integer respuestaUnica) {
        this.respuestaUnica = respuestaUnica;
    }
    
    public RespuestaDTO(List<Integer> respuestaMultiple) {
        this.respuestaMultiple = respuestaMultiple;
    }
    
    
    // GETTERS Y SETTERS
    
    public Boolean getRespuestaVF() {
        return respuestaVF;
    }

    public void setRespuestaVF(Boolean respuestaVF) {
        this.respuestaVF = respuestaVF;
    }

    public Integer getRespuestaUnica() {
        return respuestaUnica;
    }

    public void setRespuestaUnica(Integer respuestaUnica) {
        this.respuestaUnica = respuestaUnica;
    }

    public List<Integer> getRespuestaMultiple() {
        return respuestaMultiple;
    }

    public void setRespuestaMultiple(List<Integer> respuestaMultiple) {
        this.respuestaMultiple = respuestaMultiple;
    }
}

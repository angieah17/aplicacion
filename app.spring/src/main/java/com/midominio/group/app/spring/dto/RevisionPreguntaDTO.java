package com.midominio.group.app.spring.dto;

/**
 * DTO que representa la revisión de una pregunta individual dentro de un test corregido.
 * Incluye la respuesta del usuario, la correcta y la explicación.
 */
public class RevisionPreguntaDTO {
    
    private Long preguntaId;
    private String enunciado;
    private String tipoPregunta;
    private Boolean esCorrecta;
    private String respuestaUsuario;
    private String respuestaCorrecta;
    private String explicacion;
    
    
    // CONSTRUCTORES
    
    public RevisionPreguntaDTO() {
    }
    
    public RevisionPreguntaDTO(Long preguntaId, String enunciado, String tipoPregunta,
                              Boolean esCorrecta, String respuestaUsuario, String respuestaCorrecta,
                              String explicacion) {
        this.preguntaId = preguntaId;
        this.enunciado = enunciado;
        this.tipoPregunta = tipoPregunta;
        this.esCorrecta = esCorrecta;
        this.respuestaUsuario = respuestaUsuario;
        this.respuestaCorrecta = respuestaCorrecta;
        this.explicacion = explicacion;
    }
    
    
    // GETTERS Y SETTERS
    
    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
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

    public Boolean getEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(Boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public String getRespuestaUsuario() {
        return respuestaUsuario;
    }

    public void setRespuestaUsuario(String respuestaUsuario) {
        this.respuestaUsuario = respuestaUsuario;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }
}

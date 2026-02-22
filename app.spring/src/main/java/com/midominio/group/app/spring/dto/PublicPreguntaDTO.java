package com.midominio.group.app.spring.dto;

public class PublicPreguntaDTO {
    private Long id;
    private String enunciado;
    private String tematica;
    private String tipo;

    public PublicPreguntaDTO() {}

    public PublicPreguntaDTO(Long id, String enunciado, String tematica, String tipo) {
        this.id = id;
        this.enunciado = enunciado;
        this.tematica = tematica;
        this.tipo = tipo;
    }

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

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

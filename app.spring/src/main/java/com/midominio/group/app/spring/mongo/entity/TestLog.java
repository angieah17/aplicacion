package com.midominio.group.app.spring.mongo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "test_logs")
public class TestLog {

    @Id
    private String id;

    private String username;

    private LocalDateTime fecha;

    private Double nota;

    public TestLog() {
    }

    public TestLog(String username, LocalDateTime fecha, Double nota) {
        this.username = username;
        this.fecha = fecha;
        this.nota = nota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}

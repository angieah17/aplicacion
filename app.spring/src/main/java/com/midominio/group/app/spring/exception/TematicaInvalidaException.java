package com.midominio.group.app.spring.exception;

public class TematicaInvalidaException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    public TematicaInvalidaException(String message) {
        super(message);
    }
}

package com.midominio.group.app.spring.entity;

/**
 * Enum que define los tipos de preguntas disponibles en el sistema.
 * 
 * Debe coincidir con los valores del discriminador (@DiscriminatorValue) 
 * en las clases de entidad PreguntaVF, PreguntaUnica y PreguntaMultiple.
 */
public enum TipoPreguntaEnum {
    VERDADERO_FALSO("VERDADERO_FALSO"),
    UNICA("UNICA"),
    MULTIPLE("MULTIPLE");
    
    private final String value;
    
    TipoPreguntaEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
    
    /**
     * Obtiene el tipo de pregunta a partir de su valor en string.
     * La comparación es case-insensitive.
     * 
     * @param value El valor en string (puede ser null o vacío)
     * @return El enum correspondiente, o null si value es null/vacío
     * @throws IllegalArgumentException si el valor no corresponde a ningún tipo válido
     */
    public static TipoPreguntaEnum fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        
        String normalizado = value.trim().toUpperCase();
        for (TipoPreguntaEnum tipo : TipoPreguntaEnum.values()) {
            if (tipo.value.equals(normalizado)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException(
            "Tipo de pregunta inválido: '" + value + "'. " +
            "Valores permitidos: VERDADERO_FALSO, UNICA, MULTIPLE"
        );
    }
    
    /**
     * Verifica si un string corresponde a un tipo de pregunta válido.
     * 
     * @param value El valor a verificar
     * @return true si es válido o null/vacío, false en caso contrario
     */
    public static boolean isValid(String value) {
        if (value == null || value.trim().isEmpty()) {
            return true; // null es válido (significa sin filtro)
        }
        
        try {
            fromValue(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
